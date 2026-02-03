package com.shermatov.carparts.service;

public interface PasswordResetService {
    void forgotPassword(String email);
    void resetPassword(String token, String newPassword);
}
