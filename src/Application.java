
import com.shipwire.allocator.Consumer;
import com.shipwire.allocator.DataSource;
import com.shipwire.allocator.Producer;
import com.shipwire.allocator.model.Inventory;
import com.shipwire.allocator.model.Line;
import com.shipwire.allocator.model.OrderMessage;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Application {

    public static void main(String[] args) {
        // initialize inventory
        Inventory inventory=new Inventory();
        Map<String, Integer> products = new HashMap<String, Integer>() {
            
            {
                put("A", 2);
                put("B", 3);
                put("C", 1);
                put("D", 0);
                put("E", 0);
            }
        };
        inventory.addProduct(products);
        // inventory.addProduct("A", 2);
        // inventory.addProduct("B", 3);
        // inventory.addProduct("C", 1);
        // inventory.addProduct("D", 0);
        // inventory.addProduct("E", 0);

        // build producer
        BlockingQueue<OrderMessage> queue = new ArrayBlockingQueue<>(10000);
        Producer producer = new Producer(queue);
        DataSource dataSource = new DataSource(producer);
        // build 6 orders
        // order 1
        Line[] line1 = {new Line("A", 1), new Line("C", 1)};
        dataSource.addOrderMessage(123, 1, line1);
        // order 2
        Line[] line2 = {new Line("E", 5)};
        dataSource.addOrderMessage(123, 2, line2);
        // order 3
        Line[] line3 = {new Line("D", 4)};
        dataSource.addOrderMessage(123, 3, line3);
        // order 4
        Line[] line4 = {new Line("A", 1), new Line("C", 1)};
        dataSource.addOrderMessage(123, 4, line4);
        // order 5
        Line[] line5 = {new Line("B", 3)};
        dataSource.addOrderMessage(123, 5, line5);
        // order 6
        Line[] line6 = {new Line("D", 4)};
        dataSource.addOrderMessage(123, 6, line6);

        // build consumer and start
        Consumer consumer = new Consumer(queue, inventory);
        new Thread(consumer).start();

    }

}
