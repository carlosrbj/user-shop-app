package com.hsob.usershopapp.DTO;

import com.hsob.usershopapp.model.user.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

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

    public UserResponse toEntity(User user) {
        BeanUtils.copyProperties(user, this);
        return this;
    }
}
