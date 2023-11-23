package com.ssafy.imgonna.member.service;

import com.ssafy.imgonna.member.model.dto.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface MemberService {

	MemberDetailsDto createMember(MemberSignUpRequestDto requestDto, MultipartFile upfile) throws IOException;
	Member getMemberByIdAndPassword(MemberLoginRequestDto requestDto);

	Object getRefreshToken(String id) throws Exception;

	String getMemberIdByEmailAndName(MemberFindRequestDto member);
	String getMemberPasswordByIdAndEmailAndPhone(MemberFindRequestDto member);

	MemberDetailsDto getMemberDetailsByIdAndPassword(MemberLoginRequestDto requestDto);

	void delete(String id, MemberDeleteRequestDto password);

	MemberDetailsDto updateMember(String id, MemberModifyRequestDto requestDto, MultipartFile upfile) throws IOException;

	void updateMemberPasswordById(String id, MemberModifyPwRequestDto requestDto);

	Member getMemberById(String id);

	MemberDetailsDto getMemberDetailsById(String id);

	List<Member> getMemberList(Map<String, Object> map);

	void checkDuplicateId(String id);

	void saveRefreshToken(String id, String refreshToken);

	void deleteRefreshToken(String id);


	MemberLoginResponseDto loginMember(MemberLoginRequestDto requestDto);

	MemberDetailsDto getInfo(String id, String authorization);

	String refreshToken(String token, String id) throws Exception;
}
