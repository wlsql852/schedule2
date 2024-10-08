package com.sparta.schedule2.service;

import com.sparta.schedule2.dto.user.request.UserRequestDto;
import com.sparta.schedule2.dto.user.response.UserResponseDto;
import com.sparta.schedule2.entity.User;
import com.sparta.schedule2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //유저 생성
    @Transactional
    public UserResponseDto createUser(UserRequestDto requestDto) {
        //requestDto로 유저 객체 생성
        User user = new User(requestDto);
        //유저 데이터 생성
        User saveUser = userRepository.save(user);
        //생성된 데이터로 responseDto 날림
        return new UserResponseDto(saveUser);
    }

    //단건 조회
    public UserResponseDto getUser(Long userId) {
        //해당 아이디로 유저 찾기
        User user = userRepository.findById(userId).orElseThrow(()->new NullPointerException("해당 아이디의 유저가 존재하지 않습니다."));
        //찾은 유저객체를 responseDto로 날리기
        return new UserResponseDto(user);
    }

    //다건 조회
    public List<UserResponseDto> getUsers() {
        //유저 목록 가져오기
        List<User> users = userRepository.findAll();
        //각 유저객체를 responseDto로 만들어 날리기
        return users.stream().map(UserResponseDto::new).toList();
    }

    //유저 수정
    @Transactional
    public UserResponseDto updateUser(Long userId, UserRequestDto requestDto) {
        //아이디로 해당 유저 찾기
        User user = userRepository.findById(userId).orElseThrow(()->new NullPointerException("해당 아이디의 유저가 존재하지 않습니다."));
        //유저 객체 내용 바꾸기
        User saveUser = user.update(requestDto);
        //유저 데이터 수정
        userRepository.save(saveUser);
        //수정된 데이터를 responseDto로 날리기
        return new UserResponseDto(saveUser);
    }

    //유저 삭제
    @Transactional
    public void deleteUser(Long userId) {
        //아이디로 해당 유저 찾기
        User user = userRepository.findById(userId).orElseThrow(()->new NullPointerException("해당 아이디의 유저가 존재하지 않습니다."));
        //유저 데이터 삭제
        userRepository.delete(user);
    }
}
