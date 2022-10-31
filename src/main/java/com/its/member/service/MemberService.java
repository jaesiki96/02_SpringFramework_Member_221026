package com.its.member.service;

import com.its.member.dto.MemberDTO;
import com.its.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;
    // 회원가입
    public boolean save(MemberDTO memberDTO) {
        int saveResult = memberRepository.save(memberDTO);
        if (saveResult > 0) {
            return true;
        } else {
            return false;
        }
    }
    // 로그인
    public boolean login(MemberDTO memberDTO) {
        MemberDTO member = memberRepository.login(memberDTO);
        if (memberDTO != null) {
            return true;
        } else {
            return false;
        }
    }
    // 목록 출력
    public List<MemberDTO> findAll() {
        return memberRepository.findAll();
    }
    // 상세 조회
    public MemberDTO findById(long id) {
        return memberRepository.findById(id);
    }
    // 삭제
    public void delete(long id) {
        memberRepository.delete(id);
    }
    // 수정화면 출력
    public MemberDTO findByEmail(String memberEmail) {
        return memberRepository.findByEmail(memberEmail);
    }
    // 수청 처리
    public boolean update(MemberDTO memberDTO) {
        int result = memberRepository.update(memberDTO);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }
    // 이메일 중복체크
    public String emailDuplicateCheck(String memberEmail) {
        String checkResult = memberRepository.emailDuplicateCheck(memberEmail);
        if (checkResult == null) {
            return "ok";
        } else {
            return "no";
        }
    }


}
