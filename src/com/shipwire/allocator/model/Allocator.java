package com.shipwire.allocator.model;
import java.util.HashMap;
import java.util.Map;
public class Allocator {

    private Integer header;
    private Map<String, Integer> requested;
    private Map<String, Integer> allocated;
    private Map<String, Integer> backordered;

    public Allocator(){
        this.header=null;
        this.requested=new HashMap<String,Integer>();
        this.allocated=new HashMap<String,Integer>();
        this.backordered = new HashMap<String, Integer>();
    }

    public Integer getHeader() {
        return header;
    }

    public void setHeader(Integer header) {
        this.header = header;
    }

    public Map<String, Integer> getRequested() {
        return requested;
    }

    public void setRequested(Map<String, Integer> requested) {
        this.requested = requested;
    }

    public Map<String, Integer> getAllocated() {
        return allocated;
    }

    public void setAllocated(Map<String, Integer> allocated) {
        this.allocated = allocated;
    }

    public Map<String, Integer> getBackordered() {
        return backordered;
    }

    public void setBackordered(Map<String, Integer> backordered) {
        this.backordered = backordered;
    }

}
