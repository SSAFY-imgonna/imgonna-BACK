package com.ssafy.trip.member.model.mapper;


import com.ssafy.trip.member.model.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface MemberMapper {
	int createMember(Member memberDto);
    Member getMemberByIdAndPassword(Map<String, String> map);
	int getMemberById(String id);
	int getMemberByEmail(String email);
	String getMemberIdByEmailAndName(String email, String name);
	String getMemberPasswordByIdAndEmailAndPhone(String id, String email, String phone);
	void delete(String id);
	int updateMember(Member member);
	int updateMemberPasswordById(String id, String password);
	String getSaltById(String id);
}
