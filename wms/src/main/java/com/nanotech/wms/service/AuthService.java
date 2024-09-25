package com.nanotech.wms.service;

import com.nanotech.wms.model.dto.request.UserLoginRequest;
import com.nanotech.wms.model.dto.request.UserRegisterRequest;

import java.io.IOException;

public interface AuthService {

    void register(UserRegisterRequest registerDto) throws IOException;

    String login(UserLoginRequest loginDto);
}
