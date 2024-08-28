package com.sparta.schedule2.controller;

import com.sparta.schedule2.dto.MessageCreateRequestDto;
import com.sparta.schedule2.dto.MessageResponseDto;
import com.sparta.schedule2.dto.MessageUpdateReqeustDto;
import com.sparta.schedule2.service.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/create")
    public MessageResponseDto createMessage(@RequestBody MessageCreateRequestDto requestDto) {
        return messageService.createMessage(requestDto);

    }

    @GetMapping("/{messageId}")
    public MessageResponseDto getMessage(@PathVariable Long messageId) {
        return messageService.getMessage(messageId);
    }

    @GetMapping("/schedule/{scheduleId}")
    public List<MessageResponseDto> getMessageBySchedule(@PathVariable Long scheduleId) {
        return messageService.getMessages(scheduleId);
    }

    @PutMapping("/{messageId}")
    public MessageResponseDto updateMessage (@PathVariable Long messageId, @RequestBody MessageUpdateReqeustDto requestDto) {
        return messageService.updateMessage(messageId, requestDto);
    }

    @DeleteMapping("/{messageId}")
    public void deleteMessage (@PathVariable Long messageId) {
        messageService.deleteMessage(messageId);
    }

}
