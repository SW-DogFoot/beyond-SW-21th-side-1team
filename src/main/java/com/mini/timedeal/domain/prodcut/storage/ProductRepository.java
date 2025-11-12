package com.mini.timedeal.domain.prodcut.storage;

import com.mini.timedeal.domain.prodcut.model.Product;
import com.mini.timedeal.domain.prodcut.mapper.ProductMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepository implements ProductMapper {

    private Long primaryKey = 1L;
    private final Map<Long, Product> products = new HashMap<>();

    public ProductRepository() {
    }

    @Override
    public void insertProduct(Product product) {
        Long id = primaryKey++;
        product.setId(id);
        products.put(id, product);
    }

    @Override
    public void deleteProduct(Long id) {
        products.remove(id);
    }

    @Override
    public void updateProduct(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public Product findById(Long id) {
        return products.get(id);
    }

    @Override
    public List<Product> findAll() {
        return products.values().stream().toList();
    }

    @Override
    public List<Product> findByName(String name) {
        return products.values().stream().filter(product -> product.getName().equals(name)).toList();
    }
}
