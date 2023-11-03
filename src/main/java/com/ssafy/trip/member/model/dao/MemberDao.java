package com.ssafy.trip.member.model.dao;


import com.ssafy.trip.member.model.Member;

public interface MemberDao {
	int createMember(Member member);
	Member getMemberByIdAndPassword(String id, String password);
	int getMemberById(String id);
	int getMemberByEmail(String email);
	String getMemberIdByEmailAndName(String email, String name);
	String getMemberPasswordByIdAndEmailAndPhone(String id, String email, String phone);
	void delete(String id);
	int updateMember(Member member);
	int updateMemberPasswordById(String id, String password);
	String getSaltById(String id);
}
