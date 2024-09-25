package com.nanotech.wms.controller.documentation;

import com.nanotech.wms.model.dto.request.UserLoginRequest;
import com.nanotech.wms.model.dto.request.UserRegisterRequest;
import com.nanotech.wms.model.payload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

public interface AuthControllerDocumentation {

    @Operation(summary = "Register a new user")
    ApiResponse<Void> register(@ModelAttribute UserRegisterRequest request) throws IOException;

    @Operation(summary = "User login")
    ApiResponse<String> login(@RequestBody UserLoginRequest dto);

}
