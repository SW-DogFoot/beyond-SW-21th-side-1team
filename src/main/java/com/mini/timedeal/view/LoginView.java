package com.mini.timedeal.view;

import com.mini.timedeal.config.AppContext;
import com.mini.timedeal.domain.promotion.service.PromotionService;
import com.mini.timedeal.domain.user.model.User;
import com.mini.timedeal.domain.user.service.UserProductService;
import com.mini.timedeal.domain.user.service.UserService;
import com.mini.timedeal.domain.user.storage.UserProductRepository;
import com.mini.timedeal.domain.user.storage.UserRepository;
import com.mini.timedeal.enums.UserRole;

import java.util.Scanner;

public class LoginView {
    private final UserService userService;
    private Scanner sc = new Scanner(System.in);

    public LoginView() {
        AppContext context = AppContext.getInstance();
        this.userService = context.getBean(UserService.class);
    }

    /*
     * 로그인
     * */
    public void login() {

        User user = null;
        while (true) {
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
                UserView userView = new UserView();
                userView.userMenu(user);
            } else if(user.getRole() == UserRole.ADMIN) {
                System.out.println();
                AdminView adminView = new AdminView();
                adminView.showAdminMenu();
            }
        }
    }
}
