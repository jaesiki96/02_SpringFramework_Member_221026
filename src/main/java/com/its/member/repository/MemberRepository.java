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
        // memberDTO 객체 만들기
        MemberDTO memberDTO = new MemberDTO();
        // DTO 객체 담기
        memberDTO.setMemberEmail(memberEmail);
        memberDTO.setMemberPassword(memberPassword);
        // DTO 객체 넘겨주기
        return sql.selectOne("Member.login", memberDTO);
    }

    public MemberDTO login(MemberDTO memberDTO) {
        return sql.selectOne("Member.login", memberDTO);
    }

    public List<MemberDTO> findAll() {
        return sql.selectList("Member.findAll");
    }

    public MemberDTO findById(long id) {
        return sql.selectOne("Member.findById", id);
    }

    public void delete(long id) {
        sql.delete("Member.delete", id);
    }

    public MemberDTO findByEmail(String memberEmail) {
        return sql.selectOne("Member.findByEmail", memberEmail);
    }

    public int update(MemberDTO memberDTO) {
        return sql.update("Member.update", memberDTO);
    }
}
