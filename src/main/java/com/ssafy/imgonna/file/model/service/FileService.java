package com.ssafy.imgonna.file.model.service;

import com.ssafy.imgonna.accompany.model.dto.AccompanyRequestDto;
import com.ssafy.imgonna.diary.model.dto.DiaryRequestDto;

public interface FileService {
    void registerFile(AccompanyRequestDto accompanyRequestDto);
    void registerFile(DiaryRequestDto diaryRequestDto);

    void deleteFile(int accompanyNo);

}
