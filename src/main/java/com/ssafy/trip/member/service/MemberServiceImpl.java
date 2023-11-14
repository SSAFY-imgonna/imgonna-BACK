package com.ssafy.trip.member.service;

import com.ssafy.trip.member.model.dto.Member;
import com.ssafy.trip.member.model.dto.MemberFindRequestDto;
import com.ssafy.trip.member.model.dto.MemberLoginRequestDto;
import com.ssafy.trip.member.model.dto.MemberSignUpRequestDto;
import com.ssafy.trip.member.model.mapper.MemberMapper;
import com.ssafy.trip.member.model.enums.MemberTypeEnum;
import com.ssafy.trip.util.PasswordUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberServiceImpl implements MemberService {
    private MemberMapper memberMapper;

    public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    @Transactional
    public Member createMember(MemberSignUpRequestDto requestDto) {

        Member member = new Member();
        member.setId(requestDto.getId());
        member.setEmail(requestDto.getEmail());
        member.setName(requestDto.getName());
        member.setPhone(requestDto.getPhone());
        member.setNickname(requestDto.getNickname());
        member.setMbti(requestDto.getMbti());
        member.setIntroduction(requestDto.getIntroduction());
        member.setType(MemberTypeEnum.GENERAL);
        byte[] salt = getSalt();
        String digest = PasswordUtils.encode(requestDto.getPassword(), salt);
        member.setPassword(digest);
        member.setSalt(PasswordUtils.bytesToHex(salt));
        int result = memberMapper.createMember(member);
        if (result == 0) {
            return null;
        } else {
            MemberLoginRequestDto loginRequestDto = new MemberLoginRequestDto();
            loginRequestDto.setId(requestDto.getId());
            loginRequestDto.setPassword(requestDto.getPassword());
            return getMemberByIdAndPassword(loginRequestDto);
        }
    }

    @Override
    public Member getMemberByIdAndPassword(MemberLoginRequestDto requestDto) {
        String digest = getDigest(requestDto.getId(), requestDto.getPassword());
        if (digest == null) return null;
        requestDto.setPassword(digest);
        Member member = memberMapper.getMemberByIdAndPassword(requestDto);
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
    public int delete(String id, String password) {
        try {
            Member member = getMemberById(id);
            if (!getDigest(id, password).equals(member.getPassword())) {
                return 0;
            }
            memberMapper.deleteMember(id);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
        return 1;
    }

    @Override
    @Transactional
    public int updateMember(String id, Map<String, String> map, HttpSession session) {
        try {
            Member member = getMemberById(id);
            member.setName(map.get("name"));
            member.setPhone(map.get("phone"));
            member.setNickname(map.get("nickname"));
            member.setMbti(map.get("mbti"));
            member.setIntroduction(map.get("introduction"));
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
    @Transactional
    public int updateMemberPasswordById(String id, Map<String, String> map) {
        String oldPassword = map.get("oldPassword");
        String newPassword = map.get("newPassword");
        try {
            String digest = getDigest(id, oldPassword);
            if (!digest.equals(getMemberById(id).getPassword())) {
                return 0;
            }

            map = new HashMap<>();
            map.put("id", id);
            byte[] saltBytes = getSalt();
            String password = PasswordUtils.encode(newPassword, saltBytes);
            String salt = PasswordUtils.bytesToHex(saltBytes);
            map.put("password", password);
            map.put("salt", salt);

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
    public List<Member> getMemberList(Map<String, Object> map) {
        return memberMapper.getMemberList(map);
    }

    @Override
    public int getMemberCountById(String id) {
        return memberMapper.getMemberCountById(id);
    }

    @Override
    public String getMemberIdByEmailAndName(MemberFindRequestDto member) {
        Map<String, String> map = new HashMap<>();
        map.put("email", member.getEmail());
        map.put("name", member.getName());
        return memberMapper.getMemberIdByEmailAndName(map);
    }

    @Override
    public String getMemberPasswordByIdAndEmailAndPhone(MemberFindRequestDto member) {
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
