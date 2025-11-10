package com.mini.timedeal.view;

import java.util.Scanner;

public class UserView {

    Scanner sc = new Scanner(System.in);

    public void login() {

        System.out.println("[로그인]");
        System.out.print("username 입력 : ");
        String username = sc.next();
        System.out.print("password 입력 : ");
        String password = sc.next();

    }

    public void userMenu() {

        while (true) {
            System.out.println("""
                    [일반 유저 메뉴]
                    1. 핫딜 상품 검색
                    2. 핫딜 상품 구매
                    3. 구매한 상품 검색
                    0. 나가기
                    """);
            System.out.print("실행할 메뉴 번호를 입력하세요 : ");
            int menu = sc.nextInt();

            switch (menu) {
                case 1: break;
                case 2: break;
                case 3: break;
                case 0: System.exit(0);
                default: System.out.println("다시 입력해주세요."); break;
            }
        }
    }

    public void adminMenu() {

    }
}
