package com.ssafy.imgonna.diary.model.dto;

import com.ssafy.imgonna.attraction.model.dto.AttractionInfo;

import java.util.List;

public class RankResponseDto {
    private String id;
    private List<AttractionInfo> attractions;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<AttractionInfo> getAttractions() {
        return attractions;
    }

    public void setAttractions(List<AttractionInfo> attractions) {
        this.attractions = attractions;
    }
}
