package com.soundFinal.sound_final.service;

import com.soundFinal.sound_final.dto.reponse.UserResponse;
import com.soundFinal.sound_final.dto.request.PasswordCreationRequest;
import com.soundFinal.sound_final.dto.request.UserCreationRequest;
import com.soundFinal.sound_final.dto.request.UserUpdateRequest;
import com.soundFinal.sound_final.entity.Role;
import com.soundFinal.sound_final.entity.User;

import com.soundFinal.sound_final.exception.AppException;
import com.soundFinal.sound_final.exception.ErrorCode;
import com.soundFinal.sound_final.mapper.UserMapper;
import com.soundFinal.sound_final.repository.RoleRepository;
import com.soundFinal.sound_final.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;

    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByEmailIgnoreCase(request.getEmail()))
            throw new AppException(ErrorCode.EMAIL_EXISTED);

        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USERNAME_EXISTED);
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCreateDate(LocalDateTime.now());

        Optional<Role> userRoleOptional = roleRepository.findByName("USER");
        if (userRoleOptional.isEmpty()) {
            throw new RuntimeException("Role USER not found");
        }

        // Set roles for the user
        Set<Role> roles = new HashSet<>();
        roles.add(userRoleOptional.get());
        user.setRoles(roles);

        return userMapper.toUserResponse(userRepository.save(user));
    }


    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));


        userMapper.updateUser(user, request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        var roles = roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String userId) {

       userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        userRepository.deleteById(userId);
    }



    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        User user = userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        var userResponse = userMapper.toUserResponse(user);
        userResponse.setNoPassword(!StringUtils.hasText(user.getPassword()));

        return userResponse;
    }


    public void createPassword(PasswordCreationRequest request){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED));

        if(StringUtils.hasText(user.getPassword()))
            throw new AppException(ErrorCode.PASSWORD_EXISTED);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

    }


    @PreAuthorize("hasRole('ADMIN')") // phan quyen
//
//    @PreAuthorize("hasAuthority('DETELE')")
    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse).toList();
    }
//    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse getUser(String id) {
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }
}

