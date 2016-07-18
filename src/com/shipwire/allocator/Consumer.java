package com.shipwire.allocator;
import com.shipwire.allocator.model.Allocator;
import com.shipwire.allocator.model.Inventory;
import com.shipwire.allocator.model.Line;
import com.shipwire.allocator.model.OrderMessage;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    private BlockingQueue<OrderMessage> queue;
    private Inventory inventory;


    public Consumer(BlockingQueue<OrderMessage> _queue, Inventory _inventory) {
        this.queue = _queue;
        this.inventory = _inventory;
    }

    @Override
    public void run() {
        try {
            OrderMessage msg;
            Allocator allocator;
            Map<String, Integer> requested;
            Map<String, Integer> allocated;
            Map<String, Integer> backordered;
            Map<String, Integer> orderLine;
            while (!queue.isEmpty() && inventory.getTotalAmount() > 0) {
                msg = queue.poll();
                orderLine=new HashMap<String,Integer>();
                for(Line line:msg.getLines()){
                    orderLine.put(line.getProduct(), line.getQuantity());
                }
                allocator = new Allocator();
                allocator.setHeader(msg.getHeader());
                requested = new HashMap<String, Integer>();
                allocated = new HashMap<String, Integer>();
                backordered = new HashMap<String, Integer>();
                for(String product:inventory.getProducts().keySet()){
                    requested.put(product, orderLine.get(product) != null ? orderLine.get(product) : 0);
                    if (orderLine.get(product) != null) {
                        if (inventory.getProducts().get(product) > 0
                                && inventory.getProducts().get(product) >= orderLine.get(product)) {
                            allocated.put(product, orderLine.get(product));
                            backordered.put(product, 0);
                            inventory.allocateProduct(product, orderLine.get(product));
                        } else {
                            allocated.put(product, 0);
                            backordered.put(product, orderLine.get(product));
                        }
                    } else {
                        allocated.put(product, 0);
                        backordered.put(product, 0);
                    }

                }
                allocator.setRequested(requested);
                allocator.setAllocated(allocated);
                allocator.setBackordered(backordered);
                inventory.getAllocatorList().add(allocator);
            }
            pringResult(inventory);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pringResult(Inventory inventory) {
        StringBuilder builder;
        for (Allocator item : inventory.getAllocatorList()) {
            builder = new StringBuilder();
            builder.append(item.getHeader()).append(": ");
            for (Integer quantity : item.getRequested().values()) {
                builder.append(quantity).append(",");
            }
            builder.deleteCharAt(builder.lastIndexOf(",")).append("::");

            for (Integer quantity : item.getAllocated().values()) {
                builder.append(quantity).append(",");
            }
            builder.deleteCharAt(builder.lastIndexOf(",")).append("::");
            for (Integer quantity : item.getBackordered().values()) {
                builder.append(quantity).append(",");
            }
            builder.deleteCharAt(builder.lastIndexOf(","));
            System.out.println(builder.toString());
        }
    }

}
