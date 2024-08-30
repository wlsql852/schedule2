package com.sparta.schedule2.service;

import com.sparta.schedule2.dto.ScheduleCreateRequestDto;
import com.sparta.schedule2.dto.ScheduleDetailResponseDto;
import com.sparta.schedule2.dto.ScheduleResponseDto;
import com.sparta.schedule2.dto.ScheduleUpdateRequestDto;
import com.sparta.schedule2.entity.Manage;
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

import java.util.ArrayList;
import java.util.List;

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
        //requestDto에 있는 있는 manager의 아이디로 유저객체를 가진 매니저리스트 생성
        List<User> managers = requestDto.getManagers().stream().map(u->userRepository.findById(u).orElseThrow(()->new NullPointerException("해당 아이디의 유저가 존재하지 않습니다."))).toList();
        List<Manage> manageList = new ArrayList<>();
        //각 유저 객체를 일정객체와 함깨 manage객체로 만듬
        for(User manager : managers) {
            Manage manage = new Manage(schedule,manager);
            manageList.add(manage);
        }
        //manage 정보 저장
        List<Manage> manages = manageRepository.saveAll(manageList);
        return new ScheduleDetailResponseDto(saveSchedule,manages);
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
        //담당자 리스트는 일단 해당 일정의 데이터를 모두 지우고
        manageRepository.deleteAll(manageRepository.findAllByScheduleId(schedule.getId()));
        //담당자 리스트를 새로 만들어서
        List<Manage> manageList = new ArrayList<>();
        List<User> managers = requestDto.getManagers().stream().map(u->userRepository.findById(u).orElseThrow(()->new NullPointerException("해당 아이디의 유저가 존재하지 않습니다."))).toList();
        for(User manager : managers) {
            Manage manage = new Manage(schedule,manager);
            manageList.add(manage);
        }
        //다시 데이터로 저장함
        List<Manage> saveManage = manageRepository.saveAll(manageList);
        //수정된 정보는 response로 넘겨줌
        return new ScheduleDetailResponseDto(saveSchedule, saveManage);
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
