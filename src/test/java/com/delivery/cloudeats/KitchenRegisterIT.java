package com.delivery.cloudeats;

import com.delivery.cloudeats.domain.exception.EntityInUseException;
import com.delivery.cloudeats.domain.exception.KitchenNotFoundException;
import com.delivery.cloudeats.domain.model.Kitchen;
import com.delivery.cloudeats.domain.service.KitchenRegisterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KitchenRegisterIT {

    @Autowired
    private KitchenRegisterService kitchenRegisterService;
    @Test
    public void kitchenRegisterWithSuccess() {
        Kitchen newKitchen = new Kitchen();
        newKitchen.setName("Chinese");

        newKitchen = kitchenRegisterService.add(newKitchen);

        assertThat(newKitchen).isNotNull();
        assertThat(newKitchen.getId()).isNotNull();
    }

    @Test(expected = ConstraintViolationException.class)
    public void kitchenRegisterWithoutName() {
        Kitchen newKitchen = new Kitchen();
        newKitchen.setName(null);

        newKitchen = kitchenRegisterService.add(newKitchen);

    }

    @Test(expected = EntityInUseException.class)
    public void shouldFail_WhenRemoveKitchenInUse() {
        Long id = 1L;

        kitchenRegisterService.remove(id);

    }

    @Test(expected = KitchenNotFoundException.class)
    public void shouldFail_WhenRemoveKitchenNotFound() {
        Long id = 9999L;

        kitchenRegisterService.remove(id);

    }

}
