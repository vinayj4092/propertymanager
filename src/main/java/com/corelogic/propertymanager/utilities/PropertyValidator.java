package com.corelogic.propertymanager.utilities;

import com.corelogic.propertymanager.entities.Property;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * This class is used to validate the incoming data
 */
public class PropertyValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Property.class.equals(aClass);
    }

    @Override
    public void validate(Object input, Errors errors) {

        Property property = (Property) input;

        if (property.getAddress() == null)
            errors.reject("Address cannot be empty");
        else if (property.getPurchaseDate() == null)
            errors.reject("Purchased Date cannot be empty");
    }
}
