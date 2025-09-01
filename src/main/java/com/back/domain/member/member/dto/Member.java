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

    public boolean matchPassword(String password) {
        return this.password.substring("{noop}".length()).equals(password);
    }
}