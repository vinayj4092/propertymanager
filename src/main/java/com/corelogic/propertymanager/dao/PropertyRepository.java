package com.corelogic.propertymanager.dao;

import com.corelogic.propertymanager.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This is Repository and uses the Spring JPA Repository to provide the frequently used operations
 */
public interface PropertyRepository extends JpaRepository<Property, Long> {
}
