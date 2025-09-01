package com.back.domain.member.member.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Member {
    private int id;
    private String username;
    private String password;
    private String name;
    private String email;

    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public Member () {

    }

    public Member(int id, String username, String name, String email) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
    }

    public boolean matchPassword(String password) {
        return this.password.substring("{noop}".length()).equals(password);
    }
}