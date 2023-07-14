package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.MemberDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class MemberDao {
    private final SqlSession session;
    private static final String namespace = "tb_member.";

    public String selectLoginId(String lgin_id) throws Exception {
        return session.selectOne(namespace+"lgin_id", lgin_id);
    }

    public String selectPassword(String loginId) throws Exception {
        return session.selectOne(namespace+"lgin_pw", loginId);
    }

    public String selectEmail(String email) throws Exception {
        return session.selectOne(namespace + "email", email);
    }

    public String selectFindId(String name, String email) throws Exception {
        Map map = new HashMap<>();
        map.put("name", name);
        map.put("email", email);
        return session.selectOne(namespace + "find_id", map);
    }

    public Long selectMemberId(String loginId) throws Exception {  // mbr_id(회원번호) 조회
        return session.selectOne(namespace+"mbr_id", loginId);
    }

    public int insertMember(MemberDto memberDto) throws Exception {    // 회원가입 resisterMember
        return session.insert(namespace + "mbr_signup", memberDto);
    }
    public MemberDto selectMemberInfo(Long memberId) throws Exception {    // 회원정보 조회
        return session.selectOne(namespace + "mbr_Info", memberId);
    }

    // update이지만 del_yn을 'Y'로 변경하므로 실질적으로 삭제라고 본다.
    public int deleteMember(Long mbr_id) throws Exception {    // 회원탈퇴 removeMember
        return session.update(namespace + "mbr_withdrawal", mbr_id);
    }

    public int updateMemeber(MemberDto memberDto) throws Exception {    // 회원정보수정   updateMember
        return session.update(namespace + "mbr_modify", memberDto);
    }
}