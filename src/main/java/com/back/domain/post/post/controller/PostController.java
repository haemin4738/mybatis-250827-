package com.back.domain.post.post.controller;

import com.back.domain.post.post.dto.Post;
import com.back.domain.post.post.service.PostService;
import com.back.global.Rq.Rq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/posts")
@RequiredArgsConstructor
@Controller
public class PostController {
    private final PostService postService;
    private final Rq rq;

    @GetMapping("/list")
    public String list() {
        System.out.println("list : " + rq.getCurrentUrl());

        return "post/post/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute("id", id);
        return "post/post/detail";
    }

    @GetMapping("/write")
    public String write() {

        return "post/post/write";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Integer id) {

        Post post = postService.findById(id);

        if (rq.getLoginedMember().getId() != post.getMemberId()) {
            return "회원 불일치";
        }

        return "post/post/write";
    }
}