package com.mini.timedeal.view;

import com.mini.timedeal.domain.user.model.User;
import com.mini.timedeal.domain.user.service.UserProductService;
import com.mini.timedeal.domain.user.service.UserService;
import com.mini.timedeal.domain.user.storage.UserProductRepository;
import com.mini.timedeal.domain.user.storage.UserRepository;
import com.mini.timedeal.enums.UserRole;

import java.util.Scanner;

public class LoginView {

    Scanner sc = new Scanner(System.in);

    UserService userService = new UserService(new UserRepository());
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
            UserView userView = new UserView();
            userView.userMenu();
        } else if(user.getRole() == UserRole.ADMIN) {
            System.out.println();
            AdminView adminView = new AdminView();
            adminView.showAdminMenu();
        }
    }
}
