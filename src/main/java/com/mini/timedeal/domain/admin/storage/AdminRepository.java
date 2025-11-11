package com.mini.timedeal.domain.admin.storage;

import com.mini.timedeal.domain.Product;
import com.mini.timedeal.domain.admin.mapper.AdminMapper;
import com.mini.timedeal.domain.admin.model.AdminUser;
import com.mini.timedeal.domain.promotion.model.Promotion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminRepository implements AdminMapper {

    private Long primaryKey = 1L;
    private final Map<Long, Product> products = new HashMap<>();

    public AdminRepository() {
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
