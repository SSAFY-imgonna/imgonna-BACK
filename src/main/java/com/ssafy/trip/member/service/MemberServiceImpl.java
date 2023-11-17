package com.ssafy.trip.member.service;

import com.ssafy.trip.exception.member.InvalidPasswordException;
import com.ssafy.trip.exception.member.MemberInfoDuplicateException;
import com.ssafy.trip.exception.member.MemberNotFoundException;
import com.ssafy.trip.member.model.dto.*;
import com.ssafy.trip.member.model.enums.MemberTypeEnum;
import com.ssafy.trip.member.model.mapper.MemberMapper;
import com.ssafy.trip.util.JWTUtil;
import com.ssafy.trip.util.PasswordUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void delete(String id, MemberDeleteRequestDto requestDto) {
        String password = requestDto.getPassword();
        Member member = getMemberByIdAndPassword(new MemberLoginRequestDto(id, password));
        if (!getDigest(id, password).equals(member.getPassword())) {
            throw new MemberNotFoundException();
        }
        memberMapper.deleteMember(id);

    }

    @Override
    @Transactional
    public MemberDetailsDto updateMember(String id, MemberModifyRequestDto requestDto) {
        try {
            memberMapper.updateMember(requestDto);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return getMemberDetailsById(id);
    }

    @Override
    @Transactional
    public void updateMemberPasswordById(String id, MemberModifyPwRequestDto requestDto) {
        String oldPassword = requestDto.getOldPassword();

        HashMap map = new HashMap<String, String>();

        String actualPassword = getMemberById(id).getPassword();

        if (!oldPassword.equals(actualPassword)) {
            Member member = getMemberByIdAndPassword(new MemberLoginRequestDto(id, oldPassword));
            if (member == null) {
                throw new InvalidPasswordException();
            }
        }
        byte[] saltBytes = getSalt();
        String password = PasswordUtils.encode(requestDto.getNewPassword(), saltBytes);
        String salt = PasswordUtils.bytesToHex(saltBytes);
        map.put("salt", salt);

        map.put("id", id);
        map.put("password", password);

        memberMapper.updateMemberPasswordById(map);

    }

    @Override
    public Member getMemberById(String id) {
        Member member = memberMapper.getMemberById(id);
        if (member == null) {
            throw new MemberNotFoundException();
        } else {
            return member;
        }
    }

    @Override
    public MemberDetailsDto getMemberDetailsById(String id) {
        MemberDetailsDto member = memberMapper.getMemberDetailsById(id);
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
    public void checkDuplicateId(String id) {
        int cnt = memberMapper.getMemberCountById(id);
        if(cnt!=0) {
            throw new MemberInfoDuplicateException("아이디");
        }
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

        String accessToken;
        String refreshToken;

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
            member = getMemberDetailsById(id);
        }

        return member;
    }

    @Override
    public String refreshToken(String token, String id) {
        String accessToken = null;

        if (jwtUtil.isValidToken(token)) {
            if (token.equals(getRefreshToken(id))) {
                accessToken = jwtUtil.createAccessToken(id);
            }
        }

        return accessToken;
    }

    @Override
    public Object getRefreshToken(String id) {
        return memberMapper.getRefreshToken(id);
    }

    @Override
    public String getMemberIdByEmailAndName(MemberFindRequestDto member) {
        Map<String, String> map = new HashMap<>();
        map.put("email", member.getEmail());
        map.put("name", member.getName());
        String id = memberMapper.getMemberIdByEmailAndName(map);
        if (id == null) {
            throw new MemberNotFoundException();
        }
        return id;
    }

    @Override
    public String getMemberPasswordByIdAndEmailAndPhone(MemberFindRequestDto member) {
        Map<String, String> map = new HashMap<>();
        map.put("id", member.getId());
        map.put("email", member.getEmail());
        map.put("phone", member.getPhone());
        String password = memberMapper.getMemberPasswordByIdAndEmailAndPhone(map);
        if (password == null) {
            throw new MemberNotFoundException();
        }
        return password;
    }

    private byte[] getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[8];
        random.nextBytes(salt);
        return salt;
    }

}
