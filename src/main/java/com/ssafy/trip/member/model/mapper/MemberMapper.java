package com.ssafy.trip.member.model.mapper;


import com.ssafy.trip.member.model.dto.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemberMapper {
	int createMember(Member memberDto);
    Member getMemberByIdAndPassword(Map<String, String> map);
	String getSaltById(String id);
	String getMemberIdByEmailAndName(Map<String, String> map);
	String getMemberPasswordByIdAndEmailAndPhone(Map<String, String> map);
	void deleteMember(String id);

	void updateMember(Member member);
	void updateMemberPasswordById(Map<String, String> map);

	Member getMemberById(String id);

	List<Member> getMemberList(Map<String, Object> map);
}
