package com.delivery.cloudeats.domain.service;

import com.delivery.cloudeats.domain.exception.EntityInUseException;
import com.delivery.cloudeats.domain.exception.KitchenNotFoundException;
import com.delivery.cloudeats.domain.model.Kitchen;
import com.delivery.cloudeats.domain.repository.KitchenRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class KitchenRegisterService {
    public static final String KITCHEN_IN_USE = "Kitchen with id %d cannot be removed, it is in use.";

    private KitchenRepository kitchenRepository;

    public KitchenRegisterService(KitchenRepository kitchenRepository) {
        this.kitchenRepository = kitchenRepository;
    }

    public Kitchen add(Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }

    public void remove(Long id) {
        try {
            kitchenRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new KitchenNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format(KITCHEN_IN_USE, id));
        }
    }

    public Kitchen findOrThrow(Long id) {
        return kitchenRepository.findById(id).orElseThrow(() -> new KitchenNotFoundException(id));
    }
}
