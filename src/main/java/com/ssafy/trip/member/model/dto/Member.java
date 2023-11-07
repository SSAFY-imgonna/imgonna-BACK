package com.ssafy.trip.member.model.dto;

import com.ssafy.trip.member.model.roleenum.MemberRoleEnum;

public class Member {
	private String id;
	private String email;
	private String name;
	private String password;
	private String phone;
	private String nickname;
	private String mbti;
	private String introduction;
	private String joinDate;
	private MemberRoleEnum role;
	private String salt;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getMbti() {
		return mbti;
	}
	public void setMbti(String mbti) {
		this.mbti = mbti;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public MemberRoleEnum getRole() {
		return role;
	}

	public void setRole(MemberRoleEnum role) {
		this.role = role;
	}

	public String getSalt() {
		return salt;
	}
	
	public Member() {
		super();
	}

	@Override
	public String toString() {
		return "MemberDto [id=" + id + ", email=" + email + ", name=" + name + ", password=" + password + ", phone="
				+ phone + ", nickname=" + nickname + ", mbti=" + mbti + ", introduction=" + introduction + ", joinDate="
				+ joinDate + ", role=" + role + ", salt=" + salt + "]";
	}
}