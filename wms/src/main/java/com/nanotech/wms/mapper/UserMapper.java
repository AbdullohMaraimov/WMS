package com.nanotech.wms.mapper;

import com.nanotech.wms.model.dto.request.UserRegisterRequest;
import com.nanotech.wms.model.dto.response.UserResponse;
import com.nanotech.wms.model.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    public User toEntity(UserRegisterRequest dto) {
        User user = new User();
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        return user;
    }

    public UserResponse toResponse(User user) {
        return new UserResponse(
                user.getUsername(),
                user.getPhotoPath(),
                user.getEmail()
        );
    }

    public List<UserResponse> toResponses(List<User> users) {
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : users) {
            userResponses.add(toResponse(user));
        }
        return userResponses;
    }

    public User toUpdateEntity(User newUser, UserRegisterRequest userDto) {
        newUser.setUsername(userDto.username());
        newUser.setEmail(userDto.email());
        newUser.setPassword(userDto.password());
        return newUser;
    }
}
