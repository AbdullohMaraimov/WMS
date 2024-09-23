package com.nanotech.wms.service;

import com.nanotech.wms.model.dto.request.UserLoginRequest;
import com.nanotech.wms.model.dto.request.UserRegisterRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AuthService {

    void register(UserRegisterRequest registerDto, MultipartFile photo) throws IOException;

    String login(UserLoginRequest loginDto);
}
