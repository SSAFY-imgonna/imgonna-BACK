package com.ssafy.trip.member.service;

import com.ssafy.trip.member.model.dto.*;

import java.util.List;
import java.util.Map;

public interface MemberService {

	MemberDetailsDto createMember(MemberSignUpRequestDto requestDto);
	Member getMemberByIdAndPassword(MemberLoginRequestDto requestDto);

	Object getRefreshToken(String id) throws Exception;

	String getMemberIdByEmailAndName(MemberFindRequestDto member);
	String getMemberPasswordByIdAndEmailAndPhone(MemberFindRequestDto member);

	MemberDetailsDto getMemberDetailsByIdAndPassword(MemberLoginRequestDto requestDto);

	int delete(String id, String password);

	MemberDetailsDto updateMember(String id, MemberModifyRequestDto requestDto);

	void updateMemberPasswordById(String id, MemberModifyPwRequestDto requestDto);

	Member getMemberById(String id);

	MemberDetailsDto getMemberDetailsById(String id);

	List<Member> getMemberList(Map<String, Object> map);

    int getMemberCountById(String id);

	void saveRefreshToken(String id, String refreshToken);

	void deleteRefreshToken(String id);

	MemberLoginResponseDto loginMember(MemberLoginRequestDto requestDto);

	MemberDetailsDto getInfo(String id, String authorization);

	String refreshToken(String token, String id) throws Exception;
}
