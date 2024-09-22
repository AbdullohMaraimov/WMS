package com.nanotech.wms.service.security;


import com.nanotech.wms.exception.CustomNotFoundException;
import com.nanotech.wms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws CustomNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomNotFoundException("User not found with the username : %s".formatted(username)));
    }
}

