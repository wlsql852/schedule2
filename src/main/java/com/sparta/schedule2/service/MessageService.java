package com.sparta.schedule2.service;

import com.sparta.schedule2.dto.MessageCreateRequestDto;
import com.sparta.schedule2.dto.MessageResponseDto;
import com.sparta.schedule2.dto.MessageUpdateReqeustDto;
import com.sparta.schedule2.entity.Message;
import com.sparta.schedule2.entity.Schedule;
import com.sparta.schedule2.repository.MessageRepository;
import com.sparta.schedule2.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final ScheduleRepository scheduleRepository;
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository, ScheduleRepository scheduleRepository) {
        this.messageRepository = messageRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public MessageResponseDto createMessage(MessageCreateRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(requestDto.getScheduleId()).orElseThrow(NullPointerException::new);
        Message message = new Message(requestDto, schedule);
        Message saveMessage = messageRepository.save(message);
        MessageResponseDto responseDto = new MessageResponseDto(saveMessage);
        return responseDto;
    }

    public MessageResponseDto getMessage(Long messageId) {
        Message message = messageRepository.findById(messageId).orElseThrow(NullPointerException::new);
        MessageResponseDto responseDto = new MessageResponseDto(message);
        return responseDto;
    }

    public List<MessageResponseDto> getMessages(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(NullPointerException::new);
        List<MessageResponseDto> messages= schedule.getMessages().stream().map(m->new MessageResponseDto(m)).toList();
        return messages;
    }

    public MessageResponseDto updateMessage(Long messageId, MessageUpdateReqeustDto requestDto) {
        Message message = messageRepository.findById(messageId).orElseThrow(NullPointerException::new);
        Message saveMessage = message.update(requestDto);
        messageRepository.save(saveMessage);
        MessageResponseDto responseDto = new MessageResponseDto(saveMessage);
        return responseDto;
    }

    public void deleteMessage(Long messageId) {
        Message message = messageRepository.findById(messageId).orElseThrow(NullPointerException::new);
        messageRepository.delete(message);
    }
}
