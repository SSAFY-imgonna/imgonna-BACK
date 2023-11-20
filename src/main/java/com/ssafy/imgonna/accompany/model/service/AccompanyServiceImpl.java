package com.ssafy.imgonna.accompany.model.service;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.imgonna.file.model.service.FileService;
import com.ssafy.imgonna.notify.model.dto.Notify;
import com.ssafy.imgonna.notify.model.service.NotifyService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.imgonna.file.model.dto.FileInfoDto;
import com.ssafy.imgonna.accompany.model.dto.Accompany;
import com.ssafy.imgonna.accompany.model.dto.AccompanyRequestDto;
import com.ssafy.imgonna.accompany.model.dto.AccompanyResponseDto;
import com.ssafy.imgonna.accompany.model.mapper.AccompanyMapper;
import com.ssafy.imgonna.exception.accompany.InvalidAccompanyDataException;
import com.ssafy.imgonna.exception.member.MemberNotFoundException;

@Service
public class AccompanyServiceImpl implements AccompanyService {

    private AccompanyMapper accompanyMapper;
    private FileService fileService;
    private NotifyService notifyService;
    
    public AccompanyServiceImpl(AccompanyMapper accompanyMapper, FileService fileService, NotifyService notifyService) {
		super();
		this.accompanyMapper = accompanyMapper;
		this.fileService = fileService;
		this.notifyService = notifyService;
	}

	// 동행 글 작성
    @Override
    @Transactional
    public void createAccompany(AccompanyRequestDto accompanyRequestDto) {
    	// 동행 글 작성
    	System.out.println("글 입력 전 Accompany : " + accompanyRequestDto);
    	accompanyMapper.createAccompany(accompanyRequestDto);
    	System.out.println("글 입력 후 AccompanyDto : " + accompanyRequestDto);
    	
    	// 이미지 저장
    	List<FileInfoDto> fileInfos = accompanyRequestDto.getFileInfos();
    	if (fileInfos != null && !fileInfos.isEmpty()) { // 파일 정보가 있다면
    		fileService.registerFile(accompanyRequestDto);
    	}
    }
    
    // 동행 글 목록
    @Override
    public List<Accompany> getAccompanyList(Map<String, String> map) {
    	
    	// 나중에 검색어 조회시에 변경해주어야!!!
//        Map<String, Object> param = new HashMap<String, Object>();
//        String key = map.get("key");
//		if("userid".equals(key))
//			key = "b.user_id";
//        param.put("key", key == null ? "" : key);
//        param.put("word", map.get("word") == null ? "" : map.get("word"));
//		int pgNo = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
//		int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
//		param.put("start", start);
//		param.put("listsize", SizeConstant.LIST_SIZE);
    	
//    	String cat = map.get("cat");
    	
        return accompanyMapper.getAccompanyList(map);
    }

    // 조회수 증가
    @Override
    public void updateHit(int accompanyNo) {
    	accompanyMapper.updateHit(accompanyNo);
    }
    
    // 동행 글 상세
    @Override
    public AccompanyResponseDto getAccompanyByAccompanyNo(int accompanyNo) {
        return accompanyMapper.getAccompanyByAccompanyNo(accompanyNo);
    }

    // 동행 글 수정
    @Override
    @Transactional
    public void modifyAccompany(AccompanyRequestDto accompanyRequestDto, Map<String, String> map) throws SQLException {
    	// 동행 글 수정
        accompanyMapper.modifyAccompany(accompanyRequestDto);

        List<FileInfoDto> fileInfos = accompanyRequestDto.getFileInfos();
        // 기존 파일도 존재하지 않고, 수정하고자 하는 파일이 존재하지 않다면 파일 삭제
	    if ((fileInfos == null || fileInfos.isEmpty()) && map.get("originFile").isEmpty()) {
	    	// 로컬에 저장된 기존 파일 정보 삭제
	    	
	    	// 기존 파일 정보 삭제
	    	fileService.deleteFile("accompany", accompanyRequestDto.getAccompanyNo());
	    }

        // 기존 파일 존재하고, 수정하고자 하는 파일이 존재한다면 => 기존 파일 삭제 후 수정 파일 등록
        if (fileInfos != null && !fileInfos.isEmpty()) {
        	// 로컬에 저장된 기존 파일 정보 삭제
        	
	    	// 기존 파일 정보 삭제
            fileService.deleteFile("accompany", accompanyRequestDto.getAccompanyNo());
            // 새 파일 등록
            fileService.registerFile(accompanyRequestDto);
        }
    }

    // 동행 글 삭제
    @Override
    @Transactional
    public void deleteAccompany(int accompanyNo, String uploadPath) {
    	// 로컬에 저장된 파일 정보 삭제
    	deleteFiles(accompanyNo, uploadPath);
    	// 파일 정보 삭제
        fileService.deleteFile("accompany", accompanyNo);

        // 동행 글 정보 삭제
        accompanyMapper.deleteAccompany(accompanyNo);
    }
	
    // 파일들 로컬 컴퓨터에서 삭제
    public void deleteFiles(int accompanyNo, String uploadPath) {
        List<FileInfoDto> fileList = accompanyMapper.fileInfoList(accompanyNo);
        for (FileInfoDto fileInfoDto : fileList) {
            File file = new File(uploadPath + File.separator + fileInfoDto.getSaveFolder() + File.separator + fileInfoDto.getSaveFile());
            file.delete();
        }
    }

	// 동행 신청 여부
	@Override
	public int isJoin( Map<String, String> map) {
		try {
			if(map.get("id") == null) {
				throw new MemberNotFoundException();
			}
			return accompanyMapper.isJoin(map);							
		} catch(Exception e) {
			throw new InvalidAccompanyDataException();
		}
	}
	
	// 동행 신청
	@Override
	@Transactional
	public void join(Map<String, String> map) {
		// accompany_join 테이블에 레코드 추가
		accompanyMapper.join(map);
		
		// accompany 테이블에 current_num 업데이트
		map.put("cat", "increase");
		accompanyMapper.updateCurrentNum(map);
		
		// 현재 신청자수와 모집정원 같은지 확인
		int cnt = accompanyMapper.isLimit(map);
		if(cnt == 1) {
			// 같다면, 상태를 모집중 -> 모집마감으로 변경
			map.put("cat", "finished");
			accompanyMapper.updateStatus(map);
		}
		
		// 글 작성자에게 알림
		Notify notify = new Notify();
		notify.setTableName(1);
		notify.setPkNo(Integer.parseInt(map.get("accompanyNo")));
		notify.setContent("동행 신청하였습니다");
		notify.setSend(map.get("id"));;
		notify.setReceive(map.get("writerId"));
		System.out.println(notify);
		notifyService.createNotify(notify);
		
	}
	
	// 동행 신청 취소
	@Override
	@Transactional
	public void joinCancel(Map<String, String> map) {
		// accompany_join 테이블에서 레코드 삭제
		accompanyMapper.joinCancel(map);

		// 현재 신청자수와 모집정원 같은지 확인
		int cnt = accompanyMapper.isLimit(map);
		if(cnt == 1) {
			// 같다면, 상태를 모집마감 -> 모집중으로 변경
			map.put("cat", "finding");
			accompanyMapper.updateStatus(map);
		}
		
		// accompany 테이블에 current_num 업데이트 
		map.put("cat", "decrease");
		accompanyMapper.updateCurrentNum(map);
	}


}
