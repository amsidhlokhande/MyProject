package com.amsidh.mvc.util;

import com.amsidh.mvc.repository.entity.UserEntity;
import com.amsidh.mvc.service.model.UserDto;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ModelMapperUtilTest {

    private ModelMapperUtil modelMapperUtil;

    @Before
    public void setup() {
        this.modelMapperUtil = new ModelMapperUtil();
    }


    @Test
    public void testUserEntityToUserDto() {
        UserEntity userEntity = getUserEntity();
        UserDto userDto = modelMapperUtil.getUserDto(userEntity);
        assertEquals(userEntity.getFirstName(), userDto.getFirstName());
        assertEquals(userEntity.getLastName(), userDto.getLastName());
        assertEquals(userEntity.getEmailId(), userDto.getEmailId());
        assertNotNull(userDto.getEncryptedPassword());
        assertEquals(userEntity.getUserId(), userDto.getUserId());
    }

    @Test
    public void testUserDtoToUserEntity() {
        UserDto userDto = getUserDto();
        UserEntity userEntity = modelMapperUtil.getUserEntity(userDto);
        assertEquals(userDto.getFirstName(), userEntity.getFirstName());
        assertEquals(userDto.getLastName(), userEntity.getLastName());
        assertEquals(userDto.getEmailId(), userEntity.getEmailId());
        assertNotNull(userEntity.getEncryptedPassword());
        assertEquals(userDto.getUserId(), userEntity.getUserId());
    }

    @Test
    public void testForUserDtosFromUserEntities() {

        List<UserEntity> entities = new ArrayList<>();
        entities.add(getUserEntity());
        List<UserDto> userDtos = modelMapperUtil.getUserDtosFromUserEntities(entities);
        assertTrue(!userDtos.isEmpty());
    }

    @Test
    public void testForUserEntities() {

        List<UserDto> userDtos = new ArrayList<>();
        userDtos.add(getUserDto());
        List<UserEntity> userEntities = modelMapperUtil.getUserEntities(userDtos);
        assertTrue(!userEntities.isEmpty());
    }

    private UserEntity getUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName("Amsidh");
        userEntity.setLastName("Lokhande");
        userEntity.setEmailId("amsidhlokhande@gmail.com");
        userEntity.setEncryptedPassword("123456789");
        userEntity.setUserId(new UuidUtil().getNextUuid());

        return userEntity;
    }

    private UserDto getUserDto() {
        UserDto userDto = new UserDto();
        userDto.setFirstName("Amsidh");
        userDto.setLastName("Lokhande");
        userDto.setEmailId("amsidhlokhande@gmail.com");
        userDto.setPassword("123");
        userDto.setUserId(new UuidUtil().getNextUuid());

        return userDto;
    }


}
