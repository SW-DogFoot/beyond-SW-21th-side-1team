package com.mini.timedeal.view;

import com.mini.timedeal.domain.prodcut.model.Product;
import com.mini.timedeal.domain.prodcut.dto.ProductDTO;
import com.mini.timedeal.domain.prodcut.service.ProductService;

import java.util.Scanner;

public class AdminView_sj {
    private final ProductService productService;
    Scanner scanner = new Scanner(System.in);

    public AdminView_sj(ProductService productService) {
        this.productService = productService;
    }

    //판매자 메뉴
    public void showMenu(){
        System.out.println("\n===== 판매자(Admin) 메뉴 =====");
        System.out.println("1. 상품 등록");
        System.out.println("2. 상품 수정");
        System.out.println("3. 상품 삭제");
        System.out.println("4. 상품 검색");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> addProduct();
            case 2 -> updateProduct();
            case 3 -> deleteProduct();
            case 4 -> searchProduct();
//            case 5 -> showAllProducts();
            case 0 -> {
                System.out.println("메인 메뉴로 돌아갑니다.");
                return;
            }
            default -> System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
        }
    }

    private void addProduct() {
        System.out.println("\n[상품 등록]");
        System.out.print("상품명: ");
        String name = scanner.nextLine();
        System.out.print("설명: ");
        String description = scanner.nextLine();
        System.out.print("가격: ");
        int price = scanner.nextInt();

        ProductDTO dto = new ProductDTO();
        dto.setName(name);
        dto.setDescription(description);
        dto.setPrice(price);

        productService.addProduct(dto);
        System.out.println("상품이 등록되었습니다!");
    }

    private void updateProduct() {
        System.out.println("\n[상품 수정]");
        System.out.print("수정할 상품 ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        System.out.print("새 상품명: ");
        String name = scanner.nextLine();
        System.out.print("새 설명: ");
        String description = scanner.nextLine();
        System.out.print("새 가격: ");
        int price = scanner.nextInt();

        ProductDTO dto = new ProductDTO();
        dto.setName(name);
        dto.setDescription(description);
        dto.setPrice(price);

        productService.updateProduct(id, dto);
        System.out.println("상품이 수정되었습니다!");
    }

    private void deleteProduct() {
        System.out.println("\n[상품 삭제]");
        System.out.print("삭제할 상품 ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        productService.deleteProduct(id);
        System.out.println("상품이 삭제되었습니다!");
    }

    private void searchProduct() {
        System.out.println("\n[상품 검색]");
        System.out.print("검색할 상품 ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Product product = productService.searchProduct(id);
        if (product == null) {
            System.out.println("해당 상품이 존재하지 않습니다.");
        } else {
            printProduct(product);
        }
    }

    private void printProduct(Product product) {
        System.out.println("----------------------------------------");
        System.out.println("상품 ID: " + product.getId());
        System.out.println("상품명: " + product.getName());
        System.out.println("설명: " + product.getDescription());
        System.out.println("가격: " + product.getPrice());
        System.out.println("----------------------------------------");
    }

}




