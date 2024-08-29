package com.sparta.schedule2.service;

import com.sparta.schedule2.dto.MessageCreateRequestDto;
import com.sparta.schedule2.dto.MessageResponseDto;
import com.sparta.schedule2.dto.MessageUpdateReqeustDto;
import com.sparta.schedule2.entity.Message;
import com.sparta.schedule2.entity.Schedule;
import com.sparta.schedule2.entity.User;
import com.sparta.schedule2.repository.MessageRepository;
import com.sparta.schedule2.repository.ScheduleRepository;
import com.sparta.schedule2.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final ScheduleRepository scheduleRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public MessageService(MessageRepository messageRepository, ScheduleRepository scheduleRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    public MessageResponseDto createMessage(MessageCreateRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(requestDto.getScheduleId()).orElseThrow(NullPointerException::new);
        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(NullPointerException::new);
        Message message = new Message(requestDto, schedule, user);
        Message saveMessage = messageRepository.save(message);
        return new MessageResponseDto(saveMessage);
    }

    public MessageResponseDto getMessage(Long messageId) {
        Message message = messageRepository.findById(messageId).orElseThrow(NullPointerException::new);
        return new MessageResponseDto(message);
    }

    public List<MessageResponseDto> getMessages(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(NullPointerException::new);
        return schedule.getMessages().stream().map(MessageResponseDto::new).toList();
    }

    public MessageResponseDto updateMessage(Long messageId, MessageUpdateReqeustDto requestDto) {
        Message message = messageRepository.findById(messageId).orElseThrow(NullPointerException::new);
        Message saveMessage = message.update(requestDto);
        messageRepository.save(saveMessage);
        return new MessageResponseDto(saveMessage);
    }

    public void deleteMessage(Long messageId) {
        Message message = messageRepository.findById(messageId).orElseThrow(NullPointerException::new);
        messageRepository.delete(message);
    }
}
