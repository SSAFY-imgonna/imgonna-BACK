package com.ssafy.imgonna.member.model.dto;

public class MemberLoginRequestDto {
    private String id;
    private String password;

    public MemberLoginRequestDto() {
    }

    public MemberLoginRequestDto(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
