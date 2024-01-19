package com.delivery.cloudeats.api.controller;

import com.delivery.cloudeats.domain.model.Kitchen;
import com.delivery.cloudeats.domain.repository.KitchenRepository;
import com.delivery.cloudeats.domain.service.KitchenRegisterService;
import com.delivery.cloudeats.api.model.KitchensXmlWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/kitchens")
public class KitchenController {

    private KitchenRepository kitchenRepository;
    private KitchenRegisterService kitchenRegisterService;

    public KitchenController(KitchenRepository kitchenRepository, KitchenRegisterService kitchenRegisterService) {
        this.kitchenRepository = kitchenRepository;
        this.kitchenRegisterService = kitchenRegisterService;
    }

    @GetMapping
    public List<Kitchen> list() {
        return kitchenRepository.findAll();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public KitchensXmlWrapper listXml() {
        return new KitchensXmlWrapper(kitchenRepository.findAll());
    }

//    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public Kitchen findById(@PathVariable Long id) {
        return kitchenRegisterService.findOrThrow(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Kitchen add(@RequestBody @Valid Kitchen kitchen) {
        return kitchenRegisterService.add(kitchen);
    }

    @PutMapping("/{id}")
    public Kitchen update(@PathVariable Long id, @RequestBody @Valid Kitchen kitchen) {
        Kitchen kitchenAux = kitchenRegisterService.findOrThrow(id);

        BeanUtils.copyProperties(kitchen, kitchenAux, "id");

        return kitchenRegisterService.add(kitchenAux);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        kitchenRegisterService.remove(id);
    }
}
