package com.ssafy.imgonna.member.model.dto;

import com.ssafy.imgonna.member.model.enums.MemberTypeEnum;

public class Member {
	private String id;
	private String name;
	private String password;
	private String phone;
	private String nickname;
	private String introduction;
	private String joinDate;
	private String email;
	private String salt;
	private String mbti;
	private MemberTypeEnum type;
	private String photo;
	private String token;

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

	public MemberTypeEnum getType() {
		return type;
	}

	public void setType(MemberTypeEnum type) {
		this.type = type;
	}

	public String getSalt() {
		return salt;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Member() {
		super();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "Member{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				", phone='" + phone + '\'' +
				", nickname='" + nickname + '\'' +
				", introduction='" + introduction + '\'' +
				", joinDate='" + joinDate + '\'' +
				", email='" + email + '\'' +
				", salt='" + salt + '\'' +
				", mbti='" + mbti + '\'' +
				", type=" + type +
				", photo='" + photo + '\'' +
				'}';
	}
}