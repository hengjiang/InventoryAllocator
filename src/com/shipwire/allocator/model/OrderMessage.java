package com.shipwire.allocator.model;
import java.util.ArrayList;
import java.util.List;

public class OrderMessage {

    private Integer stream;
    private Integer header;
    private List<Line> lines;


    public OrderMessage() {
        this.header = null;
        this.lines = new ArrayList<Line>();
    }
    public OrderMessage(Integer _header, List<Line> _lines) {
        this.header = _header;
        this.lines = _lines;
    }

    public Integer getHeader() {
        return header;
    }


    public void setHeader(Integer header) {
        this.header = header;
    }

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    public Integer getStream() {
        return stream;
    }

    public void setStream(Integer stream) {
        this.stream = stream;
    }

}
