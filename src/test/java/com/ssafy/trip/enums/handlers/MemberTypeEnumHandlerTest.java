package com.ssafy.trip.enums.handlers;

import com.ssafy.trip.member.model.dto.Member;
import com.ssafy.trip.member.model.dto.MemberDetailsDto;
import com.ssafy.trip.member.model.mapper.MemberMapper;
import com.ssafy.trip.member.model.enums.MemberTypeEnum;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 실데이터베이스 사용 시
class MemberTypeEnumHandlerTest {

    Member dummyMember;

    @Autowired
    MemberMapper mapper;

    // 각 테스트 메서드 실행 시 매번 실행되는 메서드 (주로 초기 데이터 설정 시  사용)
    @BeforeEach
    void setup() {
        dummyMember = new Member();
        dummyMember.setId("testuser");
        dummyMember.setPassword("1234");
        dummyMember.setName("user");
        dummyMember.setPhone("01012345678");
        dummyMember.setNickname("user");
        dummyMember.setEmail("test@email.com");
        dummyMember.setMbti("esfj");
        dummyMember.setSalt("salt");
        dummyMember.setType(MemberTypeEnum.GENERAL);
    }


    @Test
    @Transactional
    public void handlingEnumTest() {
        mapper.createMember(dummyMember);
        MemberDetailsDto actualMember = mapper.getMemberById("testuser");
        Assertions.assertThatNoException();
        Assertions.assertThat(actualMember.getType()).isEqualTo(MemberTypeEnum.GENERAL);
    }


}