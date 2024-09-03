package com.sparta.schedule2.service;

import com.sparta.schedule2.dto.manage.request.ManageCreateRequestDto;
import com.sparta.schedule2.dto.manage.request.ManageUpdateRequestDto;
import com.sparta.schedule2.dto.schedule.response.ScheduleDetailResponseDto;
import com.sparta.schedule2.dto.user.response.UserSimpleResponseDto;
import com.sparta.schedule2.entity.Manage;
import com.sparta.schedule2.entity.Schedule;
import com.sparta.schedule2.entity.User;
import com.sparta.schedule2.repository.ManageRepository;
import com.sparta.schedule2.repository.ScheduleRepository;
import com.sparta.schedule2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ManageService {
    private final ManageRepository manageRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public ScheduleDetailResponseDto createManage(ManageCreateRequestDto requestDto) {
        //해당 일정 있는지 확인
        Schedule schedule = scheduleRepository.findById(requestDto.getScheduleId()).orElseThrow(()->new NullPointerException("해당 아이디의 일정이 존재하지 않습니다."));
        //작성자가 있는지 확인
        User createdBy = userRepository.findById(requestDto.getCreatedBy()).orElseThrow(()->new NullPointerException("해당 아이디의 유저가 존재하지 않습니다."));
        //작성자가 담당자를 배정하려는 유저가 맞는지 확인
        if(!schedule.getCreatedBy().equals(createdBy)) throw new NullPointerException("담당자를 등록하려는 유저가 일정을 만든 유저가 아닙니다.");
        //담당자가 될 유저 찾기
        User manager = userRepository.findById(requestDto.getManagerId()).orElseThrow(()->new NullPointerException("해당 아이디의 유저가 존재하지 않습니다."));
        //담당자 객체 만들기
        Manage manage = new Manage(schedule, manager);
        //데이터에 저장
        Manage savemanage = manageRepository.save(manage);
        //실제로 저장되었음을 최종 일정정보로 알려줌
        return new ScheduleDetailResponseDto(savemanage.getSchedule());
    }

    //해당 일정의 매니저 리스트 가져오기
    public List<UserSimpleResponseDto> getManagers(Long scheduleId) {
        //해당 일정을 가진 담당자리스트를 가져오기
        List<Manage> manages = manageRepository.findAllByScheduleId(scheduleId);
        //각 담담의 담당자를 유저responseDto로 바꿔 리스트로 반환
        return manages.stream().map(manage -> new UserSimpleResponseDto(manage.getManager())).toList();
    }

    //해당 일정의 담당자 수정
    @Transactional
    public ScheduleDetailResponseDto updateManage(Long scheduleId, ManageUpdateRequestDto requestDto) {
        //해당 일정 있는지 확인
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(()->new NullPointerException("해당 아이디의 일정이 존재하지 않습니다."));
        //작성자가 있는지 확인
        User createdBy = userRepository.findById(requestDto.getCreatedBy()).orElseThrow(()->new NullPointerException("해당 아이디의 작성자 유저가 존재하지 않습니다."));
        //작성자가 담당자를 배정하려는 유저가 맞는지 확인
        if(!schedule.getCreatedBy().equals(createdBy)) throw new NullPointerException("담당자를 등록하려는 유저가 일정을 만든 유저가 아닙니다.");
        //해당 기존의 매니저 아이디로 manage데이터 찾기
        User originManager = userRepository.findById(requestDto.getBeforeManagerId()).orElseThrow(()->new NullPointerException("해당 아이디의 매니저 유저가 존재하지 않습니다."));
        Manage manage = manageRepository.findByScheduleIdAndManagerId(schedule.getId(), originManager.getId()).orElseThrow(()->new NullPointerException("이 일정에 해당 아이디의 매니저 유저가 존재하지 않습니다."));
        System.out.println(manage.getSchedule().getId()+", "+manage.getManager().getId());
        //담당자가 될 유저 찾기
        User newManager = userRepository.findById(requestDto.getAfterManagerId()).orElseThrow(()->new NullPointerException("해당 아이디의 유저가 존재하지 않습니다."));
        //manage 객체 정보 바꾸기
        manage.update(newManager);
        //데이터로 저장
        Manage savemanage = manageRepository.save(manage);
        System.out.println(savemanage.getSchedule().getId()+", "+savemanage.getManager().getId());
        //수정된 결과를 일정정보로 알려주기
        return new ScheduleDetailResponseDto(savemanage.getSchedule());
    }


    @Transactional
    public void deleteManage(Long scheduleId, Long managerId) {
        Manage manage = manageRepository.findByScheduleIdAndManagerId(scheduleId, managerId).orElseThrow(()->new NullPointerException("이 일정에 해당 아이디의 매니저 유저가 존재하지 않습니다."));
        System.out.println(manage.getId()+", "+manage.getSchedule().getId()+", "+manage.getManager().getId());
        manageRepository.delete(manage);
    }
}
