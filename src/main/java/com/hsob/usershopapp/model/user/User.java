package com.hsob.usershopapp.model.user;

import com.hsob.usershopapp.DTO.UserRequest;
import com.hsob.usershopapp.model.credcard.CredCard;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
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

    public static User toEntity(UserRequest userRequest){
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setName(userRequest.getName());
        user.setGender(userRequest.getGender());
        user.setGenderIdentity(userRequest.getGenderIdentity());
        user.setSocialName(userRequest.getSocialName());
        user.setDocumentType(userRequest.getDocumentType());
        user.setDocument(userRequest.getDocument());
        user.setPhone(userRequest.getPhone());
        user.setAddresses(Collections.singletonList(userRequest.getAddress()));
        user.setUserType(userRequest.getUserType());
        user.setCredCards(Collections.singletonList(userRequest.getCredCard()));
        return user;
    }

}
