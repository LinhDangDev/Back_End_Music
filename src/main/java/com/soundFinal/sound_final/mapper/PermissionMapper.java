package com.soundFinal.sound_final.mapper;

import com.soundFinal.sound_final.dto.reponse.PermissionResponse;
import com.soundFinal.sound_final.dto.request.PermissionRequest;
import com.soundFinal.sound_final.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}
