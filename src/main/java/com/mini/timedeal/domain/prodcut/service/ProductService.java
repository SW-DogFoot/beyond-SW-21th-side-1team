package com.mini.timedeal.domain.prodcut.service;

import com.mini.timedeal.domain.prodcut.model.Product;
import com.mini.timedeal.domain.prodcut.dto.ProductDTO;
import com.mini.timedeal.domain.prodcut.mapper.ProductMapper;

public class ProductService {

    private final ProductMapper productMapper;// 생성자 주입
    public ProductService(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }


    // 상품 등록
    public void addProduct(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        this.productMapper.insertProduct(product);
    }
    // 상품 삭제
    public void deleteProduct(Long id) {
        productMapper.deleteProduct(id);
    }
    // 상품 수정
    public void updateProduct(Long productId, ProductDTO dto) {
        Product product = new Product();
        product.setId(productId);
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        this.productMapper.updateProduct(product);
    }
    // 상품 검색
    public Product searchProduct(Long productId) {
        return productMapper.findById(productId);
    }
}
