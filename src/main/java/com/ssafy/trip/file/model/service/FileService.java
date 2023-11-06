package com.ssafy.trip.file.model.service;

import com.ssafy.trip.accompany.model.AccompanyDto;

public interface FileService {
    void registerFile(AccompanyDto accompanyDto);

    void deleteFile(int accompanyNo);

}
