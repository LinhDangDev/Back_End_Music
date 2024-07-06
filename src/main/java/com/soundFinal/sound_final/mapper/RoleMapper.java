package com.soundFinal.sound_final.mapper;

import com.soundFinal.sound_final.dto.reponse.RoleResponse;
import com.soundFinal.sound_final.dto.request.RoleRequest;
import com.soundFinal.sound_final.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
