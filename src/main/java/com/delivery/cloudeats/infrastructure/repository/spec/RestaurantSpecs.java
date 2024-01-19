package com.delivery.cloudeats.infrastructure.repository.spec;

import com.delivery.cloudeats.domain.model.Restaurant;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestaurantSpecs {
    public static Specification<Restaurant> withFreeDelivery() {
        return (root, criteriaQuery, builder) -> builder.equal(root.get("deliveryFee"), BigDecimal.ZERO);
    }

    public static Specification<Restaurant> withName(String name) {
        return (root, criteriaQuery, builder) -> builder.like(root.get("name"), "%" + name + "%");
    }
}
