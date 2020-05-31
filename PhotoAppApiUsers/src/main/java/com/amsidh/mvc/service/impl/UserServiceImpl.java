package com.amsidh.mvc.service.impl;

import com.amsidh.mvc.repository.UserRepository;
import com.amsidh.mvc.repository.entity.UserEntity;
import com.amsidh.mvc.service.UserService;
import com.amsidh.mvc.service.model.UserDto;
import com.amsidh.mvc.ui.exception.DuplicateUserException;
import com.amsidh.mvc.ui.exception.NoDataFoundException;
import com.amsidh.mvc.ui.exception.UserNotFoundException;
import com.amsidh.mvc.util.UuidUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.amsidh.mvc.util.ObjectMapperUtil.map;
import static com.amsidh.mvc.util.ObjectMapperUtil.mapAll;
import static java.lang.String.format;
import static java.util.Optional.of;
import static java.util.Optional.ofNullable;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired(required = true)
    private UserRepository userRepository;


    @Autowired(required = true)
    private UuidUtil uuidUtil;

    @Autowired(required = true)
    PasswordEncoder passwordEncoder;

    public UserServiceImpl() {
        log.info("Loading UserServiceImpl!!!!");
    }

    @Override
    public UserDto getUser(String userId) {
        log.info(format("getUser of class UserServiceImpl with userId %s", userId));
        Optional<UserEntity> userEntity = ofNullable(userRepository.findByUserId(userId)).orElseThrow(() -> new UserNotFoundException(userId));
        return map(userEntity.get(), UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        log.info("getAllUsers of class UserServiceImpl called");
        List<UserEntity> entities = userRepository.findAll();
        if (entities.isEmpty()) {
            throw new NoDataFoundException();
        }
        return mapAll(entities, UserDto.class);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("createUser of class UserServiceImpl");
        of(userRepository.findByEmailId(userDto.getEmailId())).ifPresent(userEntity -> {
            throw new DuplicateUserException("EmailId", userEntity.get().getEmailId());
        });
        UserEntity userEntity = map(userDto, UserEntity.class);
        userEntity.setEncryptedPassword(passwordEncoder.encode(userDto.getPassword()));
        userEntity.setUserId(uuidUtil.getNextUuid());
        UserEntity savedEntity = userRepository.save(userEntity);
        return map(savedEntity, UserDto.class);
    }

    @Override
    public UserDto updateUser(String userId, UserDto userDto) {
        log.info("updateUser of class UserServiceImpl");
        UserEntity updateUserEntity = ofNullable(userRepository.findByUserId(userId).get()).map(userEntity -> {
            userEntity.setEmailId(userDto.getEmailId());
            userEntity.setFirstName(userDto.getFirstName());
            userEntity.setLastName(userDto.getLastName());
            return userEntity;
        }).orElseThrow(() -> new UserNotFoundException(userId));
        userRepository.flush();
        return map(updateUserEntity, UserDto.class);
    }

    @Override
    public void deleteUser(String userId) {
        log.info("deleteUser of class UserServiceImpl called");
        ofNullable(userRepository.findByUserId(userId).get())
                .map(Optional::of)
                .orElseThrow(() -> new UserNotFoundException(userId))
                .ifPresent(userEntity -> userRepository.delete(userEntity));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername of class UserServiceImpl called");
        UserEntity userEntity = ofNullable(userRepository.findByEmailId(username)).orElseThrow(() -> new UserNotFoundException("EmailId", username)).get();
        return new User(userEntity.getEmailId(), userEntity.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
    }


    @Override
    public UserDto getUserByEmailId(String emailId) {
        log.info("getUserByEmailId of class UserServiceImpl called");
        UserEntity userEntity = ofNullable(userRepository.findByEmailId(emailId)).orElseThrow(() -> new UserNotFoundException("EmailId", emailId)).get();
        return map(userEntity, UserDto.class);
    }
}
