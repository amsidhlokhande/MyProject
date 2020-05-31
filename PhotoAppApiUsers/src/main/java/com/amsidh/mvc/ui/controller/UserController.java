package com.amsidh.mvc.ui.controller;

import com.amsidh.mvc.service.UserService;
import com.amsidh.mvc.service.model.UserDto;
import com.amsidh.mvc.ui.model.UserRequestModel;
import com.amsidh.mvc.ui.model.UserResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static com.amsidh.mvc.util.ObjectMapperUtil.map;
import static com.amsidh.mvc.util.ObjectMapperUtil.mapAll;
import static java.lang.String.format;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private Environment environment;

    @Autowired
    private UserService userService;

    @GetMapping("/health/check")
    public ResponseEntity<String> healthCheck() {
        log.info("Checking health of users/health/check API");
        return ok()
                .body(format("User Micro Service is up and running on server %s and port %s with jwt token expire time %s", environment.getProperty("ip.address.security.allow"), environment.getProperty("local.server.port"), environment.getProperty("jwt.token.expirationTime")));
    }

    @GetMapping(produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public ResponseEntity<List<UserResponseModel>> getAllUser() {
        log.info("Fetching all the users");
        return ok().body(mapAll(userService.getAllUsers(),UserResponseModel.class));
    }

    @GetMapping(value = "/{userId}", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public ResponseEntity<UserResponseModel> getUser(@PathVariable String userId) {
        log.info(format("getUser method is called with userId %s", userId));
        return ok().body(map(userService.getUser(userId),UserResponseModel.class));
    }

    @PostMapping(
            consumes = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE}
            , produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public ResponseEntity<UserResponseModel> createUser(@Valid @RequestBody UserRequestModel userRequestModel) {
        log.info(format("createUser method is called with user details %s", userRequestModel.toString()));
        UserDto userDto = userService.createUser(map(userRequestModel,UserDto.class));
        return ResponseEntity.status(CREATED)
                .body(map(userDto,UserResponseModel.class));
    }

    @PutMapping(value = "/{userId}",
            consumes = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE}
            , produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public ResponseEntity<UserResponseModel> updateUser(@PathVariable String userId, @Valid @RequestBody UserRequestModel userRequestModel) {
        log.info(format("updateUser method is called with userId %s", userId));
        UserDto userDto = userService.updateUser(userId, map(userRequestModel,UserDto.class));
        return ResponseEntity.status(ACCEPTED)
                .body(map(userDto,UserResponseModel.class));
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        log.info(format("delete method is called with userId %s", userId));
        userService.deleteUser(userId);
        return new ResponseEntity<Void>(NO_CONTENT);
    }

}
