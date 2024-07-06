package com.soundFinal.sound_final.mapper;

import com.soundFinal.sound_final.dto.reponse.UserResponse;
import com.soundFinal.sound_final.dto.request.UserCreationRequest;
import com.soundFinal.sound_final.dto.request.UserUpdateRequest;
import com.soundFinal.sound_final.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
