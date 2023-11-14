package com.ssafy.trip.member.service;

import com.ssafy.trip.member.model.dto.Member;
import com.ssafy.trip.member.model.dto.MemberFindRequestDto;
import com.ssafy.trip.member.model.dto.MemberLoginRequestDto;
import com.ssafy.trip.member.model.dto.MemberSignUpRequestDto;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface MemberService {

	Member createMember(MemberSignUpRequestDto requestDto);
	Member getMemberByIdAndPassword(MemberLoginRequestDto requestDto);
	String getMemberIdByEmailAndName(MemberFindRequestDto member);
	String getMemberPasswordByIdAndEmailAndPhone(MemberFindRequestDto member);
	int delete(String id, String password);

	int updateMember(String id, Map<String, String> map, HttpSession session);

	int updateMemberPasswordById(String id, Map<String, String> map);

	Member getMemberById(String id);

	List<Member> getMemberList(Map<String, Object> map);

    int getMemberCountById(String id);
}
