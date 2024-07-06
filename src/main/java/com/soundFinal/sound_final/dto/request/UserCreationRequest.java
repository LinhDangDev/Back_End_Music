package com.soundFinal.sound_final.dto.request;

import com.soundFinal.sound_final.dto.reponse.PermissionResponse;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PACKAGE)
public class UserCreationRequest {
    @Size(min = 3, message = "USERNAME_INVALID")
    String username;
    @Size(min = 8, message = "INVALID_PASSWORD")
    String password;
    @Email
    String email;
    Set<PermissionResponse> permissions;
}
