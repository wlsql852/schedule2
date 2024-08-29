package com.sparta.schedule2.dto;

import com.sparta.schedule2.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSimpleResponseDto {
    private Long id;
    private String username;
    private String email;


    // 일정 단건조회, 수정, 삭제에 사용하는 응답정보의 담당자 정보
    //담당자 아이디, 이름, 이메일 정보
    public UserSimpleResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
