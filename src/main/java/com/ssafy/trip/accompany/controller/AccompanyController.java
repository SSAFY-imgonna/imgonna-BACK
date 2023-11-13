package com.ssafy.trip.accompany.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.trip.accompany.model.Accompany;
import com.ssafy.trip.file.model.dto.FileInfoDto;
import com.ssafy.trip.member.model.dto.Member;
import com.ssafy.trip.accompany.model.service.AccompanyService;
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
     * 동행 글 목록
     * @param map(pgno, key, word)
     * @return data("list") + viewName
     * @throws Exception
     */
    @GetMapping
    private ResponseEntity<?> getAccompanyList(@RequestParam Map<String, String> map) throws Exception {
        logger.debug("getAccompanyList parameter pgno : {}", map.get("pgno"));
         
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
     * 동행 글 작성
     * @param accompany 등록할 동행 글 dto
     * @param date 모임날짜
     * @param time 모임시간
     * @param files 이미지
     * @param session 
     * @param redirectAttributes
     * @return redirect URL
     * @throws Exception
     */
//    @PostMapping
//    public ResponseEntity<?> createAccompany(@RequestBody Accompany accompany, 
//    		@RequestBody String date, @RequestBody String time,
//    		@RequestBody MultipartFile[] files, HttpSession session,
//            RedirectAttributes redirectAttributes) throws Exception {
////    	   나중에 로그인 완료되면 하드코딩된거 바꿔야!!
////        Member member = (Member) session.getAttribute("memberDto");
////        accompany.setId(member.getId());
//    	accompany.setId("ssafy");
//    	logger.debug("date: {}, time: {}",date, time);
//        String joinTime = date + " " + time + ":00"; // 초를 "00"으로 초기화(TIMESTAMP로 저장됨)
//        accompany.setJoinTime(joinTime);
//        logger.debug("createAccompany Accompany : {}", accompany);
//        
//        try {
//        	// 이미지 업로드
//        	uploadFiles(accompany, files);
//        	// 동행 글 추가
//        	accompanyService.createAccompany(accompany);
//        	return ResponseEntity
//        			.status(HttpStatus.OK)
//        			.build();
//        } catch (Exception e) {
//        	return exceptionHandling(e);
//        }
//        
////      페이지 네이션 관련 처리 나중에 하자!!!
//    }


//    /**
//     * 글 상세
//     */
//    @GetMapping("/view")
//    public String view(@RequestParam int accompanyNo, @RequestParam Map<String, String> map, Model model,
//    		HttpSession session) throws Exception {
//        logger.debug("view accompanyNo : {}", accompanyNo);
//        accompanyService.updateHit(accompanyNo); // 조회수 증가
//        Accompany accompanyDto = accompanyService.getAccompanyByAccompanyNo(accompanyNo);
//        logger.debug("view accompanyDto : {}", accompanyDto);
//        model.addAttribute("accompanyDto", accompanyDto);
//   
//        
//		// 세션에 설정된 아이디 정보 가져오기
//        Member memberDto = (Member) session.getAttribute("memberDto");
//        String userId = memberDto.getId();	
//        
//		Map<String, String> joinInfo = new HashMap<>();
//		joinInfo.put("accompanyNo", String.valueOf(accompanyNo));
//		joinInfo.put("userId", userId);
//		
//		if(userId != null) {
//			// 이미 신청됐는지 여부
//			int cnt = accompanyService.isJoin(joinInfo);
//			
//			// 이미 신청되어있다면
//			if(cnt == 1) {
//				model.addAttribute("isJoin", true);
//			}
//			// 아직 신청되어있지 않다면
//			else {
//				model.addAttribute("isJoin", false);
//			}
//		}
//
////		model.addAttribute("pgno", map.get("pgno"));
////		model.addAttribute("key", map.get("key"));
////		model.addAttribute("word", map.get("word"));
//        return "accompany/view";
//    }
//
//
//    /**
//     * 글 수정폼 호출
//     */
//    @GetMapping("/modify")
//    public String modify(@RequestParam int accompanyNo, @RequestParam Map<String, String> map, Model model)
//            throws Exception {
//        logger.debug("modify accompanyNo : {}", accompanyNo);
//        Accompany accompanyDto = accompanyService.getAccompanyByAccompanyNo(accompanyNo);
//        logger.debug("view accompanyDto : {}", accompanyDto);
//
//
//        String[] splitDate = accompanyDto.getAccompanyDate().split(" ");
//        String accompanyDate = splitDate[0];
//        String accompanyTime = splitDate[1];
//
//        logger.debug("accompanyDate : {}, accompanyTime : {}", accompanyDate, accompanyTime);
//        model.addAttribute("accompanyDto", accompanyDto);
//        model.addAttribute("accompanyDate", accompanyDate);
//        model.addAttribute("accompanyTime", accompanyTime);
//
////		model.addAttribute("pgno", map.get("pgno"));
////		model.addAttribute("key", map.get("key"));
////		model.addAttribute("word", map.get("word"));
//        return "accompany/modify";
//    }
//
//    /**
//     * 동행 글 수정 메서드
//     * @param accompanyDto 수정할 동행 글 dto
//     * @param files 수정할 파일
//     * @param map accompanyDate, accompanyTime, originFile
//     * @return redirect URL
//     * @throws Exception
//     */
//    @PostMapping("/modify")
//    public String modify(Accompany accompanyDto, @RequestParam("upfile") MultipartFile[] files,
//                         @RequestParam Map<String, String> map) throws Exception {
//        logger.debug("modify AccompanyDto : {}", accompanyDto);
//        uploadFiles(accompanyDto, files);
//        accompanyService.modifyAccompany(accompanyDto, map);
//
////		redirectAttributes.addAttribute("pgno", map.get("pgno"));
////		redirectAttributes.addAttribute("key", map.get("key"));
////		redirectAttributes.addAttribute("word", map.get("word"));
//        return "redirect:/accompany/list";
//    }
//
//    /**
//     * 글 삭제
//     */
//    @GetMapping("/delete")
//    public String delete(@RequestParam int accompanyNo, @RequestParam Map<String, String> map,
//                         RedirectAttributes redirectAttributes) throws Exception {
//        logger.debug("delete accompanyNo : {}", accompanyNo);
//
//        accompanyService.deleteAccompany(accompanyNo, uploadPath);
//
////		redirectAttributes.addAttribute("pgno", map.get("pgno"));
////		redirectAttributes.addAttribute("key", map.get("key"));
////		redirectAttributes.addAttribute("word", map.get("word"));
//        return "redirect:/accompany/list";
//    }

    /**
     * 파일 업로드
     * @param accompanyDto
     * @param files
     * @throws IOException
     */
    private void uploadFiles(Accompany accompany, MultipartFile[] files) throws IOException {
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
            accompany.setFileInfos(fileInfos);
        }
    }

