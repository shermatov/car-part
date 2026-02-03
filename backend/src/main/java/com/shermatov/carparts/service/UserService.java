package com.shermatov.carparts.service;

import com.shermatov.carparts.domain.User;
import com.shermatov.carparts.dto.request.UserCreateRequestDto;
import com.shermatov.carparts.dto.request.UserUpdateRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    User create(UserCreateRequestDto createRequestDto);
    User update(String email, UserUpdateRequestDto updateRequestDto);
    User findByEmail(String email);
    Page<User> getUsers(Pageable pageable);
    Page<User> getUsersOnly(Pageable pageable);
    Page<User> getAdminsOnly(Pageable pageable);
    void delete(String email);
    Page<User> search(String query, Pageable pageable);

}
