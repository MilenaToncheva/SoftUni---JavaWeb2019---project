package com.org.pizza.service;

import com.org.pizza.domain.entities.pizza.Pizza;
import com.org.pizza.domain.models.service.PizzaServiceModel;
import com.org.pizza.repository.PizzaRepository;
import com.org.pizza.validation.errors.PizzaAddException;
import com.org.pizza.validation.errors.PizzaAlreadyExistException;
import com.org.pizza.validation.pizzaValidation.PizzaValidationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.org.pizza.constant.commonMessages.CommonMessages.INVALID_DATA_INPUT;
import static com.org.pizza.constant.errorMessages.pizza.PizzaErrorMessages.PIZZA_ALREADY_EXIST;

@Service
public class PizzaServiceImpl implements PizzaService {

    private final PizzaRepository pizzaRepository;
    private final PizzaValidationService pizzaValidationService;
    private final ModelMapper modelMapper;

    @Autowired
    public PizzaServiceImpl(PizzaRepository pizzaRepository, PizzaValidationService pizzaValidationService, ModelMapper modelMapper) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaValidationService = pizzaValidationService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addNewPizza(PizzaServiceModel pizzaServiceModel) {

        Pizza pizza = this.pizzaRepository.findByName(pizzaServiceModel.getName())
                .orElse(null);

        if (pizza != null) {
            throw new PizzaAlreadyExistException(PIZZA_ALREADY_EXIST);
        }

        if (this.pizzaValidationService.isValid(pizzaServiceModel)) {
            throw new PizzaAddException(INVALID_DATA_INPUT);
        }
        //TODO: check sets!!
        pizza = this.modelMapper.map(pizzaServiceModel, Pizza.class);
        this.pizzaRepository.save(pizza);
    }
}
