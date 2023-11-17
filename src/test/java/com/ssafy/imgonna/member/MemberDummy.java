package com.ssafy.imgonna.member;

import com.ssafy.imgonna.member.model.dto.Member;
import com.ssafy.imgonna.member.model.dto.MemberDetailsDto;
import com.ssafy.imgonna.member.model.dto.MemberSignUpRequestDto;
import com.ssafy.imgonna.member.model.enums.MemberTypeEnum;

import java.util.HashMap;
import java.util.Map;

public class MemberDummy {

    public static Member getDummyMember() {
        Member member = new Member();
        member.setId("testuser");
        member.setPassword("1234");
        member.setName("user");
        member.setPhone("01012345678");
        member.setNickname("user");
        member.setEmail("test@email.com");
        member.setMbti("esfj");
        member.setSalt("salt");
        member.setType(MemberTypeEnum.GENERAL);
        return member;
    }

    public static MemberDetailsDto getDummyMemberDetails() {
        MemberDetailsDto member = new MemberDetailsDto();
        member.setId("testuser");
        member.setName("user");
        member.setPhone("01012345678");
        member.setNickname("user");
        member.setEmail("test@email.com");
        member.setMbti("esfj");
        member.setType(MemberTypeEnum.GENERAL);
        return member;
    }

    public static MemberSignUpRequestDto getRequestDto() {
        MemberSignUpRequestDto member = new MemberSignUpRequestDto();
        member.setId("testuser");
        member.setPassword("1234");
        member.setName("user");
        member.setPhone("01012345678");
        member.setNickname("user");
        member.setEmail("test@email.com");
        member.setMbti("esfj");
        member.setType(MemberTypeEnum.GENERAL);
        return member;
    }

    public static Map getDummyMemberMap() {
        Map map = new HashMap<>();
        map.put("registerId","testUser");
        map.put("registerPw","1234");
        map.put("registerEmail","test");
        map.put("registerEmailAdd","test.com");
        map.put("registerName","testuser");
        map.put("registerPhone","010-1234-5678");
        map.put("registerNickname","nickname");
        map.put("mbti","esfj");
        map.put("introduction","hi");
        return map;
    }
}
