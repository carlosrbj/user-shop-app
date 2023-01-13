package com.hsob.usershopapp.model.user;

import com.hsob.usershopapp.DTO.UserRequest;
import com.hsob.usershopapp.model.credcard.CredCard;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author carlos
 */

@Document(collection = "user")
@Getter
@Setter
public class User implements Serializable {
    private String username;
    private String name;
    private Gender gender;
    private GenderIdentity genderIdentity;
    private String socialName;
    private DocumentType documentType;
    private String document;
    private String phone;
    private String salt;
    private String digest;
    private List<Address> addresses;
    private UserType userType;
    private List<CredCard> credCards;

    public User toEntity(UserRequest userRequest){
        BeanUtils.copyProperties(userRequest, this);
        List<Address> addressList = new ArrayList<>();
        List<CredCard> credCardList = new ArrayList<>();
        addressList.add(userRequest.getAddress());
        credCardList.add(userRequest.getCredCard());
        this.setAddresses(addressList);
        this.setCredCards(credCardList);
        return this;
    }

}
