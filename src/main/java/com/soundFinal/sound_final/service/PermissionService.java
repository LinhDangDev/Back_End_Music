package com.soundFinal.sound_final.service;


import com.soundFinal.sound_final.dto.reponse.PermissionResponse;
import com.soundFinal.sound_final.dto.request.PermissionRequest;
import com.soundFinal.sound_final.entity.Permission;
import com.soundFinal.sound_final.mapper.PermissionMapper;
import com.soundFinal.sound_final.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {

     PermissionRepository permissionRepository;
     PermissionMapper permissionMapper;


     @PreAuthorize("hasRole('ADMIN')")
      public PermissionResponse  create (PermissionRequest request) {
            Permission permission = permissionMapper.toPermission(request);
            permission = permissionRepository.save(permission);
            return permissionMapper.toPermissionResponse(permission);

      }

    @PreAuthorize("hasRole('ADMIN')")
      public List<PermissionResponse> getAll(){
          var permissions = permissionRepository.findAll();
          return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
      }

    public void delete(String permission){
        permissionRepository.deleteById(permission);
    }


}
