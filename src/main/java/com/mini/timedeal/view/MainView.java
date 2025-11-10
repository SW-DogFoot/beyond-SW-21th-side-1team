package com.mini.timedeal.view;

import com.mini.timedeal.enums.PromotionStatus;

import java.util.Scanner;

public class MainView {
    private final PromotionView promotionView = new PromotionView();

    public MainView() {

    }

    public void show() {

        Scanner sc = new Scanner(System.in);

        do {

            System.out.println("아무거나");
            sc.nextLine();

            promotionView.showPromotions(PromotionStatus.ACTIVE);

        } while (true);
    }
}
