package com.mini.timedeal.domain.user.storage;

import com.mini.timedeal.domain.Product;
import com.mini.timedeal.domain.promotion.model.Promotion;
import com.mini.timedeal.domain.user.dto.UserProductDTO;
import com.mini.timedeal.domain.user.mapper.UserProductMapper;
import com.mini.timedeal.domain.user.model.User;
import com.mini.timedeal.domain.user.model.UserProduct;
import com.mini.timedeal.enums.PromotionStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserProductRepository implements UserProductMapper {

//    private final Map<Long, User> users = new HashMap<>();
    private final Map<Long, Promotion> promotions = new HashMap<>();
    private final Map<Long, Product> products = new HashMap<>();
    private final List<UserProduct> userProducts = new ArrayList<>();

    public UserProductRepository() {

        products.put(100L, new Product(100L, "기타", "일렉기타", 10000));
        promotions.put(1L, new Promotion(1L, 100L, PromotionStatus.ACTIVE, 10, 10, 10, LocalDateTime.now(), LocalDateTime.now().plusMinutes(3)));
//        userProducts.add(new UserProduct(1L, 1L, 100L, LocalDateTime.now()));
    }

    @Override
    public void saveUserProducts(UserProduct userProduct) {

        userProducts.add(userProduct);
    }

    @Override
    public List<UserProductDTO> getUserProducts(Long userId) {

        List<UserProductDTO> productList = new ArrayList<>();

        for (UserProduct up : userProducts) {
            if(!up.getUserId().equals(userId)) continue;

            Product product = products.get(up.getProductId());
            if(product == null) continue;

            Promotion promotion = promotions.values().stream()
                    .filter(pr -> pr.getProductId().equals(up.getProductId()))
                    .findFirst()
                    .orElse(null);

            Long promotionId = (promotion != null) ? promotion.getId() : null;

            String purchasedAt = up.getPurchasedAt().toString();

            productList.add(new UserProductDTO(
                    promotionId,
                    product.getName(),
                    product.getPrice(),
                    purchasedAt
            ));
        }

        return productList;
    }
}
