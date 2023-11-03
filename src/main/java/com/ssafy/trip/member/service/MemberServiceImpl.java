package com.ssafy.trip.member.service;

import java.security.SecureRandom;

import com.ssafy.trip.member.model.MemberDto;
import com.ssafy.trip.member.model.dao.MemberDao;
import com.ssafy.trip.member.model.dao.MemberDaoImpl;
import com.ssafy.trip.util.PasswordUtils;

public class MemberServiceImpl implements MemberService {
	private static MemberService instance;
	private MemberDao memberDao = MemberDaoImpl.getInstance();

	public static MemberService getInstance() {
		if (instance == null) {
			instance = new MemberServiceImpl();
		}
		return instance;
	}

	@Override
	public int createMember(MemberDto memberDto) {

		byte[] salt = getSalt();
		String digest =  PasswordUtils.encode(memberDto.getPassword(), salt);
		memberDto.setPassword(digest);
		memberDto.setSalt(PasswordUtils.bytesToHex(salt));
		System.out.println("[회원가입]");
		System.out.println("raw password: " + memberDto.getPassword());
		System.out.println("salt: " + PasswordUtils.bytesToHex(salt));
		System.out.println("digest: " + digest);
		return memberDao.createMember(memberDto);
	}

	@Override
	public MemberDto getMemberByIdAndPassword(String id, String password) {
		byte[] salt = PasswordUtils.hexToBytes(memberDao.getSaltById(id));
		String digest =  PasswordUtils.encode(password, salt);
		System.out.println("[로그인]");
		System.out.println("raw password: " + password);
		System.out.println("salt: " + PasswordUtils.bytesToHex(salt));
		System.out.println("digest: " + digest);
		System.out.println("digest from DB: " + memberDao.getMemberByIdAndPassword(id, digest).getPassword());
		return memberDao.getMemberByIdAndPassword(id, digest);
	}

	@Override
	public int getMemberById(String id) {
		return memberDao.getMemberById(id);
	}

	@Override
	public int getMemberByEmail(String email) {
		return memberDao.getMemberByEmail(email);
	}

	@Override
	public void delete(String id) {
		memberDao.delete(id);

	}

	@Override
	public int updateMember(MemberDto memberDto) {
		// TODO Auto-generated method stub
		return memberDao.updateMember(memberDto);
	}

	@Override
	public int updateMemberPasswordById(String id, String password) {
		return memberDao.updateMemberPasswordById(id, password);
	}

	@Override
	public String getMemberIdByEmailAndName(String email, String name) {
		return memberDao.getMemberIdByEmailAndName(email, name);
	}

	@Override
	public String getMemberPasswordByIdAndEmailAndPhone(String id, String email, String phone) {
		return memberDao.getMemberPasswordByIdAndEmailAndPhone(id, email, phone);
	}

	private byte[] getSalt() {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[8];
		random.nextBytes(salt);
		return salt;
	}

}
