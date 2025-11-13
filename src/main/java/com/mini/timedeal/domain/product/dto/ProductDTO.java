package com.mini.timedeal.domain.product.dto;

public class ProductDTO {

    // 상품 정보
    private String name;
    private String description;
    private Integer price;

    public ProductDTO() {
    }

    public ProductDTO(String name, String description, Integer price) {
        this.name = name;
        this.description = description;
        this.price = price;
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
