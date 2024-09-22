package com.nanotech.wms.model.dto.response;

public record UserResponse(
        String username,
        String mainPhoto,
        String email
) { }
