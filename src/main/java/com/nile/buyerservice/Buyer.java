package com.nile.buyerservice;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

import java.util.UUID;

@Document
public class Buyer {
    @Id
    private UUID id;
    @Field
    private String name;
    @Field
    private String address;
    @Field
    private String contactInformation;
    @Field
    private String email;
    @Field
    private String country;
    @Field
    private String creditCard;

    public Buyer() {

    }

    public Buyer(UUID id, String name, String address, String contactInformation, String email, String country, String creditCard) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contactInformation = contactInformation;
        this.email = email;
        this.country = country;
        this.creditCard = creditCard;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }
}
