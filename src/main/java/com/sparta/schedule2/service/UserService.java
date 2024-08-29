package com.sparta.schedule2.service;

import com.sparta.schedule2.dto.UserRequestDto;
import com.sparta.schedule2.dto.UserResponseDto;
import com.sparta.schedule2.entity.User;
import com.sparta.schedule2.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto createUser(UserRequestDto requestDto) {
        User user = new User(requestDto);
        User saveUser = userRepository.save(user);
        UserResponseDto responseDto = new UserResponseDto(saveUser);
        return responseDto;
    }

    public UserResponseDto getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(NullPointerException::new);
        UserResponseDto responseDto = new UserResponseDto(user);
        return responseDto;
    }

    public List<UserResponseDto> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDto> responseDtos = users.stream().map(user -> new UserResponseDto(user)).toList();
        return responseDtos;
    }

    public UserResponseDto updateUser(Long userId, UserRequestDto requestDto) {
        User user = userRepository.findById(userId).orElseThrow(NullPointerException::new);
        User saveUser = user.update(requestDto);
        userRepository.save(saveUser);
        UserResponseDto responseDto = new UserResponseDto(saveUser);
        return responseDto;
    }

    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(NullPointerException::new);
        userRepository.delete(user);
    }
}
