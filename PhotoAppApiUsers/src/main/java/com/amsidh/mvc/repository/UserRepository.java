package com.amsidh.mvc.repository;

import com.amsidh.mvc.repository.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {

    Optional<UserEntity> findByUserId(String userId);
    Optional<UserEntity> findByEmailId(String emailId);
}
