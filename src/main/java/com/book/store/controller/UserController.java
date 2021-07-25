package com.book.store.controller;

import com.book.store.controller.model.AddressRequest;
import com.book.store.dto.Converter;
import com.book.store.dto.UserDetails;
import com.book.store.model.User;
import com.book.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/me")
    public ResponseEntity<UserDetails> getUserDetails(Principal principal) {
        User user = userService.getUserByEmail(principal.getName());

        return new ResponseEntity<>(Converter.userToUserDetails(user), HttpStatus.OK);
    }

    @PostMapping("/user/address")
    public ResponseEntity<UserDetails> saveAddress(Principal principal, @RequestBody @Valid AddressRequest addressRequest) {
        User user = userService.addAddress(principal.getName(), addressRequest);
        return new ResponseEntity<>(Converter.userToUserDetails(user), HttpStatus.OK);
    }
}
