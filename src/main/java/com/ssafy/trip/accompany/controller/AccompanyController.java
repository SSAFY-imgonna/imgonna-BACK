//package com.ssafy.trip.accompany.controller;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.fasterxml.jackson.core.exc.StreamWriteException;
//import com.fasterxml.jackson.databind.DatabindException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.ssafy.trip.accompany.model.AccompanyCommDto;
//import com.ssafy.trip.accompany.model.AccompanyDto;
//import com.ssafy.trip.member.model.dto.Member;
//import com.ssafy.trip.accompany.model.service.AccompanyService;
//import com.ssafy.trip.accompany.model.service.AccompanyServiceImpl;
////import com.ssafy.util.FileUtil;
////import com.ssafy.util.QuickSort;
//
//@Controller
//@RequestMapping("/accompany")
//public class AccompanyController{
//	private final Logger logger = LoggerFactory.getLogger(AccompanyController.class);
//
////	@Value("${file.path}")
////	private String uploadPath;
////
////	@Value("${file.path.upload-images}")
////	private String uploadImagePath;
////
////	@Value("${file.path.upload-files}")
////	private String uploadFilePath;
//
//	private static AccompanyService accompanyService = AccompanyServiceImpl.getInstance();
//
//	/** 동행 글 작성 폼 호출 */
//	@GetMapping("/write")
//	private String write(@RequestParam Map<String, String> map, Model model) {
//		logger.debug("write call parameter {}", map);
//		model.addAttribute("pgno", map.get("pgno"));
//		model.addAttribute("key", map.get("key"));
//		model.addAttribute("word", map.get("word"));
//
//		return "accompany/write";
//	}
//
//	/** 동행 글 작성 */
//	@PostMapping("/write")
//	private String write(AccompanyDto accompanyDto, String accompanyDate, String accompanyTime,
//			@RequestParam("upfile") MultipartFile[] files, HttpSession session, RedirectAttributes redirectAttributes) throws Exception {
//
//		logger.debug("write boardDto : {}", accompanyDto);
//		Member memberDto = (Member) session.getAttribute("userinfo");
//		accompanyDto.setUserId(memberDto.getUserId());
//
////		FileUpload 관련 설정.
////		logger.debug("uploadPath : {}, uploadImagePath : {}, uploadFilePath : {}", uploadPath, uploadImagePath, uploadFilePath);
////		logger.debug("MultipartFile.isEmpty : {}", files[0].isEmpty());
////		if (!files[0].isEmpty()) {
////			String today = new SimpleDateFormat("yyMMdd").format(new Date());
////			String saveFolder = uploadPath + File.separator + today;
////			logger.debug("저장 폴더 : {}", saveFolder);
////			File folder = new File(saveFolder);
////			if (!folder.exists())
////				folder.mkdirs();
////			List<FileInfoDto> fileInfos = new ArrayList<FileInfoDto>();
////			for (MultipartFile mfile : files) {
////				FileInfoDto fileInfoDto = new FileInfoDto();
////				String originalFileName = mfile.getOriginalFilename();
////				if (!originalFileName.isEmpty()) {
////					String saveFileName = UUID.randomUUID().toString()
////							+ originalFileName.substring(originalFileName.lastIndexOf('.'));
////					fileInfoDto.setSaveFolder(today);
////					fileInfoDto.setOriginalFile(originalFileName);
////					fileInfoDto.setSaveFile(saveFileName);
////					logger.debug("원본 파일 이름 : {}, 실제 저장 파일 이름 : {}", mfile.getOriginalFilename(), saveFileName);
////					mfile.transferTo(new File(folder, saveFileName));
////				}
////				fileInfos.add(fileInfoDto);
////			}
////			boardDto.setFileInfos(fileInfos);
////		}
//
//		// DB에는 날짜(date) 및 시간(time)이 1개의 컬럼(accompany_date)의 timestamp 형태로 저장됨
//		accompanyDate = accompanyDate + " " + accompanyTime + ":00"; // 초를 "00"으로 초기화
//		accompanyDto.setAccompanyDate(accompanyDate);
//
//		accompanyService.write(accompanyDto);
//
//		redirectAttributes.addAttribute("pgno", "1");
//		redirectAttributes.addAttribute("key", "");
//		redirectAttributes.addAttribute("word", "");
//
//		return "redirect:/article/list";
//	}
//
//
//	private void updateHit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// 쿼리 스트링에 설정된 글번호 정보 가져오기
//		int accompanyNo = Integer.parseInt(request.getParameter("accompanyNo"));
//
//		// 조회수 증가
//		service.updateHit(accompanyNo);
//
//		redirect(response, "/accompany?action=view&accompanyNo=" + accompanyNo);
//	}
//
//	private void joinCancel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// 쿼리 스트링에 설정된 글번호 정보 가져오기
//		int accompanyNo = Integer.parseInt(request.getParameter("accompanyNo"));
//
//		// 세션에 설정된 아이디 정보 가져오기
//		String userId = null;
//		HttpSession session = request.getSession(false);
//		if(session != null && session.getAttribute("memberDto") != null) {
//			MemberDto dto = (MemberDto)session.getAttribute("memberDto");
//			userId = dto.getId();
//		}
//
//		if(userId != null) {
//			// 신청 취소
//			int cnt = service.joinCancel(accompanyNo, userId);
//			System.out.println("신청취소 여부 : " + cnt);
//			// 신청 취소 성공했다면
//			if(cnt == 1) {
//				request.setAttribute("isJoin", false);
//				request.setAttribute("msg", "신청 취소 완료되었습니다");
//				forward(request, response, "accompany?action=view");
//			}
//			// 아직 신청되어있지 않다면
//			else {
//				request.setAttribute("isJoin", true);
//				request.setAttribute("msg", "신청 취소 실패하였습니다");
//				forward(request, response, "accompany?action=view");
//			}
//		}
//	}
//
//	private void join(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// 쿼리 스트링에 설정된 글번호 정보 가져오기
//		int accompanyNo = Integer.parseInt(request.getParameter("accompanyNo"));
//
//		// 세션에 설정된 아이디 정보 가져오기
//		String userId = null;
//		HttpSession session = request.getSession(false);
//		if(session != null && session.getAttribute("memberDto") != null) {
//			MemberDto dto = (MemberDto)session.getAttribute("memberDto");
//			userId = dto.getId();
//		}
//
//		if(userId != null) {
//			// 이미 신청됐는지 여부
//			int cnt = service.isJoin(accompanyNo, userId);
//
//			// 이미 신청되어있다면
//			if(cnt == 1) {
//				request.setAttribute("isJoin", true);
//				request.setAttribute("msg", "이미 신청되었습니다");
//				forward(request, response, "accompany?action=view");
//			}
//			// 아직 신청되어있지 않다면
//			else {
//				request.setAttribute("isJoin", false);
//				// 신청
//				service.join(accompanyNo, userId);
//				request.setAttribute("msg", "신청 완료되었습니다");
//				forward(request, response, "accompany?action=view");
//			}
//		}
//	}
//
//	private void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// 쿼리 스트링에 설정된 글번호 정보 가져오기
//		int accompanyNo = Integer.parseInt(request.getParameter("accompanyNo"));
//
//		// 세션에 설정된 아이디 정보 가져오기
//		String userId = null;
//		HttpSession session = request.getSession(false);
//		if(session != null && session.getAttribute("memberDto") != null) {
//			MemberDto dto = (MemberDto)session.getAttribute("memberDto");
//			userId = dto.getId();
//		}
//
//		if(userId != null) {
//			// 이미 신청됐는지 여부
//			int cnt = service.isJoin(accompanyNo, userId);
//
//			// 이미 신청되어있다면
//			if(cnt == 1) {
//				request.setAttribute("isJoin", true);
//			}
//			// 아직 신청되어있지 않다면
//			else {
//				request.setAttribute("isJoin", false);
//			}
//		}
//
//		// 총 댓글 수 구하기
//		int cnt = service.commentCount(accompanyNo);
//
//		// 상세 정보 불러옴
//		AccompanyDto accompanyDto = service.view(accompanyNo);
//
//		request.setAttribute("accompanyDto", accompanyDto);
//		request.setAttribute("cnt", cnt);
//		forward(request, response, "/accompany/view.jsp");
//	}
//
//	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		List<AccompanyDto> list = service.list();
//		request.setAttribute("list", list);
//
//		forward(request, response, "/accompany/list.jsp");
//	}
//
//
//
//	/* 여기서부터는 댓글~~~~~~~~~~~~~~~~~~ */
////	private void commentCount(HttpServletRequest request, HttpServletResponse response) throws StreamWriteException, DatabindException, IOException {
////		int accompanyNo = Integer.parseInt(request.getParameter("accompanyNo"));
////
////		int cnt = service.commentCount(accompanyNo);
////		System.out.println("작성된 댓글 개수 : " + cnt);
////
////		Map<String, Object> mapAjax = new HashMap<>();
////		mapAjax.put("cnt", cnt);
////		ObjectMapper mapper = new ObjectMapper();
////		// 서버 측에서 JSON 데이터의 문자 인코딩 설정
////		response.setContentType("application/json; charset=UTF-8");
////		mapper.writeValue(response.getWriter(), mapAjax);
////	}
////
////	private void commentDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
////		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
////
////		int cnt = service.deleteComment(commentNo);
////		System.out.println("cnt값 : " + cnt);
////
////		ObjectMapper mapper = new ObjectMapper();
////		mapper.writeValue(response.getWriter(), cnt);
////	}
////
////	private void commentModify(HttpServletRequest request, HttpServletResponse response) throws IOException {
////	    // POST 요청의 본문(body)을 읽기 위한 BufferedReader 생성
////	    BufferedReader reader = request.getReader();
////	    StringBuilder jsonStringBuilder = new StringBuilder();
////	    String line;
////
////	    while ((line = reader.readLine()) != null) {
////	        jsonStringBuilder.append(line);
////	    }
////
////	    String jsonData = jsonStringBuilder.toString();
////	    System.out.println(jsonData);
////
////		ObjectMapper mapper = new ObjectMapper();
////		// JSON 문자열을 파싱하여 객체로 변환
////		AccompanyCommDto dto = mapper.readValue(jsonData, AccompanyCommDto.class);
////
////		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
////		HttpSession session = request.getSession(false);
////		MemberDto memberDto = (MemberDto)session.getAttribute("memberDto");
////		String id = memberDto.getId();
////
////		dto.setCommentNo(commentNo);
////		dto.setId(id);
////		System.out.println(dto);
////
////		int cnt = service.modifyComm(dto);
////		System.out.println("cnt값 : " + cnt);
////
////		mapper.writeValue(response.getWriter(), cnt);
////	}
////
////	private void commentAdd(HttpServletRequest request, HttpServletResponse response) throws StreamWriteException, DatabindException, IOException {
////	    // POST 요청의 본문(body)을 읽기 위한 BufferedReader 생성
////	    BufferedReader reader = request.getReader();
////	    StringBuilder jsonStringBuilder = new StringBuilder();
////	    String line;
////
////	    while ((line = reader.readLine()) != null) {
////	        jsonStringBuilder.append(line);
////	    }
////
////	    String jsonData = jsonStringBuilder.toString();
////
////		// JSON 문자열을 파싱하여 객체로 변환
////		ObjectMapper mapper = new ObjectMapper();
////		AccompanyCommDto dto = mapper.readValue(jsonData, AccompanyCommDto.class);
////
////		int accompanyNo = Integer.parseInt(request.getParameter("accompanyNo"));
////
////		HttpSession session = request.getSession(false);
////		MemberDto memberDto = (MemberDto)session.getAttribute("memberDto");
////		String id = memberDto.getId();
////
////		dto.setAccompanyNo(accompanyNo);
////		dto.setId(id);
////
////		int cnt = service.createComm(dto);
////		System.out.println("cnt값 : " + cnt);
////
////		mapper.writeValue(response.getWriter(), cnt);
////	}
////
////	private void commentList(HttpServletRequest request, HttpServletResponse response) throws IOException {
////		int accompanyNo = Integer.parseInt(request.getParameter("accompanyNo"));
////
////		List<AccompanyCommDto> list = service.getCommList(accompanyNo);
////
////		HttpSession session = request.getSession(false);
////		MemberDto memberDto = (MemberDto)session.getAttribute("memberDto");
////		String id = null;
////		if(memberDto != null)
////			id = memberDto.getId();
////
////		Map<String, Object> mapAjax = new HashMap<>();
////		mapAjax.put("list", list);
////		mapAjax.put("id", id); // 로그인한 사람이 작성자인지 체크하기 위함
////
////		// JSON 데이터로 변환
////		ObjectMapper mapper = new ObjectMapper();
////		// 서버 측에서 JSON 데이터의 문자 인코딩 설정
////		response.setContentType("application/json; charset=UTF-8");
////		mapper.writeValue(response.getWriter(), mapAjax);
////
////	}
//
////	private void sort(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////		String keyfield1 = request.getParameter("keyfield1");
////
////		// 그냥 db 접근해서 리스트 뽑아옴
////		List<AccompanyDto> list = service.list();
////
////		System.out.println(list.size());
////
////		if(keyfield1.equals("hit") || keyfield1.equals("regDate"))
////			QuickSort.quickSort(list, 0, list.size() - 1, keyfield1); // 퀵소트
////
////		request.setAttribute("list", list);
////		request.setAttribute("keyfield1", keyfield1);
////
////		forward(request, response, "/accompany/list.jsp");
////	}
//
//
//}
