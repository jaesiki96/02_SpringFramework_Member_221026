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
    // 수정화면 출력
    @GetMapping("/update")
    public String updateForm(HttpSession session, Model model) {
        // session 값을 가져오기 (abstract 가 붙으면 추상메서드)
        // 좌변은 String, 우변은 object(object 가 가장 큼) -> 그래서 우변을 강제 형변환 해야 함
        String memberEmail = (String) session.getAttribute("loginEmail");
        // memberEmail 로 DB 에서 해당 회원의 전체 정보 조회
        // 한명에 대한 것만 수정하는 것이기 때문에 DTO
        MemberDTO memberDTO = memberService.findByEmail(memberEmail);
        model.addAttribute("member", memberDTO);
        return "memberUpdate";
    }
    // 수정 처리
    @PostMapping("/update")
    public String update(@ModelAttribute MemberDTO memberDTO) {
        boolean result = memberService.update(memberDTO);
        if (result) {
            // 로그인 회원의 memberDetail.jsp
            // id 값은 바뀌기 때문에 + 해당 DTO 의 id 값을 가져온다!!!!!!!!!
            return "redirect:/member?id="+memberDTO.getId();
        } else {
            return "index";
        }
    }
    // 로그아웃 (세션이 사라짐)
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }
}


























