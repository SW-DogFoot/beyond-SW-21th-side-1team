package com.mini.timedeal.view;

import com.mini.timedeal.config.AppContext;
import com.mini.timedeal.domain.product.dto.ProductDTO;
import com.mini.timedeal.domain.product.model.Product;
import com.mini.timedeal.domain.product.service.ProductService;
import com.mini.timedeal.domain.promotion.service.PromotionService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AdminView {
    private final PromotionService promotionService;
    private final ProductService productService;
    private final PromotionView promotionView = new PromotionView();
    private Scanner scanner = new Scanner(System.in);

    AdminView() {
        AppContext context = AppContext.getInstance();
        this.promotionService = context.getBean(PromotionService.class);
        this.productService = context.getBean(ProductService.class);
    }

    /**
     *  관리자 메뉴
     */
    public void showAdminMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("관리자 메뉴");
        do {
            String menu = """
                    ==== 메뉴 ====
                    1. 상품 추가
                    2. 상품 수정
                    3. 상품 제거
                    4. 상품 특정 조회
                    5. 상품 전체 조회
                    6. 프로모션 등록
                    7. 프로모션 해제
                    8. 프로모션 조회
                    0. 프로그램 종료하기
                    ==========================================
                    메뉴 선택 : """;
            System.out.print(menu);

            int number = Integer.parseInt(sc.nextLine());
            System.out.flush();
            switch (number) {
                case 1:
                    // 상품 추가
                    addProduct();
                    break;
                case 2:
                    // 상품 변경
                    updateProduct();
                    break;
                case 3:
                    // 상품 제거
                    deleteProduct();
                    break;
                case 4:
                    // 상품 특정 조회
                    searchProduct();
                    break;
                case 5:
                    // 상품 전체 조회
                    break;
                case 6:
                    // 프로모션 등록
                    registerPromotion();
                    break;
                case 7:
                    // 프로모션 해제
                    deregisterPromotion();
                    break;
                case 8:
                    // 프로모션 전체 조회
                    promotionView.showPromotions();
                    break;
                case 0:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 번호를 입력하셨습니다.");;
            }
        } while (true);
    }

    private void addProduct() {
        System.out.println("\n[상품 등록]");
        System.out.print("상품명: ");
        String name = scanner.nextLine();
        System.out.print("설명: ");
        String description = scanner.nextLine();
        System.out.print("가격: ");
        int price = Integer.parseInt(scanner.nextLine());

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
        int price = Integer.parseInt(scanner.nextLine());

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

    private void registerPromotion() {

        System.out.println("프로모션 등록: ");

        System.out.print("상품 아이디: ");
        Integer productId = Integer.parseInt(scanner.nextLine());

        System.out.print("상품 할인률: ");
        Integer discountRate = Integer.parseInt(scanner.nextLine());

        System.out.print("상품 수량: ");
        Integer quantity = Integer.parseInt(scanner.nextLine());

        System.out.print("프로모션 시작 날짜와 시간을 입력하세요 (yyyy-MM-dd HH:mm:ss): ");
        String startDateTimeInput = scanner.nextLine();
        LocalDateTime startDateTime = LocalDateTime.parse(startDateTimeInput, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        System.out.print("프로모션 종료 날짜와 시간을 입력하세요 (yyyy-MM-dd HH:mm:ss): ");
        String endDateTimeInput = scanner.nextLine();
        LocalDateTime endDateTime = LocalDateTime.parse(endDateTimeInput, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        promotionService.registerPromotion(productId, discountRate, quantity, startDateTime, endDateTime);
    }

    private void deregisterPromotion() {
        System.out.println("프로모션 해제: ");

        System.out.print("프로모션 아이디: ");
        Integer promotionId = Integer.parseInt(scanner.nextLine());

        promotionService.deregisterPromotion(promotionId);
    }
}
