package com.corelogic.propertymanager.entities;

import javax.persistence.*;

/**
 * This class is an entity and represents the Property table in the Database
 */
@Entity
@Table(name = "Property")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="PropertyID")
    private Long propertyId;

    @Column(name="Address")
    private String address;

    @Column(name="PurchaseDate")
    private String purchaseDate;

    @Column(name="PurchaseValue")
    private Double purchaseValue;

    public Property() {
    }

    public Property(String address, String purchaseDate, Double purchaseValue) {
        this.address = address;
        this.purchaseDate = purchaseDate;
        this.purchaseValue = purchaseValue;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Double getPurchaseValue() {
        return purchaseValue;
    }

    public void setPurchaseValue(Double purchaseValue) {
        this.purchaseValue = purchaseValue;
    }
}
