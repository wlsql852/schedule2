package com.sparta.schedule2.service;

import com.sparta.schedule2.dto.schedule.request.ScheduleCreateRequestDto;
import com.sparta.schedule2.dto.schedule.request.ScheduleUpdateRequestDto;
import com.sparta.schedule2.dto.schedule.response.ScheduleDetailResponseDto;
import com.sparta.schedule2.dto.schedule.response.ScheduleResponseDto;
import com.sparta.schedule2.entity.Schedule;
import com.sparta.schedule2.entity.User;
import com.sparta.schedule2.repository.ManageRepository;
import com.sparta.schedule2.repository.ScheduleRepository;
import com.sparta.schedule2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final ManageRepository manageRepository;

    //일정 생성
    @Transactional
    public ScheduleDetailResponseDto createSchedule(ScheduleCreateRequestDto requestDto) {
        //requestDto의 createdBy인 아이디로 유저 찾기
        User user = userRepository.findById(requestDto.getCreatedBy()).orElseThrow(()->new NullPointerException("해당 아이디의 유저가 존재하지 않습니다."));
        //유저정보를 주고 일정 객체 생성
        Schedule schedule = new Schedule(requestDto, user);
        //일정 생성
        Schedule saveSchedule = scheduleRepository.save(schedule);
        return new ScheduleDetailResponseDto(saveSchedule);
    }

    //일정 단건 조회
    //매니저 유저 정보를 가져올때 fetch가 lazy로 걸려 있으므로 읽기 전용으로 Transactional
    public ScheduleDetailResponseDto getSchedule(Long scheduleId) {
        //일정 아이디로 해당 일정 찾기
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(()->new NullPointerException("해당 아이디의 일정이 존재하지 않습니다."));
        //일정 정볼를 responseDto로 넘겨줌
        return new ScheduleDetailResponseDto(schedule);
    }

    //일정 수정
    @Transactional
    public ScheduleDetailResponseDto updateSchedule(Long scheduleId, ScheduleUpdateRequestDto requestDto) {
        //아이디로 해당 일정 찾기
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(()->new NullPointerException("해당 아이디의 일정이 존재하지 않습니다."));
        //해당 일정안의 정보를 바꾸고
        Schedule saveSchedule = schedule.update(requestDto);
        //수정하기
        scheduleRepository.save(saveSchedule);
        return new ScheduleDetailResponseDto(saveSchedule);
    }

    //일정 다건 조회(페이지 인덱스와 사이즈 파라미터)
    public Page<ScheduleResponseDto> getSchedules(int page, int size) {
        //데이터의 인덱스는 0부터 시작하므로 page에 -1해서 페이지 정보 보내주기
        Pageable pageable = PageRequest.of(page-1, size);
        //페이지별로 수정일 내림차순으로 목록 가져오기
        Page<Schedule> schedules = scheduleRepository.findALlByOrderByModifiedAtDesc(pageable);
        //가져온 목록의 각 데이터를 responseDto형태로 바꿔서 보내줌
        return schedules.map(ScheduleResponseDto::new);
    }

    //댓글 삭제
    @Transactional
    public void deleteSchedule(Long scheduleId) {
        scheduleRepository.deleteById(scheduleId);
    }
}
