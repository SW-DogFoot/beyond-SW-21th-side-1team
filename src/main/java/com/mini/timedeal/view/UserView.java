package com.mini.timedeal.view;

import com.mini.timedeal.domain.promotion.model.Promotion;
import com.mini.timedeal.domain.user.dto.UserProductDTO;
import com.mini.timedeal.domain.user.mapper.UserMapper;
import com.mini.timedeal.domain.user.model.User;
import com.mini.timedeal.domain.user.service.UserProductService;
import com.mini.timedeal.domain.user.service.UserService;
import com.mini.timedeal.domain.user.storage.UserProductRepository;
import com.mini.timedeal.domain.user.storage.UserRepository;
import com.mini.timedeal.enums.UserRole;

import java.util.List;
import java.util.Scanner;

public class UserView {

    Scanner sc = new Scanner(System.in);

    UserService userService = new UserService(new UserRepository());
    UserProductService userProductService = new UserProductService(new UserProductRepository());
    User user = new User();

    /*
    * 로그인
    * */
    public void login() {

        System.out.println("[로그인]");
        System.out.print("username 입력 : ");
        String username = sc.next();
        System.out.print("password 입력 : ");
        String password = sc.next();

        user = userService.login(username, password);

        if (user == null) {
            System.out.println("존재하지 않는 회원입니다. 다시 입력해주세요.\n");
            login();
        } else if(user.getRole() == UserRole.USER) {
            System.out.println();
            userMenu();
        } else if(user.getRole() == UserRole.ADMIN) {
            System.out.println();
            adminMenu();
        }
    }

    /*
    * 일반 유저 메뉴
    * */
    public void userMenu() {

        while (true) {
            System.out.print("""
                    [일반 유저 메뉴]
                    1. 핫딜 상품 검색
                    2. 핫딜 상품 구매
                    3. 구매한 상품 검색
                    0. 나가기
                    """);
            System.out.print("실행할 메뉴 번호를 입력하세요 : ");
            int menu = sc.nextInt();

            switch (menu) {
                case 1:
                    PromotionView promotionView = new PromotionView();
                    promotionView.showPromotions();
                    System.out.println();
                    break;
                case 2:
                    System.out.print("구매할 상품의 프로모션 아이디를 입력해주세요 : ");
                    Long promotionId = sc.nextLong();
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
                case 0: System.exit(0);
                default: System.out.println("다시 입력해주세요."); System.out.println(); break;
            }
        }
    }

    /*
    * 관리자 메뉴
    * */
    public void adminMenu() {

    }
}
