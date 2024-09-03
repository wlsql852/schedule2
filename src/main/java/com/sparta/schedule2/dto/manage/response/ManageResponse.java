package com.sparta.schedule2.dto.manage.response;

import com.sparta.schedule2.dto.user.response.UserSimpleResponseDto;
import lombok.Getter;

@Getter
public class ManageResponse {
    private Long id;
    private UserSimpleResponseDto manager;
}
