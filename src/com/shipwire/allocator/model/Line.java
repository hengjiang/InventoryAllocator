package com.shipwire.allocator.model;
public class Line {
    
    private String product;
    private Integer quantity;
    
    
    public Line(String _product, Integer _quantity) {
        this.product = _product;
        this.quantity = _quantity;
    }
    
    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }


    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
