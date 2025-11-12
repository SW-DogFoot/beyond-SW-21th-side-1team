package com.mini.timedeal.domain.admin.dto;

import java.time.LocalDateTime;

public class AdminDTO {

    // 판매자 정보
    private Long adminId;
    private String adminName;

    // 상품 정보
    private String name;
    private String description;
    private Integer price;

    public AdminDTO() {
    }

    public AdminDTO(Long adminId, String adminName, String name, String description, Integer price) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
