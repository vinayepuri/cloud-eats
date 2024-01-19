package com.delivery.cloudeats.infrastructure.repository;

import com.delivery.cloudeats.domain.model.Restaurant;
import com.delivery.cloudeats.domain.repository.RestaurantRepository;
import com.delivery.cloudeats.domain.repository.RestaurantRepositoryQueries;
import com.delivery.cloudeats.infrastructure.repository.spec.RestaurantSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired @Lazy
    private RestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> find(String name, BigDecimal initialFee, BigDecimal finalFee) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);
        Root<Restaurant> root = criteria.from(Restaurant.class);

        var predicates = new ArrayList<Predicate>();

        if (name != null && !"".equals(name.trim())) {
            predicates.add(builder.like(root.get("name"), "%" + name + "%"));
        }

        if (initialFee != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("deliveryFee"), initialFee));
        }

        if (finalFee != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("deliveryFee"), finalFee));
        }

        criteria.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Restaurant> query = entityManager.createQuery(criteria);

        return query.getResultList();
    }

    @Override
    public List<Restaurant> findFreeDelivery(String name) {
        return restaurantRepository.findAll(RestaurantSpecs.withFreeDelivery().and(RestaurantSpecs.withName(name)));
    }
}
