package com.nanotech.wms.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public record UserRegisterRequest(

        @NotNull(message = "Username cannot be null")
        @Size(min = 4, max = 20, message = "Username should be of length between 4 and 20")
        String username,

        @NotNull(message = "Password cannot be null")
        @Size(min = 4, max = 20, message = "Password should be of length between 4 and 20")
        String password,

        @Email
        String email,

        MultipartFile photo
) { }