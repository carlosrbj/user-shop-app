package com.hsob.usershopapp.model.user;

import lombok.Getter;
import lombok.Setter;

/**
 * @author carlos
 */

@Getter
@Setter
public class Address {
    public String city;
    public String state;
    public String country;
    public String zipcode;
    public String complement;
    public String street;
    public String number;
}
