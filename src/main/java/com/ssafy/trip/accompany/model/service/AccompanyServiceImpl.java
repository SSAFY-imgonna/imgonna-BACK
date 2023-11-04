package com.ssafy.trip.accompany.model.service;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.trip.file.model.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.trip.accompany.model.AccompanyDto;
import com.ssafy.trip.file.model.dto.FileInfoDto;
import com.ssafy.trip.accompany.model.mapper.AccompanyMapper;

@Service
public class AccompanyServiceImpl implements AccompanyService {

    private AccompanyMapper accompanyMapper;
    private FileService fileService;

    public AccompanyServiceImpl(AccompanyMapper accompanyMapper, FileService fileService) {
        this.accompanyMapper = accompanyMapper;
        this.fileService = fileService;
    }

    /**
     * 글 목록
     */
    @Override
    public List<AccompanyDto> list(Map<String, String> map) {

        Map<String, Object> param = new HashMap<String, Object>();
        String key = map.get("key");
//		if("userid".equals(key))
//			key = "b.user_id";
        param.put("key", key == null ? "" : key);
        param.put("word", map.get("word") == null ? "" : map.get("word"));
//		int pgNo = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
//		int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
//		param.put("start", start);
//		param.put("listsize", SizeConstant.LIST_SIZE);

        return accompanyMapper.list(map);
    }

    @Override
    public List<FileInfoDto> fileInfoList(int accompanyNo) {
        return accompanyMapper.fileInfoList(accompanyNo);
    }


    /**
     * 글 작성
     */
    @Override
    @Transactional
    public void write(AccompanyDto accompanyDto) {
        System.out.println("글 입력 전 AccompanyDto : " + accompanyDto);
        accompanyMapper.write(accompanyDto);
        System.out.println("글 입력 후 AccompanyDto : " + accompanyDto);

        List<FileInfoDto> fileInfos = accompanyDto.getFileInfos();
        if (fileInfos != null && !fileInfos.isEmpty()) {
            fileService.registerFile(accompanyDto);
        }
    }

    /**
     * 글 상세
     */
    @Override
    public AccompanyDto getAccompanyByAccompanyNo(int accompanyNo) {
        return accompanyMapper.getAccompanyByAccompanyNo(accompanyNo);
    }

    /**
     * 글 삭제
     */
    @Override
    @Transactional
    public void deleteAccompany(int accompanyNo, String uploadPath) {
        fileService.deleteFile(accompanyNo);
        accompanyMapper.deleteAccompany(accompanyNo);

        deleteFiles(accompanyNo, uploadPath);
    }

    /**
     * 동행 글 번호를 기준으로 파일 목록들을 얻어와, 얻어온 파일들을 로컬 컴퓨터에서 삭제
     * @param accompanyNo 기준 동행 글 번호
     * @param uploadPath
     */
    private void deleteFiles(int accompanyNo, String uploadPath) {
        List<FileInfoDto> fileList = this.fileInfoList(accompanyNo);
        for (FileInfoDto fileInfoDto : fileList) {
            File file = new File(uploadPath + File.separator + fileInfoDto.getSaveFolder() + File.separator + fileInfoDto.getSaveFile());
            file.delete();
        }
    }

    /**
     * 글 수정 (파일 수정도 함께)
     *
     * @param accompanyDto
     * @param map
     * @throws SQLException
     */
    @Override
    public void modifyAccompany(AccompanyDto accompanyDto, Map<String, String> map) throws SQLException {
        accompanyDto.setAccompanyNo(Integer.parseInt(map.get("accompanyNo")));
        String accompanyDate = map.get("accompanyDate");
        String accompanyTime = map.get("accompanyTime");
        accompanyDate = accompanyDate + " " + accompanyTime; // 초를 "00"으로 초기화
        accompanyDto.setAccompanyDate(accompanyDate);

        accompanyMapper.modifyAccompany(accompanyDto);

        List<FileInfoDto> fileInfos = accompanyDto.getFileInfos();
        // 기존 파일도 존재하지 않고, 수정하고자 하는 파일이 존재하지 않다면 파일 삭제
        if ((fileInfos == null || fileInfos.isEmpty()) && map.get("originFile").isEmpty()) {
            fileService.deleteFile(accompanyDto.getAccompanyNo());
        }

        // 수정하고자 하는 파일이 존재한다면 -> 기존 파일 삭제 후 수정 파일 등록
        if (fileInfos != null && !fileInfos.isEmpty()) {
            fileService.deleteFile(accompanyDto.getAccompanyNo());
            fileService.registerFile(accompanyDto);
        }
    }


//	/** 조회수 증가 */
//	@Override
//	public int updateHit(int accompanyNo) {
//		return dao.updateHit(accompanyNo);
//	}
//	
//	/** 이미 신청되어 있는지 여부 */
//	@Override
//	public int isJoin(int accompanyNo, String userId) {
//		return dao.isJoin(accompanyNo, userId);
//	}
//	
//	/** 신청 */
//	@Override
//	public int join(int accompanyNo, String userId) {
//		return dao.join(accompanyNo, userId);
//	}
//	
//	/** 신청 취소하기 */
//	@Override
//	public int joinCancel(int accompanyNo, String userId) {
//		// TODO Auto-generated method stub
//		return dao.joinCancel(accompanyNo, userId);
//	}


//	/** 댓글 목록 */
//	@Override
//	public List<AccompanyCommDto> getCommList(int accompanyNo) {
//		return dao.getCommList(accompanyNo);
//	}
//	
//	/** 댓글 작성 */
//	@Override
//	public int createComm(AccompanyCommDto dto) {
//		return dao.createComm(dto);
//	}
//	
//	/** 댓글 수정 */
//	@Override
//	public int modifyComm(AccompanyCommDto dto) {
//		return dao.modifyComm(dto);
//	}
//	
//	/** 댓글 삭제 */
//	@Override
//	public int deleteComment(int commentNo) {
//		return dao.deleteComment(commentNo);
//	}
//	
//	/** 댓글 개수 세기 */
//	@Override
//	public int commentCount(int accompanyNo) {
//		return dao.commentCount(accompanyNo);
//	}


}