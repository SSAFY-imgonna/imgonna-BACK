package com.ssafy.trip.file.model.service;

import com.ssafy.trip.accompany.model.AccompanyDto;
import com.ssafy.trip.file.model.mapper.FileMapper;
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
    public void registerFile(AccompanyDto accompanyDto) {
        Map<String, Object> map = new HashMap<>();
        map.put("fileInfos", accompanyDto.getFileInfos());
        map.put("accompanyNo", accompanyDto.getAccompanyNo());
        fileMapper.registerFile(map);
    }

    @Override
    public void deleteFile(int accompanyNo) {
        fileMapper.deleteFile(accompanyNo);
    }

}
