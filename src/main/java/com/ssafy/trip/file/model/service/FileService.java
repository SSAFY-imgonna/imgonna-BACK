package com.ssafy.trip.file.model.service;

import com.ssafy.trip.accompany.model.dto.Accompany;
import com.ssafy.trip.accompany.model.dto.AccompanyRequestDto;

public interface FileService {
    void registerFile(AccompanyRequestDto accompanyRequestDto);

    void deleteFile(int accompanyNo);

}