//    /**
//     * 동행 신청
//     */
//    @GetMapping("/join")
//	private String join(@RequestParam("accompanyNo") int accompanyNo, @RequestParam Map<String, String> map,
//			HttpSession session, Model model, RedirectAttributes redirectAttributes) {
//		// 세션에 설정된 아이디 정보 가져오기
//        Member memberDto = (Member) session.getAttribute("memberDto");
//        String userId = memberDto.getId();	
//        
//		Map<String, String> joinInfo = new HashMap<>();
//		joinInfo.put("accompanyNo", String.valueOf(accompanyNo));
//		joinInfo.put("userId", userId);
//		
//		if(userId != null) {
//			// 이미 신청됐는지 여부
//			int cnt = accompanyService.isJoin(joinInfo);
//			
//			// 이미 신청되어있다면
//			if(cnt == 1) {
//				redirectAttributes.addAttribute("isJoin", true);
////				redirectAttributes.addAttribute("msg", "이미 신청되었습니다");
//				return "redirect:/accompany/view";
//			}
//			// 아직 신청되어있지 않다면
//			else {
//				// 신청
//				accompanyService.join(joinInfo);
//				redirectAttributes.addAttribute("msg", "신청 완료되었습니다");
//				redirectAttributes.addAttribute("isJoin", true);
//				redirectAttributes.addAttribute("accompanyNo", accompanyNo);
//				
//				return "redirect:/accompany/view";
//			}
//		}
//		
//		redirectAttributes.addAttribute("pgno", map.get("pgno"));
//		redirectAttributes.addAttribute("key", map.get("key"));
//		redirectAttributes.addAttribute("word", map.get("word"));
//		
//		return "redirect:/accompany/view";
//	} 
//	
//    /**
//     * 동행 취소
//     * @param accompanyNo
//     * @param map
//     * @param session
//     * @param model
//     * @param redirectAttributes
//     * @return
//     */
//    @GetMapping("/joinCancel")
//	private String joinCancel(@RequestParam("accompanyNo") int accompanyNo, @RequestParam Map<String, String> map,
//			HttpSession session, Model model, RedirectAttributes redirectAttributes) {		
//		// 세션에 설정된 아이디 정보 가져오기
//        Member memberDto = (Member) session.getAttribute("memberDto");
//        String userId = memberDto.getId();	
//		
//		Map<String, String> joinInfo = new HashMap<>();
//		joinInfo.put("accompanyNo", String.valueOf(accompanyNo));
//		joinInfo.put("userId", userId);
//		
//		if(userId != null) {
//			// 신청 취소
//			accompanyService.joinCancel(joinInfo);
//			
//			// 신청 취소 성공했다면
//			redirectAttributes.addAttribute("isJoin", false);
//			redirectAttributes.addAttribute("msg", "신청 취소 완료되었습니다");
//			redirectAttributes.addAttribute("accompanyNo", accompanyNo);
//			return "redirect:/accompany/view";	
//		}	
//		
//		redirectAttributes.addAttribute("pgno", map.get("pgno"));
//		redirectAttributes.addAttribute("key", map.get("key"));
//		redirectAttributes.addAttribute("word", map.get("word"));
//		return "redirect:/accompany/view";
//	}
    
    /**
     * 예외처리
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
