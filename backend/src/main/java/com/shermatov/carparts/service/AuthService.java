package com.shermatov.carparts.service;

import com.shermatov.carparts.dto.request.LoginRequestDto;
import com.shermatov.carparts.dto.request.RegisterRequest;
import com.shermatov.carparts.dto.response.LoginResponseDto;
import com.shermatov.carparts.dto.response.UserResponse;

public interface AuthService {
    UserResponse register(RegisterRequest request);
    LoginResponseDto login(LoginRequestDto request);
}
