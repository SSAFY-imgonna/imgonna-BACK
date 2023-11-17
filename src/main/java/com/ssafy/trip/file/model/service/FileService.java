package com.ssafy.trip.file.model.service;

import com.ssafy.trip.accompany.model.dto.Accompany;
import com.ssafy.trip.accompany.model.dto.AccompanyRequestDto;
import com.ssafy.trip.diary.model.dto.DiaryRequestDto;

public interface FileService {
    void registerFile(AccompanyRequestDto accompanyRequestDto);
    void registerFile(DiaryRequestDto diaryRequestDto);

    void deleteFile(int accompanyNo);

}
