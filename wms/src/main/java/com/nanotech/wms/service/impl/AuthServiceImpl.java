package com.nanotech.wms.service.impl;

import com.nanotech.wms.model.dto.request.UserLoginRequest;
import com.nanotech.wms.model.dto.request.UserRegisterRequest;
import com.nanotech.wms.model.entity.User;
import com.nanotech.wms.service.AuthService;
import com.nanotech.wms.service.UserService;
import com.nanotech.wms.service.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;

    @Override
    public void register(UserRegisterRequest registerDto, MultipartFile photo) throws IOException {
        userService.create(registerDto, photo);
    }

    @Override
    public String login(UserLoginRequest loginDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.username(), loginDto.password()));
        User user = userService.findByUsername(loginDto.username());
        return jwtService.generateToken(user);
    }
}
