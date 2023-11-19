package com.ssafy.imgonna.diary.service;

import com.ssafy.imgonna.diary.model.dto.*;
import com.ssafy.imgonna.diary.model.mapper.DiaryMapper;
import com.ssafy.imgonna.file.model.dto.FileInfoDto;
import com.ssafy.imgonna.file.model.service.FileService;
import com.ssafy.imgonna.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DiaryServiceImpl implements DiaryService {
    private DiaryMapper diaryMapper;
    private FileService fileService;
    private final MemberService memberService;

    @Autowired
    public DiaryServiceImpl(DiaryMapper diaryMapper, FileService fileService, MemberService memberService) {
        super();
        this.diaryMapper = diaryMapper;
        this.fileService = fileService;
        this.memberService = memberService;
    }

    // 관광지명에 따른 관광지 목록
    @Override
    public List<AttractionResponseDto> getAttractionListByTitle(String title) {
        return diaryMapper.getAttractionListByTitle(title);
    }

    // 여행일기 목록
    @Override
    public DiaryListResponseDto getDiaryList(Map<String, String> map) {
        Map<String, Object> param = new HashMap<>();
        param.put("word", map.get("word") == null ? "" : map.get("word"));
        int currentPage = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
        int sizePerPage = Integer.parseInt(map.get("spp") == null ? "9" : map.get("spp"));

        int start = currentPage * sizePerPage - sizePerPage;
        param.put("start", start);
        param.put("listsize", sizePerPage);

        String key = map.get("key");
        param.put("key", key == null ? "" : key);

        List<DiaryResponseDto> diaryList = diaryMapper.getDiaryList(param);

        int totalArticleCount = diaryMapper.getTotalDiaryCount(param);
        int totalPageCount = (totalArticleCount - 1) / sizePerPage + 1;


        DiaryListResponseDto diaryListResponseDto = new DiaryListResponseDto();
        diaryListResponseDto.setDiaryList(diaryList);
        diaryListResponseDto.setCurrentPage(currentPage);
        diaryListResponseDto.setTotalPageCount(totalPageCount);

        return diaryListResponseDto;
    }

    // 여행일기 작성
    @Override
    @Transactional
    public void createDiary(DiaryRequestDto diaryRequestDto) {
        // 여행일기 작성
        diaryMapper.createDiary(diaryRequestDto);
        // 이미지 저장
        List<FileInfoDto> fileInfos = diaryRequestDto.getFileInfos();
        if (fileInfos != null && !fileInfos.isEmpty()) { // 파일 정보가 있다면
            fileService.registerFile(diaryRequestDto);
        }
    }

    // 여행일기 상세
    @Override
    public DiaryResponseDto getDiaryByDiaryNo(int diaryNo) {
        return diaryMapper.getDiaryByDiaryNo(diaryNo);
    }

    // 여행일기 수정
    @Override
    @Transactional
    public void modifyDiary(DiaryRequestDto diaryRequestDto, Map<String, String> map) {
        // 여행일기 글 수정
        diaryMapper.modifyDiary(diaryRequestDto);

        List<FileInfoDto> fileInfos = diaryRequestDto.getFileInfos();
        // 기존 파일도 존재하지 않고, 수정하고자 하는 파일이 존재하지 않다면 파일 삭제
        if ((fileInfos == null || fileInfos.isEmpty()) && map.get("originFile").isEmpty()) {
            // 로컬에 저장된 기존 파일 정보 삭제

            // 기존 파일 정보 삭제
            fileService.deleteFile("travel_diary", diaryRequestDto.getDiaryNo());
        }

        // 기존 파일 존재하고, 수정하고자 하는 파일이 존재한다면 => 기존 파일 삭제 후 수정 파일 등록
        if (fileInfos != null && !fileInfos.isEmpty()) {
            // 로컬에 저장된 기존 파일 정보 삭제

            // 기존 파일 정보 삭제
            fileService.deleteFile("travel_diary", diaryRequestDto.getDiaryNo());
            // 새 파일 등록
            fileService.registerFile(diaryRequestDto);
        }
    }

    // 여행일기 삭제
    @Override
    public void deleteDiary(int diaryNo, String uploadPath) {
        // 로컬에 저장된 파일 정보 삭제
        deleteFiles(diaryNo, uploadPath);
        // 파일 정보 삭제
        fileService.deleteFile("travel_diary", diaryNo);

        // 여행일기 글 정보 삭제
        diaryMapper.deleteDiary(diaryNo);
    }

    @Override
    public List<RankResponseDto> getRankList() {
        List<RankResponseDto> rankList = new ArrayList<>();
        List<String> rankIdList = diaryMapper.getRankList();
        for (String id : rankIdList) {
            RankResponseDto responseDto = new RankResponseDto();
            responseDto.setMember(memberService.getMemberDetailsById(id));
            responseDto.setAttractions(diaryMapper.getAttractionListById(id));
            rankList.add(responseDto);
        }
        return rankList;
    }

    // 파일들 로컬 컴퓨터에서 삭제
    public void deleteFiles(int diaryNo, String uploadPath) {
        List<FileInfoDto> fileList = diaryMapper.fileInfoList(diaryNo);
        for (FileInfoDto fileInfoDto : fileList) {
            File file = new File(uploadPath + File.separator + fileInfoDto.getSaveFolder() + File.separator + fileInfoDto.getSaveFile());
            file.delete();
        }
    }

}
