package com.mini.timedeal.domain.prodcut.mapper;

import com.mini.timedeal.domain.prodcut.model.Product;

import java.util.List;

public interface productMapper {
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
