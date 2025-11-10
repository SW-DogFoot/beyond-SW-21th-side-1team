package com.mini.timedeal.config;

import com.mini.timedeal.domain.promotion.mapper.PromotionMapper;
import com.mini.timedeal.domain.promotion.service.PromotionService;
import com.mini.timedeal.domain.promotion.storage.PromotionRepository;
import org.apache.ibatis.session.SqlSession;

public final class AppConfig {

    public AppConfig() {

    }

    public void initRepository() {
        AppContext context = AppContext.getInstance();

        // Repository 등록
        context.registerBean(PromotionRepository.class);

        // Service 등록
        context.registerBean(PromotionService.class, PromotionMapper.class);
    }

    public void initMyBatis() {
        AppContext context = AppContext.getInstance();
        SqlSession sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
        
        // Mapper 등록
        PromotionMapper promotionMapper = sqlSession.getMapper(PromotionMapper.class);
        context.registerBeanObject(promotionMapper);
        
        // Service 등록
        context.registerBean(PromotionService.class, PromotionMapper.class);
    }
}
