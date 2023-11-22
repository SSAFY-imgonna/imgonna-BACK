package com.ssafy.imgonna.member.service;

import com.ssafy.imgonna.exception.member.InvalidPasswordException;
import com.ssafy.imgonna.exception.member.MemberInfoDuplicateException;
import com.ssafy.imgonna.exception.member.MemberNotFoundException;
import com.ssafy.imgonna.member.model.dto.*;
import com.ssafy.imgonna.member.model.enums.MemberTypeEnum;
import com.ssafy.imgonna.member.model.mapper.MemberMapper;
import com.ssafy.imgonna.util.FileUtil;
import com.ssafy.imgonna.util.JWTUtil;
import com.ssafy.imgonna.util.PasswordUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberMapper memberMapper;
    private final JWTUtil jwtUtil;
    private final FileUtil fileUtil;


    public MemberServiceImpl(MemberMapper memberMapper, JWTUtil jwtUtil, FileUtil fileUtil) {
        this.memberMapper = memberMapper;
        this.jwtUtil = jwtUtil;
        this.fileUtil = fileUtil;
    }

    @Override
    @Transactional
    public MemberDetailsDto createMember(MemberSignUpRequestDto requestDto, MultipartFile upfile) throws IOException {


        requestDto.setType(MemberTypeEnum.GENERAL);
        byte[] salt = getSalt();
        String digest = PasswordUtils.encode(requestDto.getPassword(), salt);
        requestDto.setPassword(digest);
        requestDto.setSalt(PasswordUtils.bytesToHex(salt));
        //파일 업로드
        if (upfile != null) {
            String photoPath = fileUtil.uploadFile(upfile);
            requestDto.setPhoto(photoPath);
        }

        int result = memberMapper.createMember(requestDto);

        if (result == 0) {
            // TODO 회원가입 비정상 처리 예외 터뜨리기
        }

        return getMemberDetailsById(requestDto.getId());
    }

    @Override
    @Transactional
    public MemberDetailsDto updateMember(String id, MemberModifyRequestDto requestDto, MultipartFile upfile) throws IOException {

        String originPath = getMemberDetailsById(id).getPhoto();
        if (originPath != null) {
            fileUtil.removeFile(originPath);
        }
        //파일 업로드
        if (upfile != null) {
            String photoPath = fileUtil.uploadFile(upfile);
            requestDto.setPhoto(photoPath);
        }

        memberMapper.updateMember(requestDto);

        return getMemberDetailsById(id);
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
        if (cnt != 0) {
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
