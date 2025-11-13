package com.mini.timedeal.domain.user.service;

import com.mini.timedeal.domain.product.mapper.ProductMapper;
import com.mini.timedeal.domain.product.model.Product;
import com.mini.timedeal.domain.promotion.mapper.PromotionMapper;
import com.mini.timedeal.domain.promotion.model.Promotion;
import com.mini.timedeal.domain.user.dto.UserProductDTO;
import com.mini.timedeal.domain.user.mapper.UserProductMapper;
import com.mini.timedeal.domain.user.model.UserProduct;

import java.util.ArrayList;
import java.util.List;

public class UserProductService {

    private final UserProductMapper userProductMapper;
    private final ProductMapper productMapper;
    private final PromotionMapper promotionMapper;

    public UserProductService(UserProductMapper userProductMapper, ProductMapper productMapper, PromotionMapper promotionMapper) {
        this.userProductMapper = userProductMapper;
        this.productMapper = productMapper;
        this.promotionMapper = promotionMapper;
    }

    /*
    * 유저가 구매한 상품들 불러오기
    * */
    public List<UserProductDTO> userProductList(Long userId) {
        List<UserProduct> userProducts = userProductMapper.getUserProducts(userId);
        List<UserProductDTO> result = new ArrayList<>();

        for (UserProduct up : userProducts) {

            // 1) 상품 정보 조회
            Product product = productMapper.findById(up.getProductId());
            if (product == null) continue;

            // 2) 프로모션 정보 조회 (없을 수도 있으니 Optional 느낌으로)
            Promotion promotion = promotionMapper.findById(up.getProductId());

            Long promotionId = (promotion != null) ? promotion.getId() : null;

            String purchasedAt = up.getPurchasedAt().toString();

            result.add(new UserProductDTO(
                    promotionId,
                    product.getName(),
                    product.getPrice(),
                    purchasedAt
            ));
        }

        return result;
    }
}
