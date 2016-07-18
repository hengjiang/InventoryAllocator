package com.shipwire.allocator.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Inventory {

    private Map<String, Integer> products;
    private List<Allocator> allocatorList;
    private Integer totalAmount;


    public Inventory() {
        this.products = new ConcurrentHashMap<String, Integer>();
        this.totalAmount = 0;
        this.allocatorList = new ArrayList<Allocator>();
    }

    /**
     * @param products
     * @param totalAmount
     */
    public Inventory(Map<String, Integer> _products, Integer _totalAmount) {
        super();
        this.products = _products;
        this.totalAmount = _totalAmount;
    }

    public Map<String, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<String, Integer> products) {
        this.products = products;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<Allocator> getAllocatorList() {
        return allocatorList;
    }

    public void setAllocatorList(List<Allocator> allocatorList) {
        this.allocatorList = allocatorList;
    }
    public void addProduct(String _product, Integer _quantity) {
        if (products.get(_product) == null) {
            products.put(_product, _quantity);
        } else {
            products.put(_product, _quantity + products.get(_product));
        }
        totalAmount += _quantity;
    }

    public void allocateProduct(String _product, Integer _quantity) {
        products.put(_product, products.get(_product) - _quantity);
        totalAmount -= _quantity;
    }

    public void addProduct(Map<String, Integer> _products) {
        for (String _product : _products.keySet()) {
            if (products.get(_product) == null) {
                products.put(_product, _products.get(_product));
            } else {
                products.put(_product, _products.get(_product) + products.get(_product));
            }
            totalAmount += _products.get(_product);
        }

    }
}
