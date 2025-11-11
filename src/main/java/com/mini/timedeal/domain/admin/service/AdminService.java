package com.mini.timedeal.domain.admin.service;

import com.mini.timedeal.domain.Product;
import com.mini.timedeal.domain.admin.dto.AdminDTO;
import com.mini.timedeal.domain.admin.mapper.AdminMapper;

public class AdminService {

    private final AdminMapper adminMapper;// 생성자 주입
    public AdminService(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }


    // 상품 등록
    public void addProduct(AdminDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        adminMapper.insertProduct(product);
    }
    // 상품 삭제
    public void deleteProduct(Long id) {
        adminMapper.deleteProduct(id);
    }
    // 상품 수정
    public void updateProduct(Long productId, AdminDTO dto) {
        Product product = new Product();
        product.setId(productId);
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        adminMapper.updateProduct(product);
    }
    // 상품 검색
    public Product searchProduct(Long productId) {
        return adminMapper.findById(productId);
    }
}
