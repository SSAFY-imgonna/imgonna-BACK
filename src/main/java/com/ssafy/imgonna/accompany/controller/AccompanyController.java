package com.ssafy.imgonna.accompany.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import com.ssafy.imgonna.common.annotation.CheckToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.imgonna.file.model.dto.FileInfoDto;
import com.ssafy.imgonna.accompany.model.dto.Accompany;
import com.ssafy.imgonna.accompany.model.dto.AccompanyRequestDto;
import com.ssafy.imgonna.accompany.model.dto.AccompanyResponseDto;
import com.ssafy.imgonna.accompany.model.service.AccompanyService;
//import com.ssafy.util.FileUtil;
//import com.ssafy.util.QuickSort;

@RestController
@RequestMapping("/accompany")
public class AccompanyController {
    private final Logger logger = LoggerFactory.getLogger(AccompanyController.class);

    @Value("${file.path}")
    private String uploadPath;

//    @Value("${file.path.upload-images}")
//    private String uploadImagePath;
//
//    @Value("${file.path.upload-files}")
//    private String uploadFilePath;

    @Autowired
    private ServletContext servletContext;

    private AccompanyService accompanyService;

    public AccompanyController(AccompanyService accompanyService) {
        super();
        this.accompanyService = accompanyService;
    }


