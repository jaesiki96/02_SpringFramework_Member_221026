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

    public boolean save(MemberDTO memberDTO) {
        int saveResult = memberRepository.save(memberDTO);
        if (saveResult > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean login(String memberEmail, String memberPassword) {
        MemberDTO memberDTO = memberRepository.login(memberEmail,memberPassword);
        if (memberEmail.equals(memberDTO.getMemberEmail())
                && memberPassword.equals(memberDTO.getMemberPassword())) {
            return true;
        } else {
            return false;
        }
    }

    public List<MemberDTO> members() {
        return memberRepository.members();
    }

    public MemberDTO member(long memberId) {
        return memberRepository.member(memberId);
    }
}
