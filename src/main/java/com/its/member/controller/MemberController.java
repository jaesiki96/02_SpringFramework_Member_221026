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
import javax.servlet.http.HttpSession;
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
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session, Model model) {
        boolean loginResult = memberService.login(memberDTO);
        if (loginResult) {
            // 세션에 로그인한 사용자의 이메일을 저장
            // memberDTO.getMemberEmail()을 "LoginEmail"에 담는다
            session.setAttribute("loginEmail", memberDTO.getMemberEmail());
            model.addAttribute("modelEmail", memberDTO.getMemberEmail());
            return "memberMain";
        } else {
            return "memberLogin";
        }
    }
    // 목록 출력
    // 데이터를 가지고 화면으로 가야함 -> Model 객체 필요
    @GetMapping("/members")
    public String findAll(Model model) {
        List<MemberDTO> memberList = memberService.findAll();
        model.addAttribute("memberList", memberList);
        return "memberList";
    }
    // 상세 조회
    // 파라미터로 id가 넘어왔기 때문에 @RequestParam 사용
    @GetMapping("/member")
    public String findById(@RequestParam("id") long id, Model model) {
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member", memberDTO);
        return "memberDetail";
    }
    // 삭제
    // 링크를 타고오면 Get
    // members 를 다시 불러오는 이유는 redirect 를 사용하지 않으면 삭제 후 회원 목록이 다시 나타나지 않기 때문에
    @GetMapping("/delete")
    public String delete(@RequestParam("id") long id, Model model) {
        memberService.delete(id);
        // 2. redirect 방식을 이용하여 /members 주소 요청
        // members 에 findAll 이 있기 때문에 redirect 로 회원목록을 불러올 수 있다
        return "redirect:/members";
    }
}
