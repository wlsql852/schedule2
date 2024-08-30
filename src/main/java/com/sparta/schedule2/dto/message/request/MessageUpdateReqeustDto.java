package com.sparta.schedule2.dto.message.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;

//댓글 수정 정보
@Getter
public class MessageUpdateReqeustDto {
    @NotEmpty(message = "내용을 넣어주세요")
    @Size(max=500, message = "500자 이하만 가능합니다.")
    private String content;
}
