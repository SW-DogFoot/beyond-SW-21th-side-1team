package com.mini.timedeal.view;

import com.mini.timedeal.domain.user.model.User;
import com.mini.timedeal.enums.PromotionStatus;
import com.mini.timedeal.enums.UserRole;

import javax.management.relation.Role;
import java.util.Scanner;

public class MainView {
//    private final LoginView loginView = new LoginView();
    private final AdminView adminView = new AdminView();
    private final UserView userView = new UserView();

    public MainView() {

    }

    public void show() {
        User user = new User();
        do {
            // 로그인 페이지
//            user = loginView.login();

            // 유저또는 관리자 페이지 보여주기
            if (user.getRole() == UserRole.ADMIN) {
                adminView.showAdminMenu();
            } else {
                userView.userMenu();
            }
        } while (user == null);
    }
}
