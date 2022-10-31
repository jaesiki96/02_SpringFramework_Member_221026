package com.its.member.controller;

import com.its.member.dto.MemberDTO;
import com.its.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Member;
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
    // ajax 연습 --------------------------------------------------------------------------------------------------------
    // ajax-ex
    @GetMapping("/ajax-ex")
    public String ajaxEx() {
        return "ajaxEx";
    }
    // ajax1
    // ajax 를 사용할때는 public 과 String 사이에 @ResponseBody
    // @ResponseBody 를 사용 안하면 ok.jsp 를 찾아감...
    @GetMapping("/ajax1")
    public @ResponseBody String ajax1() {
        System.out.println("MemberController.ajax1");
        return "ok";
    }
    // ajax2
    @PostMapping("/ajax2")
    public @ResponseBody String ajax2() {
        System.out.println("MemberController.ajax2");
        return "good";
    }
    // ajax3
    @GetMapping("/ajax3")
    public @ResponseBody String ajax3(@RequestParam("value1") String value1,
                                      @RequestParam("value2") String value2) {
        System.out.println("MemberController.ajax3");
        System.out.println("value1 = " + value1 + ", value2 = " + value2);
        return "vvv";
    }
    // ajax4
    @PostMapping("/ajax4")
    public @ResponseBody String ajax4(@RequestParam("value1") String value1,
                                      @RequestParam("value2") String value2) {
        System.out.println("MemberController.ajax4");
        System.out.println("value1 = " + value1 + ", value2 = " + value2);
        String value3 = "I'm a return";
        return value3;
        // value3 가 ajaxEx에 result 로 보내진다
    }
    // ajax5
    @PostMapping("/ajax5")
    public @ResponseBody MemberDTO ajax5(@RequestParam("value1") String value1,
                                      @RequestParam("value2") String value2) {
        System.out.println("MemberController.ajax5");
        System.out.println("value1 = " + value1 + ", value2 = " + value2);
        String value3 = "I'm a return";
        MemberDTO memberDTO = memberService.findById(1L);
        return memberDTO;
    }
    // ajax6
    @PostMapping("/ajax6")
    public @ResponseBody List<MemberDTO> ajax6(@RequestParam("value1") String value1,
                                         @RequestParam("value2") String value2) {
        System.out.println("MemberController.ajax6");
        System.out.println("value1 = " + value1 + ", value2 = " + value2);
        String value3 = "I'm a return";
        List<MemberDTO> memberDTOList = memberService.findAll();
        return memberDTOList;
    }
    // -----------------------------------------------------------------------------------------------------------------

    // 이메일 중복체크 (ajax)
    @PostMapping("/duplicate-check")
    public @ResponseBody String emailDuplicateCheck(@RequestParam("inputEmail") String memberEmail) {
        System.out.println("memberEmail = " + memberEmail);
        String checkResult = memberService.emailDuplicateCheck(memberEmail);
        return checkResult;
    }
    // 상세조회 (ajax)
   @GetMapping("/detail-ajax")
    public @ResponseBody MemberDTO detailAjax(@RequestParam("id") Long id) {
       System.out.println("id = " + id);
//       MemberDTO memberDTO = memberService.findById(id); // 위 findById 가져옴
//       return memberDTO;
       return memberService.findById(id);
   }
}


























