package com.its.member.controller;

import com.its.member.dto.MemberDTO;
import com.its.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MemberController {
    @Autowired
    private MemberService memberService;
    // 회원가입 페이지 출력
    @GetMapping("/save")
    public String saveForm() {
    return "memberSave";
    }
    // 회원가입 처리
    @PostMapping("/save")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        boolean saveResult = memberService.save(memberDTO);
        if (saveResult) {
            return "memberLogin";
        } else {
            return "index";
        }
    }
    // 로그인 페이지 출력
    @GetMapping("/login")
    public String loginForm() {
        return "memberLogin";
    }
    // 로그인 처리
    @PostMapping("/login")
    public String login(@RequestParam("memberEmail") String memberEmail,
                        @RequestParam("memberPassword") String memberPassword) {
        boolean loginResult = memberService.login(memberEmail,memberPassword);
        if (loginResult) {
            return "memberMain";
        } else {
            return "index";
        }
    }

    @GetMapping("/members")
    public String members(Model model) {
        List<MemberDTO> memberList = memberService.members();
        model.addAttribute("memberList", memberList);
        return "memberList";
    }

    @GetMapping("/member")
    public String member(@RequestParam("memberId") long memberId, Model model) {
        MemberDTO memberDTO = memberService.member(memberId);
        model.addAttribute("member", memberDTO);
        return "memberDetail";
    }
}
