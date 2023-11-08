package com.ssafy.trip.member.service;

import com.ssafy.trip.member.model.dto.Member;
import com.ssafy.trip.member.model.mapper.MemberMapper;
import com.ssafy.trip.member.model.enums.MemberTypeEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.anyString;
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

    @Test
    void createMember() {
        Member member = new Member();
        member.setId("testuser");
        member.setType(MemberTypeEnum.GENERAL);

        given(memberMapper.getMemberById(anyString()))
                .willReturn(member);

    }
}