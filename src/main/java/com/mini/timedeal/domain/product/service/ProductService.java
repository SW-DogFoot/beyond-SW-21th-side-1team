package com.mini.timedeal.domain.product.service;

import com.mini.timedeal.domain.product.model.Product;
import com.mini.timedeal.domain.product.dto.ProductDTO;
import com.mini.timedeal.domain.product.mapper.ProductMapper;

import java.util.List;

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
    // 상품 검색(특정)
    public Product searchProduct(Long productId) {
        return productMapper.findById(productId);
    }

    // 상품 전체 조회
    public List<Product> searchAllProduct() {
        return productMapper.findAll();
    }
}
