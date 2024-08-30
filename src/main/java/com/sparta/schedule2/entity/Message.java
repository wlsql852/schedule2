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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @Column(name="content", nullable=false, length=500)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="schedule_id", nullable = false)
    private Schedule schedule;


    public Message(MessageCreateRequestDto requestDto, Schedule schedule, User user) {
        this.user = user;
        this.content = requestDto.getContent();
        this.schedule = schedule;
    }

    public Message update(MessageUpdateReqeustDto requestDto) {
        this.content = requestDto.getContent();
        return this;
    }
}
