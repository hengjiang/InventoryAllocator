package com.shipwire.allocator;
import com.shipwire.allocator.model.OrderMessage;

import java.util.concurrent.BlockingQueue;

public class Producer {

    private BlockingQueue<OrderMessage> queue;


    public Producer(BlockingQueue<OrderMessage> q) {
        this.queue = q;
    }

    public void sendMessage(OrderMessage msg) {
        try {
            queue.put(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
