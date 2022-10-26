package com.its.member.repository;

import com.its.member.dto.MemberDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {
    @Autowired
    private SqlSessionTemplate sql;

    public int save(MemberDTO memberDTO) {
        return sql.insert("Member.save", memberDTO);
    }

    public MemberDTO login(String memberEmail, String memberPassword) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberEmail(memberEmail);
        memberDTO.setMemberPassword(memberPassword);
        return sql.selectOne("Member.login", memberDTO);
    }

    public List<MemberDTO> members() {
        return sql.selectList("Member.members");
    }

    public MemberDTO member(long memberId) {
        return sql.selectOne("Member.member", memberId);
    }
}
