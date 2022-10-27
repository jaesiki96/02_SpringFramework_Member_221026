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

    public MemberDTO member(long memberId) {
        return memberRepository.member(memberId);
    }
}
