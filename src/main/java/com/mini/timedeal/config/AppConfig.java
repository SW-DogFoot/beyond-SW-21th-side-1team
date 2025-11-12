package com.mini.timedeal.config;

import com.mini.timedeal.domain.admin.mapper.AdminMapper;
import com.mini.timedeal.domain.admin.service.AdminService;
import com.mini.timedeal.domain.admin.storage.AdminRepository;
import com.mini.timedeal.domain.promotion.mapper.PromotionMapper;
import com.mini.timedeal.domain.promotion.service.PromotionService;
import com.mini.timedeal.domain.promotion.storage.PromotionRepository;
import com.mini.timedeal.domain.user.mapper.UserMapper;
import com.mini.timedeal.domain.user.mapper.UserProductMapper;
import com.mini.timedeal.domain.user.service.UserProductService;
import com.mini.timedeal.domain.user.service.UserService;
import com.mini.timedeal.domain.user.storage.UserProductRepository;
import com.mini.timedeal.domain.user.storage.UserRepository;
import org.apache.ibatis.session.SqlSession;

public final class AppConfig {

    public AppConfig() {

    }

    public void initRepository() {
        AppContext context = AppContext.getInstance();

        // Repository 등록
        context.registerBean(PromotionRepository.class);
        context.registerBean(AdminRepository.class);
        context.registerBean(UserRepository.class);
        context.registerBean(UserProductRepository.class);

        // Service 등록
        context.registerBean(PromotionService.class, PromotionMapper.class);
        context.registerBean(AdminService.class, AdminMapper.class);
        context.registerBean(UserService.class, UserMapper.class);
        context.registerBean(UserProductService.class, UserProductMapper.class);
    }

    public void initMyBatis() {
        AppContext context = AppContext.getInstance();
        SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
        
        // Mapper 등록
        context.registerBeanObject(sqlSession.getMapper(PromotionMapper.class));
        context.registerBeanObject(sqlSession.getMapper(AdminMapper.class));
        context.registerBeanObject(sqlSession.getMapper(UserMapper.class));
        context.registerBeanObject(sqlSession.getMapper(UserProductMapper.class));
        
        // Service 등록
        context.registerBean(PromotionService.class, PromotionMapper.class);
        context.registerBean(AdminService.class, AdminMapper.class);
        context.registerBean(UserService.class, UserMapper.class);
        context.registerBean(UserProductService.class, UserProductMapper.class);
    }
}
