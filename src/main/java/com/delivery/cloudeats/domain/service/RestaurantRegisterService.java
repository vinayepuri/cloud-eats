package com.delivery.cloudeats.domain.service;

import com.delivery.cloudeats.domain.model.Restaurant;
import com.delivery.cloudeats.domain.exception.EntityInUseException;
import com.delivery.cloudeats.domain.exception.RestaurantNotFoundException;
import com.delivery.cloudeats.domain.model.Kitchen;
import com.delivery.cloudeats.domain.repository.RestaurantRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class RestaurantRegisterService {

    public static final String RESTAURANT_IN_USE = "Restaurant with id %d cannot be removed, it is in use.";

    private RestaurantRepository restaurantRepository;
    private KitchenRegisterService kitchenRegisterService;

    public RestaurantRegisterService(RestaurantRepository restaurantRepository, KitchenRegisterService kitchenRegisterService) {
        this.restaurantRepository = restaurantRepository;
        this.kitchenRegisterService = kitchenRegisterService;
    }

    public Restaurant add(Restaurant restaurant) {
        Long kitchenId = restaurant.getKitchen().getId();
        Kitchen kitchen = kitchenRegisterService.findOrThrow(kitchenId);

        restaurant.setKitchen(kitchen);

        return restaurantRepository.save(restaurant);
    }

    public void remove(Long id) {
        try {
            restaurantRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RestaurantNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format(RESTAURANT_IN_USE, id));
        }
    }

    public Restaurant findOrThrow(Long id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new RestaurantNotFoundException(id));
    }
}
