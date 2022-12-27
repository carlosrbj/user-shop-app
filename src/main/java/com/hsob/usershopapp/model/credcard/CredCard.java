package com.hsob.usershopapp.model.credcard;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

/**
 * @author carlos
 */

@Document(collection = "cred-card")
@Getter
@Setter
public class CredCard {
    private @MongoId ObjectId id;
    private String userId;
    private Date registrationDate;
    private String cardNumber;
    private String expirationDate;
    private String CVV;
}
