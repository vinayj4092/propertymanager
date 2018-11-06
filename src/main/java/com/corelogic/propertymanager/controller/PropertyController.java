package com.corelogic.propertymanager.controller;

import com.corelogic.propertymanager.entities.Property;
import com.corelogic.propertymanager.service.PropertyService;
import com.corelogic.propertymanager.utilities.JsonResponseBody;
import com.corelogic.propertymanager.utilities.PropertyNotFoundExcception;
import com.corelogic.propertymanager.utilities.PropertyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * This class exposes the end points of the PropertyManager application.
 */
@RestController
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @Value("${propertymanager.pagination.size}")
    private int paginationSize;

    /**
     * Shows all the stored properties in the DB
     *
     * @param pageNum the Page number
     * @return list of Properties
     */
    @RequestMapping("/showProperties")
    public ResponseEntity<Object> showProperties(@RequestParam("pageNum") int pageNum){

        System.out.println (this.paginationSize);
        try {
            Page<Property> propertiesPage = propertyService.getProperties(pageNum, this.paginationSize);
            if(propertiesPage.getTotalElements() == 0)
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new JsonResponseBody(HttpStatus.NO_CONTENT.value()));
            else
                return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), propertiesPage));
        }catch(Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JsonResponseBody(HttpStatus.INTERNAL_SERVER_ERROR.value(), "There was an internal error " + exception.toString()));
        }
    }

    /**
     * Method to add a new property
     *
     * @param property the <code>Property</code> to be added
     * @param result <code>BindingResult</code> object
     * @return the newly added Property
     */
    @RequestMapping(value="/newProperty", method=POST)
    public ResponseEntity<JsonResponseBody> addNewProperty(@RequestBody Property property, BindingResult result){

        // used to validate the property data
        PropertyValidator validator = new PropertyValidator();
        validator.validate(property, result);

        // Check for errors and return an appropriate response
        if(result.hasErrors()){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponseBody(HttpStatus.BAD_REQUEST.value(), "Data not valid: " + result.toString()));
        }

        try {
            return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.CREATED.value(), propertyService.addProperty(property)));
        }catch(Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JsonResponseBody(HttpStatus.INTERNAL_SERVER_ERROR.value(), "There was an internal error " + exception.toString()));
        }
    }

    /**
     * Method to update a <code>Property</code>
     *
     * @param propertyId
     * @param property
     * @param result
     * @return the updated Property
     */
    @RequestMapping(value="/updateProperty/{propertyId}", method=PUT)
    public ResponseEntity<JsonResponseBody> updateProperty(@PathVariable("propertyId") long propertyId, @RequestBody Property property, BindingResult result){

        // used to validate the property data
        PropertyValidator validator = new PropertyValidator();
        validator.validate(property, result);

        // Check for errors
        if(result.hasErrors()){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponseBody(HttpStatus.BAD_REQUEST.value(), "Data not valid: " + result.toString()));
        }

        try {
            return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), propertyService.updateProperty(property, propertyId)));
        }
        catch(PropertyNotFoundExcception propertyNotFoundExcception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new JsonResponseBody(HttpStatus.NOT_FOUND.value(), "Property not found " + propertyNotFoundExcception.toString()));
        }
        catch(Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JsonResponseBody(HttpStatus.INTERNAL_SERVER_ERROR.value(), "There was an internal error " + exception.toString()));
        }
    }
}
