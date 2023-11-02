package com.ssafy.trip.member.model;

import java.io.Serializable;

public class MemberDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String email;
	private String name;
	private String password;
	private String phone;
	private String nickname;
	private String mbti;
	private String introduction;
	private String joinDate;
	private String role;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getSalt() {
		return salt;
	}
	
	public MemberDto() {
		super();
	}
	
	public MemberDto(String id, String email, String name, String password, String phone, String nickname,
			String joinDate, String role, String salt) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.nickname = nickname;
		this.joinDate = joinDate;
		this.role = role;
		this.salt = salt;
	}
	
	public MemberDto(String id, String email, String name, String password, String phone, String nickname,
			String mbti, String introduction, String joinDate, String role) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.nickname = nickname;
		this.mbti = mbti;
		this.introduction = introduction;
		this.joinDate = joinDate;
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "MemberDto [id=" + id + ", email=" + email + ", name=" + name + ", password=" + password + ", phone="
				+ phone + ", nickname=" + nickname + ", mbti=" + mbti + ", introduction=" + introduction + ", joinDate="
				+ joinDate + ", role=" + role + ", salt=" + salt + "]";
	}
}