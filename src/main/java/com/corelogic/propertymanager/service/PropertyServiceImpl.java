package com.corelogic.propertymanager.service;

import com.corelogic.propertymanager.dao.PropertyRepository;
import com.corelogic.propertymanager.entities.Property;
import com.corelogic.propertymanager.utilities.PropertyNotFoundExcception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class represents the PropertyService through which the Property details can be fetched or added.
 */
@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    /**
     * A method to add a new Property
     *
     * @param property a Property object
     * @return
     */
    @Override
    public Property addProperty(Property property) {
        return propertyRepository.save(property);
    }

    /**
     * A method to update a <code>Property</code>
     *
     * @param property a <code>Property</code> object
     * @return
     */
    @Override
    public Property updateProperty(Property property, Long propertyId) throws PropertyNotFoundExcception {
        // First fetch the Property from the DB
        Property propertyFromDB = propertyRepository.findOne(propertyId);

        // Check if property exists, if not throw PropertyNotFoundException
        if (propertyFromDB == null)
            throw new PropertyNotFoundExcception("There is no property corresponding to the property ID " + propertyId);

        property.setPropertyId(propertyId);
        return propertyRepository.save(property);
    }

     /**
     * A method that returns a List of Properties
     * @param pageNum the page number
     * @param pageSize the pagination size
     * @return a <code>List</code> of Properties with the pagination details
     */
    @Override
    public Page<Property> getProperties(int pageNum, int pageSize) {

        // PageRequest object is created to handle the Pagination logic
        PageRequest pageRequest = new PageRequest(pageNum, pageSize, Sort.Direction.ASC, "PurchaseValue");
        return propertyRepository.findAll(pageRequest);
    }
}
