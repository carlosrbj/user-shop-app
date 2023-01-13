package com.hsob.usershopapp.controller;

import com.hsob.usershopapp.DTO.UserRequest;
import com.hsob.usershopapp.DTO.UserResponse;
import com.hsob.usershopapp.model.user.Address;
import com.hsob.usershopapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author carlos
 */

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/saveUser")
    public ResponseEntity<HttpStatus> save(@RequestBody UserRequest userRequest, @RequestHeader String password, @RequestHeader String confirmPassword){
        userService.saveUser(userRequest, password, confirmPassword);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public  ResponseEntity<UserResponse> getUser(@RequestParam(required = false) String document, @RequestParam(required = false) String username){
        return ResponseEntity.ok(userService.getUser(document, username));
    }

    @GetMapping("/getAllUsers")
    public  ResponseEntity<PageImpl<UserResponse>> getAllUsers(@RequestParam int page, @RequestParam int size, @RequestParam(required = false) String name){
        return ResponseEntity.ok(userService.getAllUsers(page, size, name));
    }


    @PutMapping("/updateAddress")
    public ResponseEntity<HttpStatus> update(@RequestBody Address address, @RequestHeader String password, @RequestHeader String username){
        userService.updateAddress(address, password, username);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
