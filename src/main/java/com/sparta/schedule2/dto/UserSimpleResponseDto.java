package com.sparta.schedule2.dto;

import com.sparta.schedule2.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UserSimpleResponseDto {
    private Long id;
    private String username;
    private String email;


    public UserSimpleResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
