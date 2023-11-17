package com.ssafy.trip.member.model.mapper;


import com.ssafy.trip.member.model.dto.Member;
import com.ssafy.trip.member.model.dto.MemberDetailsDto;
import com.ssafy.trip.member.model.dto.MemberLoginRequestDto;
import com.ssafy.trip.member.model.dto.MemberModifyRequestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemberMapper {
	int createMember(Member memberDto);
    Member getMemberByIdAndPassword(MemberLoginRequestDto requestDto);
    MemberDetailsDto getMemberDetailsByIdAndPassword(MemberLoginRequestDto requestDto);
	String getSaltById(String id);
	String getMemberIdByEmailAndName(Map<String, String> map);
	String getMemberPasswordByIdAndEmailAndPhone(Map<String, String> map);
	void deleteMember(String id);

	void updateMember(MemberModifyRequestDto member);
	void updateMemberPasswordById(Map<String, String> map);

	Member getMemberById(String id);

	List<Member> getMemberList(Map<String, Object> map);

    int getMemberCountById(String id);

    void saveRefreshToken(Map<String, String> map);

	void deleteRefreshToken(Map<String, String> map);

	Object getRefreshToken(String id);

	MemberDetailsDto getMemberDetailsById(String id);
}
