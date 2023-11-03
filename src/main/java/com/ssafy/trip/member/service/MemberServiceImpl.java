package com.ssafy.trip.member.service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import com.ssafy.trip.member.model.Member;
import com.ssafy.trip.member.model.mapper.MemberMapper;
import com.ssafy.trip.util.PasswordUtils;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
    private MemberMapper memberMapper;

    public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public int createMember(Member memberDto) {

        byte[] salt = getSalt();
        String digest = PasswordUtils.encode(memberDto.getPassword(), salt);
        memberDto.setPassword(digest);
        memberDto.setSalt(PasswordUtils.bytesToHex(salt));
        return memberMapper.createMember(memberDto);
    }

    @Override
    public Member getMemberByIdAndPassword(String id, String password) {
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

    @Override
    public int getMemberById(String id) {
        return memberMapper.getMemberById(id);
    }

    @Override
    public int getMemberByEmail(String email) {
        return memberMapper.getMemberByEmail(email);
    }

    @Override
    public void delete(String id) {
        memberMapper.delete(id);

    }

    @Override
    public int updateMember(Member member) {
        return memberMapper.updateMember(member);
    }

    @Override
    public int updateMemberPasswordById(String id, String password) {
        return memberMapper.updateMemberPasswordById(id, password);
    }

    @Override
    public String getMemberIdByEmailAndName(String email, String name) {
        return memberMapper.getMemberIdByEmailAndName(email, name);
    }

    @Override
    public String getMemberPasswordByIdAndEmailAndPhone(String id, String email, String phone) {
        return memberMapper.getMemberPasswordByIdAndEmailAndPhone(id, email, phone);
    }

    private byte[] getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[8];
        random.nextBytes(salt);
        return salt;
    }

}
