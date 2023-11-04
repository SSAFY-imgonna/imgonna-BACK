package com.ssafy.trip.member.service;

import com.ssafy.trip.member.model.dto.Member;
import com.ssafy.trip.member.model.dto.MemberFind;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface MemberService {

	int createMember(Map<String, String> map);
	Member getMemberByIdAndPassword(String id, String password);
	String getMemberIdByEmailAndName(MemberFind member);
	String getMemberPasswordByIdAndEmailAndPhone(MemberFind member);
	int delete(String id, String password);

	int updateMember(Map<String, String> map, HttpSession session);

	int updateMemberPasswordById(String id, Map<String, String> map);

	Member getMemberById(String id);
}
