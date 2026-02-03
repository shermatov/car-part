package com.shermatov.carparts.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponseDto
{
    private String token;   // JWT token returned to the client
}
