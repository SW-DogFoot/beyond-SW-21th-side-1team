package com.mini.timedeal.view;

import com.mini.timedeal.config.AppContext;
import com.mini.timedeal.domain.promotion.model.Promotion;
import com.mini.timedeal.domain.user.dto.UserProductDTO;
import com.mini.timedeal.domain.user.mapper.UserMapper;
import com.mini.timedeal.domain.user.model.User;
import com.mini.timedeal.domain.user.service.UserProductService;
import com.mini.timedeal.domain.user.service.UserService;
import com.mini.timedeal.domain.user.storage.UserProductRepository;
import com.mini.timedeal.domain.user.storage.UserRepository;
import com.mini.timedeal.enums.PromotionStatus;
import com.mini.timedeal.enums.UserRole;

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
                    System.out.print("구매할 상품의 프로모션 아이디를 입력해주세요 : ");
                    Long promotionId = Long.parseLong(sc.nextLine());
                    userService.order(promotionId);
                    System.out.println();
                    break;
                case 3:
                    List<UserProductDTO> userProducts = userProductService.userProductList(user.getId());
                    if(userProducts.isEmpty()) {
                        System.out.println("구매한 상품이 없습니다.");
                    } else {
                        System.out.println("-------------------------");
                        for (var product : userProducts) {
                            System.out.println(product);
                        }
                    }
                    System.out.println();
                    break;
                case 0:
                    return;
                default: System.out.println("다시 입력해주세요."); System.out.println(); break;
            }
        }
    }
}
