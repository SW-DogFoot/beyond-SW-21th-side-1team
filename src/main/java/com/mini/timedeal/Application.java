package com.mini.timedeal;

import com.mini.timedeal.domain.promotion.scheduler.PromotionScheduler;
import com.mini.timedeal.config.AppConfig;
import com.mini.timedeal.view.LoginView;

public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        appConfig.initRepository();

        PromotionScheduler scheduler = new PromotionScheduler();
        scheduler.start();

        LoginView loginView = new LoginView();
        loginView.login();

        scheduler.shutdown();
    }
}
