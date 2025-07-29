package it.aces.vlad_project.mapper;

import it.aces.vlad_project.dto.user.UserCreateDto;
import it.aces.vlad_project.dto.user.UserResponseDto;
import it.aces.vlad_project.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = SPRING)
public interface UserMapper extends BaseMapper<UserResponseDto, UserEntity> {
    UserEntity toEntity (UserCreateDto userCreateDto);
}
