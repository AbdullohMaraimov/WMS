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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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

    private static final String PHOTO_DIRECTORY = "D:\\Projects\\Tasks\\wms\\src\\main\\resources\\uploads\\photos\\";


    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public void create(UserRegisterRequest dto) throws IOException {
        Optional<User> byUsername = userRepository.findByUsername(dto.username());

        if (byUsername.isPresent()) {
            throw new CustomUsernameAlreadyExistsException("Username already taken, please use different username");
        }

        if (!dto.profileImage().isEmpty()) {
            saveImage(dto.profileImage(), dto.username());
        }

        User user = userMapper.toEntity(dto);
        user.setPhotoPath(PHOTO_DIRECTORY + dto.username() + "_" + dto.profileImage().getOriginalFilename());
        userRepository.save(user);
    }

    @Override
    @Cacheable(value = "userCache", key = "#id")
    public User findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("User with id: %s not found!".formatted(id)));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomNotFoundException("User with username: %s not found!".formatted(username)));
    }

    @Override
    public ResponseEntity<byte[]> getProfileImage(UUID userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent() && user.get().getPhotoPath() != null) {
            String imagePath = user.get().getPhotoPath();
            Path path = Paths.get(imagePath);

            Resource resource = new UrlResource(path.toUri());
            if (!resource.exists()) {
                throw new Exception("Image not found with path %s".formatted(imagePath));
            }

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=salom");
            headers.add(HttpHeaders.CONTENT_TYPE, "image/jpeg");
            byte[] bytes = resource.getContentAsByteArray();

            return new ResponseEntity<>(bytes, headers, 200);
        } else {
            throw new Exception("User or image not found");
        }
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
    @CacheEvict(value = "userCache", key = "#id")
    public void update(UUID id, UserRegisterRequest dto) throws IOException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("User with id %s not found".formatted(id.toString())));
        User newUser = userMapper.toUpdateEntity(user, dto);

        userRepository.save(newUser);
    }

    @Override
    @CacheEvict(value = "userCache", key = "#id")
    public void delete(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("User with id %s not found".formatted(id.toString())));
        user.setDeleted(true);
        userRepository.save(user);
    }

    private void saveImage(MultipartFile file, String username) throws IOException {
        Path uploadPath = Paths.get(PHOTO_DIRECTORY);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName = username + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);
    }

    private void deleteOldPhoto(String oldPhotoPath) throws IOException {
        Path oldPhoto = Paths.get(oldPhotoPath);
        if (Files.exists(oldPhoto)) {
            Files.delete(oldPhoto);
        }
    }

}
