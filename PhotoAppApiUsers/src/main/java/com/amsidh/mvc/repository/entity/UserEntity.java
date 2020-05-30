package com.amsidh.mvc.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Document(collection = "UserDetails")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserEntity implements Serializable {

    @Id
    private String userId;

    @Field("FIRSTNAME")
    @Length(min = 2, max = 50)
    @NotNull
    private String firstName;

    @Field("LASTNAME")
    @Length(min = 2, max = 50)
    @NotNull
    private String lastName;

    @Field("EMAILID")
    @Indexed(unique = true)
    @Length(max = 120)
    @NotNull
    private String emailId;

    @Field("PASSWORD")
    @NotNull
    private String encryptedPassword;

}
