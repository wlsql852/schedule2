package com.sparta.schedule2.dto.manage.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ManageCreateRequestDto {
    @NotNull(message = "일정 아이디가 필수로 입력되어야 합니다")
    private Long scheduleId;
    @NotNull(message = "작성자 아이디가 필수로 입력되어야 합니다")
    private Long createdBy;
    @NotNull(message = "담당자 아이디가 필수로 입력되어야 합니다")
    private Long managerId;
}
