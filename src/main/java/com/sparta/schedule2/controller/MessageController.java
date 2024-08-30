package com.sparta.schedule2.controller;

import com.sparta.schedule2.dto.message.request.MessageCreateRequestDto;
import com.sparta.schedule2.dto.message.response.MessageResponseDto;
import com.sparta.schedule2.dto.message.request.MessageUpdateReqeustDto;
import com.sparta.schedule2.service.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//댓글 관련 페이지
@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    /**
     * 댓글 생성
     *
     * @Param 댓글 등록 정보
     * @Return 생성된 댓글 정보
     */
    @PostMapping("/create")
    public MessageResponseDto createMessage(@Valid  @RequestBody MessageCreateRequestDto requestDto) {
        return messageService.createMessage(requestDto);
    }

    /**
     * 댓글 단건 조회
     *
     * @Param 검색할 댓글 아이디
     * @Return 검색한 댓글 아이디 정보
     */
    @GetMapping("/{messageId}")
    public MessageResponseDto getMessage(@PathVariable Long messageId) {
        return messageService.getMessage(messageId);
    }

    /**
     * 해당 일정의 댓글 목록 조회
     *
     * @Param 검색할 아이디
     * @Return 해당 아이디의 일정에 등록된 댓글 정보
     */
    @GetMapping("/schedule/{scheduleId}")
    public List<MessageResponseDto> getMessageBySchedule(@PathVariable Long scheduleId) {
        return messageService.getMessages(scheduleId);
    }

    /**
     * 댓글 수정
     *
     * @Param 해당 댓글 아이디, 댓글 수정 정보
     * @Return 수정된 댓글 정보
     */
    @PutMapping("/{messageId}")
    public MessageResponseDto updateMessage(@PathVariable Long messageId, @Valid @RequestBody MessageUpdateReqeustDto requestDto) {
        return messageService.updateMessage(messageId, requestDto);
    }

    /**
     * 댓글 삭제
     *
     * @Param 댓글 아이디
     * @Return 없음
     */
    @DeleteMapping("/{messageId}")
    public void deleteMessage(@PathVariable Long messageId) {
        messageService.deleteMessage(messageId);
    }

}
