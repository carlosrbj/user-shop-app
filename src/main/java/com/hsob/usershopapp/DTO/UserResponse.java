package com.hsob.usershopapp.DTO;

import com.hsob.usershopapp.model.user.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author carlos
 */

@Getter
@Setter
public class UserResponse implements Serializable {
    private String username;
    private String name;
    private Gender gender;
    private GenderIdentity genderIdentity;
    private String socialName;
    private DocumentType documentType;
    private String document;
    private String phone;
    private UserType userType;

    public static UserResponse toEntity(User user) {
        UserResponse response = new UserResponse();
        response.setName(user.getName());
        response.setUsername(user.getUsername());
        response.setGender(user.getGender());
        response.setGenderIdentity(user.getGenderIdentity());
        response.setSocialName(user.getSocialName());
        response.setDocumentType(user.getDocumentType());
        response.setDocument(user.getDocument());
        response.setPhone(user.getPhone());
        response.setUserType(user.getUserType());
        return response;
    }
}
