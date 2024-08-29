package com.sparta.schedule2.service;

import com.sparta.schedule2.dto.UserRequestDto;
import com.sparta.schedule2.dto.UserResponseDto;
import com.sparta.schedule2.entity.User;
import com.sparta.schedule2.repository.UserRepository;
import org.springframework.stereotype.Service;

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
        return new UserResponseDto(saveUser);
    }

    public UserResponseDto getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(NullPointerException::new);
        return new UserResponseDto(user);
    }

    public List<UserResponseDto> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserResponseDto::new).toList();
    }

    public UserResponseDto updateUser(Long userId, UserRequestDto requestDto) {
        User user = userRepository.findById(userId).orElseThrow(NullPointerException::new);
        User saveUser = user.update(requestDto);
        userRepository.save(saveUser);
        return new UserResponseDto(saveUser);
    }

    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(NullPointerException::new);
        userRepository.delete(user);
    }
}
