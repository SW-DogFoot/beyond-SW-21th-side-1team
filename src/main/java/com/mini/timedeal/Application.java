package com.mini.timedeal;

import com.mini.timedeal.domain.promotion.scheduler.PromotionScheduler;
import com.mini.timedeal.config.AppConfig;
import com.mini.timedeal.view.LoginView;
import com.mini.timedeal.view.MainView;
import com.mini.timedeal.view.UserView;

public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        appConfig.initRepository();

        PromotionScheduler scheduler = new PromotionScheduler();
        scheduler.start();

//        MainView view = new MainView();
//        view.show();
        LoginView loginView = new LoginView();
        loginView.login();

        scheduler.shutdown();
    }
}
