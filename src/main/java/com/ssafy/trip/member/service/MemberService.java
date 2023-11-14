package com.ssafy.trip.member.service;

import com.ssafy.trip.member.model.dto.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface MemberService {

	Member createMember(MemberSignUpRequestDto requestDto);
	Member getMemberByIdAndPassword(MemberLoginRequestDto requestDto);
	String getMemberIdByEmailAndName(MemberFindRequestDto member);
	String getMemberPasswordByIdAndEmailAndPhone(MemberFindRequestDto member);
	int delete(String id, String password);

	Member updateMember(String id, Map<String, String> map, HttpSession session);

	Member updateMemberPasswordById(String id, MemberModifyPwRequestDto requestDto);

	Member getMemberById(String id);

	List<Member> getMemberList(Map<String, Object> map);

    int getMemberCountById(String id);
}
