package com.shermatov.carparts.service.impl;

import com.shermatov.carparts.domain.User;
import com.shermatov.carparts.dto.request.LoginRequestDto;
import com.shermatov.carparts.dto.request.RegisterRequest;
import com.shermatov.carparts.dto.response.LoginResponseDto;
import com.shermatov.carparts.dto.response.UserResponse;
import com.shermatov.carparts.exception.EmailAlreadyUsedException;
import com.shermatov.carparts.repository.UserRepository;
import com.shermatov.carparts.security.JwtService;
import com.shermatov.carparts.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    @Transactional
    public UserResponse register(RegisterRequest request) {
        if (userRepository.existsByEmailAndDeletedAtIsNull(request.getEmail())) {
            throw new EmailAlreadyUsedException("Email is already in use");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        User saved = userRepository.save(user);
        return new UserResponse(saved.getEmail());
    }

    @Override
    public LoginResponseDto login(LoginRequestDto request) {
        User user = userRepository.findByEmailAndDeletedAtIsNull(request.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        String token = jwtService.generateToken(user);
        return new LoginResponseDto(token);
    }
}
