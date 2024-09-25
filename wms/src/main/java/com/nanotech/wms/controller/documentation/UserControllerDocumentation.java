package com.nanotech.wms.controller.documentation;

import com.nanotech.wms.model.dto.request.UserRegisterRequest;
import com.nanotech.wms.model.dto.response.UserResponse;
import com.nanotech.wms.model.entity.User;
import com.nanotech.wms.model.payload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface UserControllerDocumentation {

    @Operation(summary = "Get user by ID")
    ApiResponse<User> getUserById(@PathVariable UUID id);

    @Operation(summary = "Get user by username")
    ApiResponse<User> getUserByUsername(@PathVariable String username);

    @Operation(summary = "Get all users")
    ApiResponse<List<UserResponse>> getAllUsers();

    @Operation(summary = "Update user by ID")
    ApiResponse<Void> updateUser(@PathVariable UUID id, @RequestBody UserRegisterRequest dto) throws IOException;

    @Operation(summary = "Delete user by ID")
    ApiResponse<Void> deleteUser(@PathVariable UUID id);
}
