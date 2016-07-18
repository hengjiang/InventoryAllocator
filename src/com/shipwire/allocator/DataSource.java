
package com.shipwire.allocator;

import com.shipwire.allocator.model.Line;
import com.shipwire.allocator.model.OrderMessage;

import java.util.ArrayList;
import java.util.List;

public class DataSource {

    private Producer producer;


    public DataSource(Producer _producer) {
        this.producer = _producer;
    }

    public void addOrderMessage(Integer stream, Integer header, Line[] linesInput) {
        OrderMessage orderMessage1 = new OrderMessage();
        orderMessage1.setStream(stream);
        orderMessage1.setHeader(header);
        List<Line> lines = new ArrayList<Line>();
        for (Line line : linesInput) {
            if (line.getProduct() != null && !"".equals(line.getProduct()) && line.getQuantity() != null
                    && line.getQuantity() > 0 && line.getQuantity() <= 5) {
                lines.add(line);
            }
        }
        if (!lines.isEmpty()) {
            orderMessage1.setLines(lines);
            producer.sendMessage(orderMessage1);
        }
    }

}
