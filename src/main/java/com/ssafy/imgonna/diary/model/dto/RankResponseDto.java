package com.ssafy.imgonna.diary.model.dto;

import com.ssafy.imgonna.attraction.model.dto.AttractionInfo;
import com.ssafy.imgonna.member.model.dto.MemberDetailsDto;

import java.util.List;

public class RankResponseDto {
    private MemberDetailsDto member;
    private List<AttractionInfo> attractions;


    public MemberDetailsDto getMember() {
        return member;
    }

    public void setMember(MemberDetailsDto member) {
        this.member = member;
    }

    public List<AttractionInfo> getAttractions() {
        return attractions;
    }

    public void setAttractions(List<AttractionInfo> attractions) {
        this.attractions = attractions;
    }
}
