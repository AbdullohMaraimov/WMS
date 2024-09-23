package com.nanotech.wms.service.impl;

import com.nanotech.wms.exception.CustomNotFoundException;
import com.nanotech.wms.exception.CustomUsernameAlreadyExistsException;
import com.nanotech.wms.mapper.UserMapper;
import com.nanotech.wms.model.dto.request.UserRegisterRequest;
import com.nanotech.wms.model.dto.response.UserResponse;
import com.nanotech.wms.model.entity.User;
import com.nanotech.wms.repository.UserRepository;
import com.nanotech.wms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String PHOTO_DIRECTORY = "src/main/resources/uploads/photos/";
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public void create(UserRegisterRequest dto, MultipartFile photo) throws IOException {
        Optional<User> byUsername = userRepository.findByUsername(dto.username());

        if (byUsername.isEmpty()) {
            User user = userMapper.toEntity(dto);
            if (!photo.isEmpty()) {
                String photoPath = savePhoto(photo);
                user.setMainPhoto(photoPath);
            }
            userRepository.save(user);
        } else {
            throw new CustomUsernameAlreadyExistsException("Username already taken, please use different username");
        }
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomNotFoundException("User with username: %s not found!".formatted(username)));
    }

    @Override
    public List<UserResponse> findAll() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new CustomNotFoundException("No user found!");
        }
        return userMapper.toResponses(users);
    }

    @Override
    public void update(UUID id, UserRegisterRequest dto) throws IOException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("User with id %s not found".formatted(id.toString())));
        User newUser = userMapper.toUpdateEntity(user, dto);

        if (user.getMainPhoto() != null && !user.getMainPhoto().isEmpty()) {
            deleteOldPhoto(newUser.getMainPhoto());
        }

        userRepository.save(newUser);
    }

    @Override
    public void delete(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("User with id %s not found".formatted(id.toString())));
        user.setDeleted(true);
        userRepository.save(user);
    }

    private String savePhoto(MultipartFile photo) throws IOException {
        Path photoDir = Paths.get(PHOTO_DIRECTORY);
        if (!Files.exists(photoDir)) {
            Files.createDirectories(photoDir);
        }

        String fileName = UUID.randomUUID() + "_" + photo.getOriginalFilename();
        Path photoPath = photoDir.resolve(fileName);

        Files.copy(photo.getInputStream(), photoPath);

        return photoPath.toString();
    }

    private void deleteOldPhoto(String oldPhotoPath) throws IOException {
        Path oldPhoto = Paths.get(oldPhotoPath);
        if (Files.exists(oldPhoto)) {
            Files.delete(oldPhoto);
        }
    }

}
