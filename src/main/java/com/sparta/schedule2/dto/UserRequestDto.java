package com.sparta.schedule2.dto;

import lombok.Getter;

//유저 등록, 수정 정보
@Getter
public class UserRequestDto {
    private String username;
    private String email;
}
