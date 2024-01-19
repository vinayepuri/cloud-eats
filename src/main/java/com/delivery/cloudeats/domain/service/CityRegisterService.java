package com.delivery.cloudeats.domain.service;

import com.delivery.cloudeats.domain.exception.CityNotFoundException;
import com.delivery.cloudeats.domain.model.City;
import com.delivery.cloudeats.domain.model.State;
import com.delivery.cloudeats.domain.exception.EntityInUseException;
import com.delivery.cloudeats.domain.repository.CityRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CityRegisterService {

    private CityRepository cityRepository;
    private StateRegisterService stateRegisterService;

    public CityRegisterService(CityRepository cityRepository, StateRegisterService stateRegisterService) {
        this.cityRepository = cityRepository;
        this.stateRegisterService = stateRegisterService;
    }

    public City add(City city) {
        Long stateId = city.getState().getId();
        State state = stateRegisterService.findOrThrow(stateId);

        city.setState(state);
        return cityRepository.save(city);
    }

    public void remove(Long id) {
        try {
            cityRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new CityNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format("City with id %d cannot be removed, it is in use.", id));
        }
    }

    public City findOrThrow(Long id) {
        return cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException(id));
    }
}
