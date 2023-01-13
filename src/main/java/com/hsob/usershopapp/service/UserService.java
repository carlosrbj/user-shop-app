package com.hsob.usershopapp.service;

import com.hsob.usershopapp.DTO.UserRequest;
import com.hsob.usershopapp.DTO.UserResponse;
import com.hsob.usershopapp.Utils;
import com.hsob.usershopapp.model.user.Address;
import com.hsob.usershopapp.model.user.User;
import com.hsob.usershopapp.repository.ShopAppDAO;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

/**
 * @author carlos
 */

@Service
public class UserService extends ShopAppDAO {

    public void saveUser(UserRequest userRequest, String password, String confirmPassword) {
        try {
            Utils.validateNewPassword(password, confirmPassword);
            checkDocument(userRequest);
            checkUsername(userRequest);
            User user = new User();
            user.toEntity(userRequest);
            validateUserEntity(user, password);
            shopAppDB.save(user);
        } catch (Exception exception){
            logger.info(exception.getMessage());
            throw new IllegalArgumentException(exception.getMessage());
        }
    }

    private void validateUserEntity(User user, String password) {
        if (!user.getSocialName().isEmpty()) user.setName(user.getSocialName());
        String salt = Utils.generateSalt();
        String digest = Utils.generateDigest(password, salt);
        user.setSalt(salt);
        user.setDigest(digest);
    }

    private void checkDocument(UserRequest userRequest) {
        User user = shopAppDB.findOne(new Query(Criteria.where("document").is(userRequest.getDocument())), User.class);
        if (user != null){
            String msg = "The document informed is already registered";
            logger.info(msg);
            throw new IllegalArgumentException(msg);
        }
    }

    private void checkUsername(UserRequest userRequest) {
        if (userRequest.getUsername().isEmpty()){
            userRequest.setUsername(userRequest.getDocument());
        } else {
            User user = shopAppDB.findOne(new Query(Criteria.where("username").is(userRequest.getUsername())), User.class);
            if (user != null){
                String msg = "The username informed is already registered";
                logger.info(msg);
                throw new IllegalArgumentException(msg);
            }
        }
    }

    public void updateAddress(Address address, String password, String username) {
        try{
            User user = shopAppDB.findOne(new Query(Criteria.where("username").is(username)), User.class);
            if (user == null) throw new IllegalArgumentException("User not found");
            boolean validPassword = Utils.validatePassword(password, user);
            if (validPassword){
                Update update = new Update();
                update.set("address", address);
                shopAppDB.upsert(new Query(Criteria.where("username").is(username)), update, User.class);
                logger.info("Address updated");
            } else{
                logger.info("invalid password");
                throw new IllegalArgumentException("invalid password");
            }
        } catch (Exception e){
            logger.info(e.getMessage());
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public UserResponse getUser(String document, String username) {
        User user = null;
        UserResponse userResponse = new UserResponse();
        if (document != null) user = shopAppDB.findOne(new Query(Criteria.where("document").is(document)), User.class);
        if (username != null) user = shopAppDB.findOne(new Query(Criteria.where("username").is(username)), User.class);
        if (user != null) {
            return userResponse.toEntity(user);
        } else {
            String msg = "User not found";
            logger.info(msg);
            throw new IllegalArgumentException(msg);
        }
    }

    public PageImpl<UserResponse> getAllUsers(int page, int size, String name) {
        Query query = new Query();
        List<User> users = Collections.emptyList();
        List<UserResponse> userResponses = new java.util.ArrayList<>(Collections.emptyList());

        if (!name.isEmpty()) query.addCriteria(Criteria.where("name").regex(name, "i"));

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "name");
        long count = shopAppDB.count(query, User.class);
        query.with(pageable);

        if (count > 0) users = shopAppDB.find(query, User.class);
        for (User user : users) {
            UserResponse userResponse = new UserResponse();
            userResponses.add(userResponse.toEntity(user));
        }

        return new PageImpl<>(userResponses, pageable, count);
    }
}
