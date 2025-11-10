package com.mini.timedeal.view;

import com.mini.timedeal.enums.PromotionStatus;

import java.util.Scanner;

public class MainView {
    private final AdminView adminView = new AdminView();

    public MainView() {

    }

    public void show() {

        Scanner sc = new Scanner(System.in);

        do {

            adminView.showAdminMenu();

        } while (true);
    }
}
