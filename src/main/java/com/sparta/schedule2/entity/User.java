package com.sparta.schedule2.entity;

import com.sparta.schedule2.dto.user.request.UserRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="user")
public class User extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="username", nullable=false, length=50)
    private String username;
    @Column(name="email", length=100)
    private String email;

    @OneToMany (mappedBy = "createdBy", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Schedule> schedules = new ArrayList<>();

    @OneToMany(mappedBy = "manager", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    List<Manage> manages = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    List<Message> messages = new ArrayList<>();


    public User(UserRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.email = requestDto.getEmail();
    }


    public User update(UserRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.email = requestDto.getEmail();
        return this;
    }
}
