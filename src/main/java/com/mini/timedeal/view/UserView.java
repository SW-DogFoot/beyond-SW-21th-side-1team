package com.mini.timedeal.view;

import com.mini.timedeal.config.AppContext;
import com.mini.timedeal.domain.promotion.dto.PromotionDTO;
import com.mini.timedeal.domain.user.dto.UserProductDTO;
import com.mini.timedeal.domain.user.model.User;
import com.mini.timedeal.domain.user.service.UserProductService;
import com.mini.timedeal.domain.user.service.UserService;
import com.mini.timedeal.enums.PromotionStatus;

import java.util.List;
import java.util.Scanner;

public class UserView {

    private Scanner sc = new Scanner(System.in);
    private final UserService userService;
    private final UserProductService userProductService;

    public UserView() {
        AppContext context = AppContext.getInstance();
        this.userService = context.getBean(UserService.class);
        this.userProductService = context.getBean(UserProductService.class);
    }

    /*
    * 일반 유저 메뉴
    * */
    public void userMenu(User user) {

        while (true) {
            System.out.print("""
                    [일반 유저 메뉴]
                    1. 핫딜 상품 검색
                    2. 핫딜 상품 구매
                    3. 구매한 상품 검색
                    0. 나가기
                    """);
            System.out.print("실행할 메뉴 번호를 입력하세요 : ");
            int menu = Integer.parseInt(sc.nextLine());

            switch (menu) {
                case 1:
                    PromotionView promotionView = new PromotionView();
                    promotionView.showPromotions(PromotionStatus.ACTIVE);
                    System.out.println();
                    break;
                case 2:
                    while (true) {
                        System.out.print("구매할 상품의 프로모션 아이디를 입력해주세요 : ");
                        String input = sc.next();
                        System.out.println("------------------------------------------");
                        try {
                            Long promotionId = Long.parseLong(input);
                            userService.order(promotionId, user);
                            System.out.println();
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("숫자만 입력해주세요.");
                        } catch (IllegalArgumentException | IllegalStateException e) {
                            System.out.println(e.getMessage() + " 다시 입력해주세요.");
                        }
                    }
                    break;
                case 3:
                    List<UserProductDTO> userProducts = userProductService.userProductList(user.getId());
                    if(userProducts.isEmpty()) {
                        System.out.println("구매한 상품이 없습니다.");
                    } else {
                        printUserProducts(userProducts);
                    }
                    System.out.println();
                    break;
                case 0:
                    return;
                default: System.out.println("다시 입력해주세요."); System.out.println(); break;
            }
        }
    }

    /*
    * 사용자 구매 상품 출력
    * */
    private void printUserProducts(List<UserProductDTO> userProducts) {
        final String headerFormat = "| %-20s | %-20s | %-20s | %-25s |";
        final String rowFormat = "| %-20d | %-20s | %-20s | %-25s |";
        final String separator = "+----------------------+----------------------+----------------------+---------------------------+";

        System.out.println(separator);
        System.out.printf(headerFormat, "Promotion ID", "Product Name", "Price", "Purchased Date");
        System.out.println();
        System.out.println(separator);

        for (UserProductDTO userProduct : userProducts) {
            System.out.printf(rowFormat,
                    userProduct.getPromotionId(),
                    userProduct.getProductName(),
                    userProduct.getPrice(),
                    userProduct.getPurchasedAt()
            );
            System.out.println();
        }
        System.out.println(separator);
    }
}
