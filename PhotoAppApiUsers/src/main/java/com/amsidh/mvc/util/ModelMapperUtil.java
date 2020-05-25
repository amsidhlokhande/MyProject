package com.amsidh.mvc.util;

import com.amsidh.mvc.service.model.UserDto;
import com.amsidh.mvc.repository.entity.UserEntity;
import com.amsidh.mvc.ui.UserRequestModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.modelmapper.convention.MatchingStrategies.STRICT;

@Service
public class ModelMapperUtil {
    private ModelMapper modelMapper = new ModelMapper();

    public ModelMapperUtil() {
        this.modelMapper.getConfiguration()
                .setMatchingStrategy(STRICT);
    }

    //UserDto and UserRequestModel Mappings
    public UserRequestModel getUserRequestModel(UserDto userDto) {
        return modelMapper.map(userDto, UserRequestModel.class);
    }

    public UserDto getUserDto(UserRequestModel userRequestModel) {
        return modelMapper.map(userRequestModel, UserDto.class);
    }

    public List<UserRequestModel> getUserRequestModels(List<UserDto> userDtos) {
        return userDtos.parallelStream().map(userDto -> getUserRequestModel(userDto)).collect(Collectors.toList());
    }

    public List<UserDto> getUserDtos(List<UserRequestModel> userRequestModels) {
        return userRequestModels.parallelStream().map(userRequestModel -> getUserDto(userRequestModel)).collect(Collectors.toList());
    }


    //UserDto and UserEntity Mapping

    public UserDto getUserDto(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDto.class);
    }

    public UserEntity getUserEntity(UserDto userDto) {
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userEntity.setEncryptedPassword(EncryptedPasswordUtil.encrypt(userDto.getPassword()));
        return userEntity;
    }

    public List<UserEntity> getUserEntities(List<UserDto> userDtos) {
        return userDtos.parallelStream().map(userDto -> getUserEntity(userDto)).collect(Collectors.toList());
    }

    public List<UserDto> getUserDtosFromUserEntities(List<UserEntity> userEntities) {
        return userEntities.parallelStream().map(userEntity -> getUserDto(userEntity)).collect(Collectors.toList());
    }
}