    /**
     * 동행 글 작성
     *
     * @param accompanyRequestDto 등록할 동행 글 dto
     * @param upfile
     * @param session
     * @return ResponseEntity
     * @throws Exception
     */
    @PostMapping
    @CheckToken
    public ResponseEntity<?> createAccompany(AccompanyRequestDto accompanyRequestDto,
                                             @RequestPart(required = false) MultipartFile[] upfile, HttpSession session) throws Exception {
        String date = accompanyRequestDto.getDate();
        String time = accompanyRequestDto.getTime();
        String joinTime = date + " " + time + ":00"; // 초를 "00"으로 초기화(TIMESTAMP로 저장됨)
        accompanyRequestDto.setJoinTime(joinTime);

        logger.debug("AccompanyRequestDto : {}", accompanyRequestDto);

        try {
            if (upfile != null) {
                // 이미지 업로드
                uploadFiles(accompanyRequestDto, upfile);
            }
            // 동행 글 추가
            accompanyService.createAccompany(accompanyRequestDto);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    /**
     * 동행 글 목록
     *
     * @param map(pgno,key,word)
     * @return ResponseEntity
     * @throws Exception
     */
    @GetMapping
    private ResponseEntity<?> getAccompanyList(@RequestParam Map<String, String> map) throws Exception {
        logger.debug("getAccompanyList parameter cat : {}", map.get("cat"));

        try {
            List<Accompany> AccompanyList = accompanyService.getAccompanyList(map);
            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_JSON);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .headers(header)
                    .body(AccompanyList);
        } catch (Exception e) {
            return exceptionHandling(e);
        }

//        페이지 네이션 관련 처리 나중에 하자!!!
    }

    /**
     * 동행 글 상세
     *
     * @param accompanyNo 동행 글 번호
     * @param map(pgno,key,word)
     * @return
     * @throws Exception
     */
    @GetMapping("/{accompanyNo}")
    public ResponseEntity<AccompanyResponseDto> getAccompanyByAccompanNo(@PathVariable int accompanyNo, @RequestParam Map<String, String> map) throws Exception {
        logger.debug("getAccompanyByAccompanNo accompanyNo : {}", accompanyNo);

        // 조회수 증가
        accompanyService.updateHit(accompanyNo);
        // 동행 글 상세
        AccompanyResponseDto accompanyResponseDto = accompanyService.getAccompanyByAccompanyNo(accompanyNo);
        String joinTime = accompanyResponseDto.getJoinTime();
        String date = joinTime.split(" ")[0];
        String time = joinTime.split(" ")[1].substring(0, 5);
        accompanyResponseDto.setDate(date);
        accompanyResponseDto.setTime(time);

        logger.debug("getAccompanyByAccompanNo accompanyDto : {}", accompanyResponseDto);

        Map<String, String> joinInfo = new HashMap<>();
        joinInfo.put("accompanyNo", String.valueOf(accompanyNo));
        joinInfo.put("id", map.get("id"));
        logger.debug("getAccompanyByAccompanNo joinInfo : {}", joinInfo);

        // 로그인 되어 있다면
        if (joinInfo.get("id") != null) {
            // 이미 신청됐는지 여부
            int cnt = accompanyService.isJoin(joinInfo);
            logger.debug("getAccompanyByAccompanNo cnt : {}", cnt);
            // 신청되어있다면 true, 신청되어있지 않다면 false
            boolean isJoin = cnt == 1 ? true : false;
            logger.debug("getAccompanyByAccompanNo isJoin : {}", isJoin);

            accompanyResponseDto.setIsJoin(isJoin);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accompanyResponseDto);
    }

    /**
     * 동행 글 수정
     *
     * @param accompanyNo         동행 글 번호
     * @param accompanyRequestDto 수정할 동행 글 dto
     * @param upfile
     * @param originFile
     * @return ResponseEntity
     * @throws Exception
     */
    @PutMapping("/{accompanyNo}")
    @CheckToken
    public ResponseEntity<String> modifyAccompany(@PathVariable int accompanyNo, AccompanyRequestDto accompanyRequestDto,
                                                  @RequestPart(required = false) MultipartFile[] upfile, @RequestPart(required = false) String originFile) throws Exception {
        logger.debug("modifyAccompany AccompanyRequestDto : {}", accompanyRequestDto);
        String date = accompanyRequestDto.getDate();
        String time = accompanyRequestDto.getTime();
        String joinTime = date + " " + time + ":00"; // 초를 "00"으로 초기화(TIMESTAMP로 저장됨)
        accompanyRequestDto.setJoinTime(joinTime);
        accompanyRequestDto.setAccompanyNo(accompanyNo);
        Map<String, String> map = new HashMap<String, String>();
        map.put("originFile", "");
        // 기존 파일 존재한다면
        if (originFile != null) {
            map.put("originFile", originFile);
        }

        // 이미지 업로드
        if (upfile != null) {
            uploadFiles(accompanyRequestDto, upfile);
        }
        // 동행 글 수정
        accompanyService.modifyAccompany(accompanyRequestDto, map);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    /**
     * 동행 글 삭제
     *
     * @param accompanyNo 동행 글 번호
     * @return
     * @throws Exception
     */
    @DeleteMapping("/{accompanyNo}")
    @CheckToken
    public ResponseEntity<String> deleteAccompany(@PathVariable int accompanyNo) throws Exception {
        logger.debug("deleteAccompany accompanyNo : {}", accompanyNo);

        accompanyService.deleteAccompany(accompanyNo, uploadPath);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    /**
     * 동행 신청
     *
     * @param accompanyNo 동행 글 번호
     * @param map(id)
     * @return
     * @throws Exception
     */
    @PostMapping("/{accompanyNo}")
    @CheckToken
    public ResponseEntity<?> createAccompanyJoin(@PathVariable int accompanyNo, @RequestBody Map<String, String> map) throws Exception {
        Map<String, String> joinInfo = new HashMap<>();
        joinInfo.put("accompanyNo", String.valueOf(accompanyNo));
        joinInfo.put("id", map.get("id")); // 로그인한 사용자 정보 가져오기
        joinInfo.put("writerId", map.get("writerId")); // 글 작성자 정보 가져오기
        logger.debug("createAccompanyJoin joinInfo : {}", joinInfo);

        accompanyService.join(joinInfo);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    /**
     * 동행 신청 취소
     *
     * @param accompanyNo 동행 글 번호
     * @param map(id)
     * @return
     * @throws Exception
     */
    @DeleteMapping("/{accompanyNo}/join")
    @CheckToken
    public ResponseEntity<?> deleteAccompanyJoin(@PathVariable int accompanyNo, @RequestParam Map<String, String> map) throws Exception {
        Map<String, String> joinInfo = new HashMap<>();
        joinInfo.put("accompanyNo", String.valueOf(accompanyNo));
        joinInfo.put("id", map.get("id")); // 로그인한 사용자 정보 가져오기
        logger.debug("deleteAccompanyJoin joinInfo : {}", joinInfo);

        accompanyService.joinCancel(joinInfo);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    /**
     * 파일 업로드
     *
     * @param accompanyDto
     * @param files
     * @throws IOException
     */
    private void uploadFiles(AccompanyRequestDto accompanyRequestDto, MultipartFile[] files) throws IOException {
        // FileUpload 관련 설정
//        logger.debug("uploadPath : {}, uploadImagePath : {}, uploadFilePath : {}", uploadPath, uploadImagePath, uploadFilePath);
        logger.debug("MultipartFile.isEmpty : {}", files[0].isEmpty());
        if (!files[0].isEmpty()) { // 파일 1개라도 업로드했다면
            String realPath = servletContext.getRealPath("/upload");
            String today = new SimpleDateFormat("yyMMdd").format(new Date());
            String saveFolder = uploadPath + File.separator + today;
            logger.debug("저장 폴더 : {}", saveFolder);
            File folder = new File(saveFolder);
            if (!folder.exists()) // 해당 폴더(upload 및 날짜별 폴더)가 존재하지 않을경우, 생성해줌
                folder.mkdirs();
            List<FileInfoDto> fileInfos = new ArrayList<FileInfoDto>();
            for (MultipartFile mfile : files) {
                FileInfoDto fileInfoDto = new FileInfoDto();
                String originalFileName = mfile.getOriginalFilename(); // 원본 파일 이름
                if (!originalFileName.isEmpty()) {
                    // java.util.UUID 클래스를 사용하여 UUID(Universally Unique Identifier)를 생성하고 문자열로 반환
                    String saveFileName = UUID.randomUUID().toString()
                            + originalFileName.substring(originalFileName.lastIndexOf('.')); // 파일 확장자([ex].jpg)만 가져옴
                    fileInfoDto.setSaveFolder(today); // 해당 날짜별 폴더에 저장되어있음
                    fileInfoDto.setOriginalFile(originalFileName);
                    fileInfoDto.setSaveFile(saveFileName);
                    logger.debug("원본 파일 이름 : {}, 실제 저장 파일 이름 : {}", mfile.getOriginalFilename(), saveFileName);
                    mfile.transferTo(new File(folder, saveFileName));
                }
                fileInfos.add(fileInfoDto);
            }
            accompanyRequestDto.setFileInfos(fileInfos);
        }
    }

    /**
     * 예외처리
     *
     * @param e 예외객체
     * @return 예외메세지
     */
    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error : " + e.getMessage());
    }
}
