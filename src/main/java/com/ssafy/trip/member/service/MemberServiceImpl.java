package com.ssafy.trip.member.service;

import com.ssafy.trip.exception.member.MemberNotFoundException;
import com.ssafy.trip.member.model.dto.*;
import com.ssafy.trip.member.model.mapper.MemberMapper;
import com.ssafy.trip.member.model.enums.MemberTypeEnum;
import com.ssafy.trip.util.JWTUtil;
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
    private final MemberMapper memberMapper;
    private final JWTUtil jwtUtil;


    public MemberServiceImpl(MemberMapper memberMapper, JWTUtil jwtUtil) {
        this.memberMapper = memberMapper;
        this.jwtUtil = jwtUtil;
    }

    @Override
    @Transactional
    public MemberDetailsDto createMember(MemberSignUpRequestDto requestDto) {

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
            return getMemberDetailsByIdAndPassword(loginRequestDto);
        }
    }

    @Override
    public Member getMemberByIdAndPassword(MemberLoginRequestDto requestDto) throws MemberNotFoundException {
        String digest = getDigest(requestDto.getId(), requestDto.getPassword());
        requestDto.setPassword(digest);
        Member member = memberMapper.getMemberByIdAndPassword(requestDto);

        if (member == null) {
            throw new MemberNotFoundException();
        } else {
            return member;
        }
    }

    @Override
    public MemberDetailsDto getMemberDetailsByIdAndPassword(MemberLoginRequestDto requestDto) {
        String digest = getDigest(requestDto.getId(), requestDto.getPassword());
        requestDto.setPassword(digest);
        MemberDetailsDto member = memberMapper.getMemberDetailsByIdAndPassword(requestDto);

        if (member == null) {
            throw new MemberNotFoundException();
        } else {
            return member;
        }
    }

    private String getDigest(String id, String password) {
        String salt = memberMapper.getSaltById(id);

        if (salt == null) {
            throw new MemberNotFoundException();
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
            Member member = getMemberByIdAndPassword(new MemberLoginRequestDto(id, password));
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
    public Member updateMember(String id, MemberModifyRequestDto requestDto) {
        try {
            MemberDetailsDto member = getMemberById(id);
            member.setName(requestDto.getName());
            member.setPhone(requestDto.getPhone());
            member.setNickname(requestDto.getNickname());
            member.setMbti(requestDto.getMbti());
            member.setIntroduction(requestDto.getIntroduction());
            memberMapper.updateMember(member);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        MemberLoginRequestDto loginRequestDto = new MemberLoginRequestDto();
        loginRequestDto.setId(id);
        loginRequestDto.setPassword(requestDto.getPassword());
        return getMemberByIdAndPassword(loginRequestDto);
    }


    private void updateSession(HttpSession session, Member member) {
        Member memberDto = (Member) session.getAttribute("memberDto");
        memberDto.setNickname(member.getNickname());
        session.setAttribute("memberDto", memberDto);
    }

    @Override
    @Transactional
    public Member updateMemberPasswordById(String id, MemberModifyPwRequestDto requestDto) {
        try {

            String oldPassword = requestDto.getOldPassword();
            String digest = getDigest(id, oldPassword);
            String actualPassword = getMemberByIdAndPassword(new MemberLoginRequestDto(id, oldPassword)).getPassword();
            if (!digest.equals(actualPassword)) {
                return null;
            }

            HashMap map = new HashMap<>();
            map.put("id", id);
            byte[] saltBytes = getSalt();
            String password = PasswordUtils.encode(requestDto.getNewPassword(), saltBytes);
            String salt = PasswordUtils.bytesToHex(saltBytes);
            map.put("password", password);
            map.put("salt", salt);

            memberMapper.updateMemberPasswordById(map);

        } catch (
                Exception ex) {
            System.out.println(ex.getMessage());
        }
        MemberLoginRequestDto loginRequestDto = new MemberLoginRequestDto();
        loginRequestDto.setId(id);
        loginRequestDto.setPassword(requestDto.getNewPassword());
        return getMemberByIdAndPassword(loginRequestDto);
    }

    @Override
    public MemberDetailsDto getMemberById(String id) {
        MemberDetailsDto member = memberMapper.getMemberById(id);
        if (member == null) {
            throw new MemberNotFoundException();
        } else {
            return member;
        }
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
    public void saveRefreshToken(String id, String refreshToken) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("token", refreshToken);
        memberMapper.saveRefreshToken(map);
    }

    @Override
    public void deleteRefreshToken(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("token", null);
        memberMapper.deleteRefreshToken(map);
    }

    @Override
    public MemberLoginResponseDto loginMember(MemberLoginRequestDto requestDto) {

        MemberDetailsDto member = getMemberDetailsByIdAndPassword(requestDto);

        MemberLoginResponseDto responseDto = null;

        String accessToken = null;
        String refreshToken = null;

        if (member != null) {
            accessToken = jwtUtil.createAccessToken(requestDto.getId());
            refreshToken = jwtUtil.createRefreshToken(requestDto.getId());

            saveRefreshToken(requestDto.getId(), refreshToken);

            responseDto = new MemberLoginResponseDto(accessToken, refreshToken);
        }
        return responseDto;
    }

    @Override
    public MemberDetailsDto getInfo(String id, String authorization) {
        MemberDetailsDto member = null;

        if (jwtUtil.isValidToken(authorization)) {
            member = getMemberById(id);
        }

        return member;
    }

    @Override
    public String refreshToken(String token, String id) throws Exception {
        String accessToken = null;

        if (jwtUtil.isValidToken(token)) {
            if (token.equals(getRefreshToken(id))) {
                accessToken = jwtUtil.createAccessToken(id);
            }
        }

        return accessToken;
    }

    @Override
    public Object getRefreshToken(String id) throws Exception {
        return memberMapper.getRefreshToken(id);
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
