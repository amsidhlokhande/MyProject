package com.amsidh.mvc.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserDto implements Serializable {
    private String userId;
    private String password;
    private String firstName;
    private String lastName;
    private String emailId;
    private String encryptedPassword;
}
