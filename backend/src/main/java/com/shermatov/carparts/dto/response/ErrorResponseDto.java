package com.shermatov.carparts.dto.response;

import java.time.LocalDateTime;

public record ErrorResponseDto(LocalDateTime timestamp, String error, int status) {
}
