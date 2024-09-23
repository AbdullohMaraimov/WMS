package com.nanotech.wms.controller;

import com.nanotech.wms.model.dto.request.UserLoginRequest;
import com.nanotech.wms.model.dto.request.UserRegisterRequest;
import com.nanotech.wms.model.payload.ApiResponse;
import com.nanotech.wms.service.AuthService;
import com.nanotech.wms.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/register")
    public ApiResponse<Void> register(@RequestPart("user") @Valid UserRegisterRequest dto,
                                      @RequestPart(value = "photo", required = false) MultipartFile photo) throws IOException {
        userService.create(dto, photo);
        return new ApiResponse<>(true,"Saved successfully!");
    }

    @GetMapping("/login")
    public ApiResponse<String> login(@RequestBody @Valid UserLoginRequest dto) {
        String token = authService.login(dto);
        return new ApiResponse<>(true, "Successful Login!", token);
    }

}
