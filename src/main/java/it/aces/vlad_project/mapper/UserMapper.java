package it.aces.vlad_project.mapper;

import it.aces.vlad_project.dto.user.UserCreateDto;
import it.aces.vlad_project.dto.user.UserResponseDto;
import it.aces.vlad_project.dto.user.UserThinResponseDto;
import it.aces.vlad_project.entity.UserEntity;
import it.aces.vlad_project.util.MappingUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = SPRING, uses = {RoleMapper.class, MappingUtil.class})
public interface UserMapper extends BaseMapper<UserResponseDto, UserEntity> {

    @Mapping(target = "role", ignore = true)
    UserEntity toEntity (UserCreateDto userCreateDto);

    @Mapping(target = "role", source = "role", qualifiedByName = "mapEntitiesToIds")
    UserThinResponseDto toUserThinDto(UserEntity userEntity);
}
