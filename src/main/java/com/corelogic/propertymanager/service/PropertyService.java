package com.corelogic.propertymanager.service;

import com.corelogic.propertymanager.entities.Property;
import com.corelogic.propertymanager.utilities.PropertyNotFoundExcception;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * This interface represents the services to be exposed by PropertyService implementation, through which the Property details can be fetched or added.
 */
public interface PropertyService {

    /**
     * A method to add a new <code>Property</code>
     *
     * @param property a <code>Property</code> object
     * @return
     */
    public Property addProperty(Property property);

    /**
     * A method to update a <code>Property</code>
     *
     * @param property a <code>Property</code> object
     * @return
     */
    public Property updateProperty(Property property, Long propertyId) throws PropertyNotFoundExcception;

    /**
     * A method that returns a List of Properties
     * @param pageNum the page number
     * @param pageSize the pagination size
     * @return a <code>List</code> of Properties
     */
    public Page<Property> getProperties(int pageNum, int pageSize);
}
