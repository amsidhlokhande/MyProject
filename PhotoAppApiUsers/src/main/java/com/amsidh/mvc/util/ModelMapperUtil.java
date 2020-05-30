package com.amsidh.mvc.util;

import com.amsidh.mvc.repository.entity.UserEntity;
import com.amsidh.mvc.service.model.UserDto;
import com.amsidh.mvc.ui.model.UserRequestModel;
import com.amsidh.mvc.ui.model.UserResponseModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.modelmapper.convention.MatchingStrategies.STRICT;

@Service
public class ModelMapperUtil {
    private ModelMapper modelMapper = new ModelMapper();
    private EncryptedPasswordUtil encryptedPasswordUtil;

    @Autowired
    public ModelMapperUtil(EncryptedPasswordUtil encryptedPasswordUtil) {
        this.modelMapper.getConfiguration()
                .setMatchingStrategy(STRICT);
        this.encryptedPasswordUtil = encryptedPasswordUtil;
    }

    //UserDto and UserRequestModel Mappings
    public UserDto getUserDto(UserRequestModel userRequestModel) {
        return modelMapper.map(userRequestModel, UserDto.class);
    }

    //UserDto and UserRequestModel Mappings
    public UserResponseModel getUserResponseModel(UserDto userDto) {
        return modelMapper.map(userDto, UserResponseModel.class);
    }

    public List<UserResponseModel> getUserResponseModels(List<UserDto> userDtos) {
        return userDtos.parallelStream().map(userDto -> getUserResponseModel(userDto)).collect(Collectors.toList());
    }

    //UserDto and UserEntity Mapping

    public UserDto getUserDto(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDto.class);
    }

    public UserEntity getUserEntity(UserDto userDto) {
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userEntity.setEncryptedPassword(encryptedPasswordUtil.encrypt(userDto.getPassword()));
        return userEntity;
    }

    public List<UserDto> getUserDtosFromUserEntities(List<UserEntity> userEntities) {
        return userEntities.parallelStream().map(userEntity -> getUserDto(userEntity)).collect(Collectors.toList());
    }
}
