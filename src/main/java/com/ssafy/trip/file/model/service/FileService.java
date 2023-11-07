package com.ssafy.trip.file.model.service;

import com.ssafy.trip.accompany.model.Accompany;

public interface FileService {
    void registerFile(Accompany accompanyDto);

    void deleteFile(int accompanyNo);

}
