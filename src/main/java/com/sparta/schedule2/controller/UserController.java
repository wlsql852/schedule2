package com.sparta.schedule2.controller;

import com.sparta.schedule2.dto.UserRequestDto;
import com.sparta.schedule2.dto.UserResponseDto;
import com.sparta.schedule2.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//user 관련 페이지
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 유저 생성\n
     *
     * @Param 유저 등록 정보 \n
     * @Return 생성된 유저 정보\n
     */
    @PostMapping("/create")
    public UserResponseDto createUser(@Valid @RequestBody UserRequestDto requestDto) {
        return userService.createUser(requestDto);
    }

    /**
     * 유저 단건 조회 \n
     *
     * @Param 조회할 유저 아이디 \n
     * @Return 조회된 유저 정보 \n
     */
    @GetMapping("/{userId}")
    public UserResponseDto getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }

    /**
     * 유저 목록 조회 \n
     *
     * @Param \n
     * @Return 유저정보목록 \n
     */
    @GetMapping("")
    public List<UserResponseDto> getAllUsers() {
        return userService.getUsers();
    }

    /**
     * 유저 수정 \n
     *
     * @Param 수정할 유저 아이디, 유저 수정 정보 \n
     * @Return 수정된 유저 정보 \n
     */
    @PutMapping("/{userId}")
    public UserResponseDto updateUser(@PathVariable Long userId, @Valid @RequestBody UserRequestDto requestDto) {
        return userService.updateUser(userId, requestDto);
    }

    /**
     * 유저 삭제 \n
     *
     * @Param 삭제할 유저 아이디 \n
     * @Return \n
     */
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
}
