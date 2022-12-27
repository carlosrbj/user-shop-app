package com.hsob.usershopapp.DTO;

import com.hsob.usershopapp.model.credcard.CredCard;
import com.hsob.usershopapp.model.user.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author carlos
 */

@Getter
@Setter
public class UserRequest implements Serializable {
    private String username;
    private String name;
    private Gender gender;
    private GenderIdentity genderIdentity;
    private String socialName;
    private DocumentType documentType;
    private String document;
    private String phone;
    private Address address;
    private UserType userType;
    private CredCard credCard;
}
