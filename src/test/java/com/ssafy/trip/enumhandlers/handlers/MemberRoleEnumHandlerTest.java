package com.ssafy.trip.enumhandlers.handlers;

import com.ssafy.trip.member.model.dto.Member;
import com.ssafy.trip.member.model.mapper.MemberMapper;
import com.ssafy.trip.member.model.roleenum.MemberRoleEnum;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

class MemberRoleEnumHandlerTest {

//    @Autowired
//    private SqlSessionFactory sqlSessionFactory;
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
        dummyMember.setRole(MemberRoleEnum.GENERAL);

//        sqlSessionFactory.getConfiguration().getTypeHandlerRegistry().register(MemberRoleEnumHandler.class);
    }


    @Test
    public void handlingEnumTest() {
//        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
//            sqlSessionFactory.getConfiguration().addMapper(MemberMapper.class);
//            Member member = sqlSession.selectOne("com.ssafy.trip.member.model.mapper.MemberMapper.getMemberById", dummyMember.getId());
//            System.out.println(member);
//        }

        Member actualMember = mapper.getMemberById("ssafy");
        Assertions.assertThat(actualMember.getRole()).isEqualTo(MemberRoleEnum.GENERAL);
    }


}