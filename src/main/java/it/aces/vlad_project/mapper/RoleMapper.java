package it.aces.vlad_project.mapper;

import it.aces.vlad_project.dto.role.RoleCreateDto;
import it.aces.vlad_project.dto.role.RoleResponseDto;
import it.aces.vlad_project.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = SPRING)
public interface RoleMapper extends BaseMapper<RoleResponseDto, RoleEntity> {
    RoleEntity toEntity (RoleCreateDto roleCreateDto);
}
