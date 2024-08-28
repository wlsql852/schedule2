package com.sparta.schedule2.entity;

import com.sparta.schedule2.dto.MessageCreateRequestDto;
import com.sparta.schedule2.dto.MessageUpdateReqeustDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="message")
@NoArgsConstructor
public class Message extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username", nullable=false, length=50)
    private String username;
    @Column(name="content", nullable=false, length=500)
    private String content;

    @ManyToOne
    @JoinColumn(name="schedule_id")
    private Schedule schedule;


    public Message(MessageCreateRequestDto requestDto, Schedule schedule) {
        this.username = requestDto.getUsername();
        this.content = requestDto.getContent();
        this.schedule = schedule;
    }

    public Message update(MessageUpdateReqeustDto requestDto) {
        this.content = requestDto.getContent();
        return this;
    }
}
