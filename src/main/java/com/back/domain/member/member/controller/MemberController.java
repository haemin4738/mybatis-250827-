package com.back.domain.member.member.controller;

import com.back.domain.member.member.dto.Member;
import com.back.domain.member.member.service.MemberService;
import com.back.global.Rq.Rq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@RequestMapping("/members")
@Controller
public class MemberController {
    private final MemberService memberService;
    private final Rq rq;

    @GetMapping("/login")
    public  String showLogin(){
        return "member/member/login";
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(String username, String password){
        Member member = memberService.findByUsername(username);

        // 존재하는 회원인지 검증
        if (member == null) {
            return "존재하지 않는 회원입니다.";
        }

        // 비밀번호 일치 여부 검증
        if (member.matchPassword(password) == false) {
            return "비밀번호가 일치 하지 않습니다.";
        }

        rq.setLoginDone(member);

        return "로그인 처리";
    }

    @GetMapping("/logout")
    @ResponseBody
    public String logout() {
        rq.setLogoutDone();

        return "로그아웃 처리";
    }

    @GetMapping("/me")
    @ResponseBody
    public Member me() {
        return rq.getLoginedMember();
    }
}