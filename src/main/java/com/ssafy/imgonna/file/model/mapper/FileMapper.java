package com.ssafy.imgonna.file.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface FileMapper {

    /**
     * 파일 삭제
     */
    void deleteFile(int accompanyNo);

    /**
     * 파일 등록
     */
    void registerFile(Map<String, Object> map);


}
