package com.back.global.rq;

import com.back.domain.member.member.dto.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@RequiredArgsConstructor
public class Rq {
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final HttpSession session;

    public String getCurrentUrl() {
        String url = request.getRequestURL().toString();
        String queryString =  request.getQueryString();

        if (queryString != null && queryString.length() > 0) {
            url = url + "?" + queryString;
        }

        return url;
    }
    public boolean isLogined() {
        return getLoginedMemerId() > 0;
    }

    public boolean isLogout() {
        return isLogined() == false;
    }

    public int getLoginedMemerId() {
        Integer loginedMemberId = (Integer) session.getAttribute("loginedMemerId");

        if (loginedMemberId == null) {
            return 0;
        }

        return loginedMemberId;
    }

    public String getLoginedMemberUsername() {
        return (String) session.getAttribute("loginedMemberUsername");
    }

    public String getLoginedMemberName() {
        return (String) session.getAttribute("loginedMemberName");
    }

    public String getLoginedMemberEmail() {
        return (String) session.getAttribute("loginedMemberEmail");
    }

    public Member getLoginedMember() {
        int id = getLoginedMemerId();
        String username = getLoginedMemberUsername();
        String name = getLoginedMemberName();
        String email = getLoginedMemberEmail();

        return new Member(id, username, name, email);
    }

    public void setLoginDone(Member member) {
        session.setAttribute("loginedMemerId", member.getId());
        session.setAttribute("loginedMemberUsername", member.getUsername());
        session.setAttribute("loginedMemberName", member.getName());
        session.setAttribute("loginedMemberEmail", member.getEmail());
    }

    public void setLogoutDone() {
        session.removeAttribute("loginedMemerId");
        session.removeAttribute("loginedMemberUsername");
        session.removeAttribute("loginedMemberName");
        session.removeAttribute("loginedMemberEmaill");
    }
}