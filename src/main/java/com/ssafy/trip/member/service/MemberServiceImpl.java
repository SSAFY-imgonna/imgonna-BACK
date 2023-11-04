package com.ssafy.trip.member.service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import com.ssafy.trip.member.model.dto.Member;
import com.ssafy.trip.member.model.dto.MemberFind;
import com.ssafy.trip.member.model.mapper.MemberMapper;
import com.ssafy.trip.util.PasswordUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

@Service
public class MemberServiceImpl implements MemberService {
    private MemberMapper memberMapper;

    public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public int createMember(Map<String, String> map) {

        Member member = new Member();
        member.setId(map.get("registerId"));
        String email = map.get("registerEmail") + map.get("registerEmailAdd");
        member.setEmail(email);
        member.setName(map.get("registerName"));
        member.setPhone(map.get("registerPhone"));
        member.setNickname(map.get("registerNickname"));
        member.setMbti(map.get("mbti"));
        member.setIntroduction(map.get("introduction"));
        member.setRole("general");

        String rawPassword = map.get("registerPw");
        byte[] salt = getSalt();
        String digest = PasswordUtils.encode(rawPassword, salt);
        member.setPassword(digest);
        member.setSalt(PasswordUtils.bytesToHex(salt));
        return memberMapper.createMember(member);
    }

    @Override
    public Member getMemberByIdAndPassword(String id, String password) {
        String digest = getDigest(id, password);
        if (digest == null) return null;
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("password", digest);
        Member member = memberMapper.getMemberByIdAndPassword(map);
        if (member == null) {
            return null;
        }
        System.out.println("digest from DB: " + member.getPassword());
        return member;
    }

    private String getDigest(String id, String password) {
        String salt = memberMapper.getSaltById(id);

        if (salt == null) {
            return null;
        }
        byte[] saltBytes = PasswordUtils.hexToBytes(salt);
        String digest = PasswordUtils.encode(password, saltBytes);
        System.out.println("[로그인]");
        System.out.println("raw password: " + password);
        System.out.println("salt: " + PasswordUtils.bytesToHex(saltBytes));
        System.out.println("digest: " + digest);
        return digest;
    }

    @Override
    public boolean delete(String id, String password) {
        try {
            Member member = getMemberById(id);
            if (!getDigest(id, password).equals(member.getPassword())) {
                return false;
            }
            memberMapper.deleteMember(id);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public int updateMember(Map<String, String> map, HttpSession session) {
        try {
            Member member = getMemberById(map.get("id"));
            member.setName(map.get("name"));
            member.setPhone(map.get("phone"));
            member.setNickname(map.get("nickname"));
            member.setMbti(map.get("mbti"));
            member.setIntroduction(map.get("introduction"));
            System.out.println("##update##\n" + member);
            memberMapper.updateMember(member);
            updateSession(session, member);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
        return 1;
    }


    private void updateSession(HttpSession session, Member member) {
        Member memberDto = (Member) session.getAttribute("memberDto");
        memberDto.setNickname(member.getNickname());
        session.setAttribute("memberDto", memberDto);
    }

    @Override
    public int updateMemberPasswordById(String id, String password) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("password", password);
        try {
            memberMapper.updateMemberPasswordById(map);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
        return 1;
    }

    @Override
    public Member getMemberById(String id) {
        return memberMapper.getMemberById(id);
    }

    @Override
    public String getMemberIdByEmailAndName(MemberFind member) {
        Map<String, String> map = new HashMap<>();
        map.put("email", member.getEmail());
        map.put("name", member.getName());
        return memberMapper.getMemberIdByEmailAndName(map);
    }

    @Override
    public String getMemberPasswordByIdAndEmailAndPhone(MemberFind member) {
        Map<String, String> map = new HashMap<>();
        map.put("id", member.getId());
        map.put("email", member.getEmail());
        map.put("phone", member.getPhone());
        return memberMapper.getMemberPasswordByIdAndEmailAndPhone(map);
    }

    private byte[] getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[8];
        random.nextBytes(salt);
        return salt;
    }

}
