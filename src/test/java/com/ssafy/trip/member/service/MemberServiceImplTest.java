package com.ssafy.trip.member.service;

import com.ssafy.trip.member.MemberDummy;
import com.ssafy.trip.member.model.dto.Member;
import com.ssafy.trip.member.model.dto.MemberDetailsDto;
import com.ssafy.trip.member.model.dto.MemberSignUpRequestDto;
import com.ssafy.trip.member.model.mapper.MemberMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

/**
 * @author yihoney
 */
@ExtendWith(SpringExtension.class) // Spring Test Context 프레임워크 + Junit5와 통합해 사용할 때 명시
@ContextConfiguration(classes = MemberServiceImpl.class)
class MemberServiceImplTest {

    // 테스트 할 서비스 빈
    @Autowired
    MemberService memberService;

    @MockBean
    MemberMapper memberMapper;

    MemberSignUpRequestDto dummyRequestDto;
    MemberDetailsDto dummyMember;

    @BeforeEach
    void setUp() {
        dummyRequestDto = MemberDummy.getRequestDto();
        dummyMember = MemberDummy.getDummyMemberDetails();
    }

    @Test
    @DisplayName("회원 등록 테스트")
    void createMember() {

        given(memberMapper.createMember(any(Member.class)))
                .willReturn(1);

        MemberDetailsDto result = memberService.createMember(dummyRequestDto);

        Assertions.assertThat(result).isNotNull();

    }

    @Test
    @DisplayName("아이디로 회원 상세정보 단건 조회 테스트")
    void getMemberById() {

        given(memberMapper.getMemberById(any(String.class)))
                .willReturn(dummyMember);

        MemberDetailsDto actualMember = memberService.getMemberById(dummyMember.getId());

        Assertions.assertThat(actualMember).isNotNull();
        Assertions.assertThat(actualMember.getEmail()).isEqualTo(dummyMember.getEmail());

    }
}