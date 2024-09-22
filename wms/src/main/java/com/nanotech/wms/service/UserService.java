package com.nanotech.wms.service;

import com.nanotech.wms.model.dto.response.RegistrationResponse;
import com.nanotech.wms.model.dto.request.UserRegisterRequest;
import com.nanotech.wms.model.dto.response.UserResponse;
import com.nanotech.wms.model.entity.User;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface UserService {

    void create(UserRegisterRequest userRegisterRequest) throws IOException;

    User findByUsername(String username);

    List<UserResponse> findAll();

    void update(UUID uuid, UserRegisterRequest userRegisterRequest) throws IOException;

    void delete(UUID uuid);

}
