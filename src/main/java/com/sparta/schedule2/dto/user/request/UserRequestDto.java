package com.sparta.schedule2.dto.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

//유저 등록, 수정 정보
@Getter
public class UserRequestDto {
    @NotBlank(message = "이름을 입력해주세요")
    private String username;
    @Email(message = "이메일 형식이 아닙니다.")
    private String email;
}
