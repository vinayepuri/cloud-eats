package com.delivery.cloudeats.domain.repository;

import com.delivery.cloudeats.domain.model.Restaurant;

import java.math.BigDecimal;
import java.util.List;

public interface RestaurantRepositoryQueries {
    List<Restaurant> find(String name, BigDecimal initialFee, BigDecimal finalFee);
    List<Restaurant> findFreeDelivery(String name);
}
