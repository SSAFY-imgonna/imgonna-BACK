package com.ssafy.imgonna.file.model.service;

import com.ssafy.imgonna.accompany.model.dto.AccompanyRequestDto;
import com.ssafy.imgonna.diary.model.dto.DiaryRequestDto;
import com.ssafy.imgonna.file.model.mapper.FileMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FileServiceImpl implements FileService {

    private FileMapper fileMapper;

    public FileServiceImpl(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    @Override
    public void registerFile(AccompanyRequestDto accompanyRequestDto) {
        Map<String, Object> map = new HashMap<>();
        map.put("fileInfos", accompanyRequestDto.getFileInfos());
        map.put("accompanyNo", accompanyRequestDto.getAccompanyNo());
        fileMapper.registerFile(map);
    }

    @Override
    public void registerFile(DiaryRequestDto diaryRequestDto) {
        Map<String, Object> map = new HashMap<>();
        map.put("fileInfos", diaryRequestDto.getFileInfos());
        map.put("diaryNo", diaryRequestDto.getDiaryNo());
        fileMapper.registerFile(map);
    }

    @Override
    public void deleteFile(int accompanyNo) {
        fileMapper.deleteFile(accompanyNo);
    }


}
