package com.nanotech.wms.controller;

import com.nanotech.wms.controller.documentation.UserControllerDocumentation;
import com.nanotech.wms.model.dto.request.UserRegisterRequest;
import com.nanotech.wms.model.dto.response.UserResponse;
import com.nanotech.wms.model.entity.User;
import com.nanotech.wms.model.payload.ApiResponse;
import com.nanotech.wms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController implements UserControllerDocumentation {

    private final UserService userService;

    @GetMapping("/{id}")
    public ApiResponse<User> getUserById(@PathVariable UUID id) {
        User user = userService.findById(id);
        return new ApiResponse<>(true, "User found", user);
    }

    @GetMapping("/username/{username}")
    public ApiResponse<User> getUserByUsername(@PathVariable String username) {
        User user = userService.findByUsername(username);
        return new ApiResponse<>(true, "User found", user);
    }

    @GetMapping("/users/{userId}/profile-image")
    public ResponseEntity<byte[]> getUserProfileImage(@PathVariable UUID userId) throws Exception {
        return userService.getProfileImage(userId);
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userService.findAll();
        return new ApiResponse<>(true, "Users found", users);
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> updateUser(@PathVariable UUID id, @RequestBody UserRegisterRequest dto) throws IOException {
        userService.update(id, dto);
        return new ApiResponse<>(true, "User updated successfully");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteUser(@PathVariable UUID id) {
        userService.delete(id);
        return new ApiResponse<>(true, "User deleted successfully");
    }

}
