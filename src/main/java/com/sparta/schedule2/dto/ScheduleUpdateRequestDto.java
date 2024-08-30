package com.sparta.schedule2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

//일정 수정 정보
@Getter
public class ScheduleUpdateRequestDto {
    @NotBlank(message = "제목을 필수로 입력되어야 합니다.")
    private String title;
    @NotEmpty(message = "내용을 입력해주세요")
    private String content;
    @NotNull(message = "담당자 아이디가 필수로 입력되어야 합니다")
    private List<Long> managers;
}
