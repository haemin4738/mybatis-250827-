package com.back.domain.member.member.controller;

import com.back.domain.member.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/members")
@Controller
public class MemberController {
    private final MemberService memberService;

    @GetMapping("login")
    public String login(){
        return "member/member/login";
    }
}
