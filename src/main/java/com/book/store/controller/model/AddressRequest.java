package com.book.store.controller.model;

import lombok.Data;

@Data
public class AddressRequest {

    private Long id;
    private String city;
    private String zipCode;
    private String street;
    private String buildingNumber;
    private String user_email;

}
