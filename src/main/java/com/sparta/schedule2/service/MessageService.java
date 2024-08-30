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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MessageService {
    private final ScheduleRepository scheduleRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public MessageService(MessageRepository messageRepository, ScheduleRepository scheduleRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    //댓글 생성
    @Transactional
    public MessageResponseDto createMessage(MessageCreateRequestDto requestDto) {
        //requestDto에 있는 scheduleId로 해당 일정 찾기
        Schedule schedule = scheduleRepository.findById(requestDto.getScheduleId()).orElseThrow(()->new NullPointerException("해당 아이디의 일정이 존재하지 않습니다."));
        //requestDto에 있는 userId로 해당 유저 찾기
        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(()->new NullPointerException("해당 아이디의 유저가 존재하지 않습니다."));
        //해당 일정에 해당 유저의 아이디로 댓글 생성
        Message message = new Message(requestDto, schedule, user);
        Message saveMessage = messageRepository.save(message);
        //생성된 댓글 보여주기
        return new MessageResponseDto(saveMessage);
    }

    //댓글 단건 조회
    public MessageResponseDto getMessage(Long messageId) {
        //아이디로 해당 댓글 찾기
        Message message = messageRepository.findById(messageId).orElseThrow(()->new NullPointerException("해당 아이디의 댓글이 존재하지 않습니다."));
        //찾은 메세지를 responseDto로 넘겨주기
        return new MessageResponseDto(message);
    }

    //해당 일정의 댓글 목록 조회
    public List<MessageResponseDto> getMessages(Long scheduleId) {
        //일정 아이디로 해당 일정 찾기
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(()->new NullPointerException("해당 아이디의 일정이 존재하지 않습니다."));
        //해당 일정의 메세지를 가져와서 reponseDto로 바꾼후 리스트로 가져오기
        return schedule.getMessages().stream().map(MessageResponseDto::new).toList();
    }

    //댓글 수정
    @Transactional
    public MessageResponseDto updateMessage(Long messageId, MessageUpdateReqeustDto requestDto) {
        //아이디로 해당 댓글 찾기
        Message message = messageRepository.findById(messageId).orElseThrow(()->new NullPointerException("해당 아이디의 댓글이 존재하지 않습니다."));
        //해당 댓글 내용 바꾸고
        Message saveMessage = message.update(requestDto);
        //데이터 수정(save는 있으면 수정,없으면 생성하는 함수)
        messageRepository.save(saveMessage);
        //수정된 정보를 responseDto로 넘겨줌
        return new MessageResponseDto(saveMessage);
    }

    //댓글 삭제
    @Transactional
    public void deleteMessage(Long messageId) {
        //아이디로 해당 댓글 찾기
        Message message = messageRepository.findById(messageId).orElseThrow(()->new NullPointerException("해당 아이디의 댓글이 존재하지 않습니다."));
        //댓글 삭제
        messageRepository.delete(message);
    }
}
