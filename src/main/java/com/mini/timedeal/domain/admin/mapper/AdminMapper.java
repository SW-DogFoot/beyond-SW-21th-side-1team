package com.mini.timedeal.domain.admin.mapper;

import com.mini.timedeal.domain.Product;
import com.mini.timedeal.domain.admin.model.AdminUser;

import java.util.List;

public interface AdminMapper {
    //상품 등록
    void insertProduct(Product product);

    //상품 삭제
    void deleteProduct(Long id);

    //상품 수정
    void updateProduct(Product product);

    //상품 검색
    Product findById(Long id);
    List<Product> findAll();
    List<Product> findByName(String name);
}
