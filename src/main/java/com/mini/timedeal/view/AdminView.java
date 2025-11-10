package com.mini.timedeal.view;

import com.mini.timedeal.config.AppContext;
import com.mini.timedeal.domain.promotion.service.PromotionService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AdminView {
    private final PromotionService promotionService;
    private final PromotionView promotionView = new PromotionView();

    AdminView() {
        AppContext context = AppContext.getInstance();
        this.promotionService = context.getBean(PromotionService.class);
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
                    break;
                case 2:
                    // 상품 변경
                    break;
                case 3:
                    // 상품 제거
                    break;
                case 4:
                    // 상품 특정 조회
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

    private void registerPromotion() {
        Scanner sc = new Scanner(System.in);

        System.out.println("프로모션 등록: ");

        System.out.print("상품 아이디: ");
        Integer productId = Integer.parseInt(sc.nextLine());

        System.out.print("상품 할인률: ");
        Integer discountRate = Integer.parseInt(sc.nextLine());

        System.out.print("상품 수량: ");
        Integer quantity = Integer.parseInt(sc.nextLine());

        System.out.print("프로모션 시작 날짜와 시간을 입력하세요 (yyyy-MM-dd HH:mm:ss): ");
        String startDateTimeInput = sc.nextLine();
        LocalDateTime startDateTime = LocalDateTime.parse(startDateTimeInput, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        System.out.print("프로모션 종료 날짜와 시간을 입력하세요 (yyyy-MM-dd HH:mm:ss): ");
        String endDateTimeInput = sc.nextLine();
        LocalDateTime endDateTime = LocalDateTime.parse(endDateTimeInput, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        promotionService.registerPromotion(productId, discountRate, quantity, startDateTime, endDateTime);
    }

    private void deregisterPromotion() {
        Scanner sc = new Scanner(System.in);

        System.out.println("프로모션 해제: ");

        System.out.print("프로모션 아이디: ");
        Integer promotionId = Integer.parseInt(sc.nextLine());

        promotionService.deregisterPromotion(promotionId);
    }
}
