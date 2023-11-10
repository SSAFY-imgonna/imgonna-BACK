package com.ssafy.trip.member.model.mapper;

import com.ssafy.trip.member.MemberDummy;
import com.ssafy.trip.member.model.dto.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;


@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberMapperTest {

    Member dummyMember;
    @Autowired
    MemberMapper memberMapper;

    // 각 테스트 메서드 실행 시 매번 실행되는 메서드 (주로 초기 데이터 설정 시  사용)
    @BeforeEach
    void setup() {
        dummyMember = MemberDummy.getDummyMember();
    }
    @Test
    void createMember() {
        memberMapper.createMember(dummyMember);

        Member actualMember = memberMapper.getMemberById(dummyMember.getId());

        Assertions.assertThat(actualMember.getType()).isEqualTo(dummyMember.getType());
        Assertions.assertThat(actualMember.getEmail()).isEqualTo(dummyMember.getEmail());
    }

    @Test
    void getMemberById() {

        Member actualMember = memberMapper.getMemberById(dummyMember.getId());

        Assertions.assertThat(actualMember.getType()).isEqualTo(dummyMember.getType());
        Assertions.assertThat(actualMember.getEmail()).isEqualTo(dummyMember.getEmail());
    }
}