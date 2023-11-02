package com.ssafy.trip.accompany.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.trip.accompany.model.AccompanyDto;
import com.ssafy.trip.accompany.service.AccompanyService;
import com.ssafy.trip.accompany.service.AccompanyServiceImpl;
import com.ssafy.trip.member.model.MemberDto;
import com.ssafy.trip.util.FileUtil;
import com.ssafy.trip.util.QuickSort;
import org.springframework.web.multipart.MultipartRequest;

@WebServlet("/accompany")
public class AccompanyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static AccompanyService service = AccompanyServiceImpl.getInstance();
	
	private String contextPath = null;
	
	/** 서블릿 초기화 메서드: contextPath 가져와서 멤버변수 설정 초기화 */
	public void init() {
		// 현재 서블릿이 속해 있는 웹 어플리케이션의 ServletContext 객체 가져오기
		ServletContext application = getServletContext();
		String servletName = getServletConfig().getServletName();
		// 현재 웹어플리케이션의 contextPath를 가져와서 멤버변수에 설정하기
		// 요청서비스에서 반복적으로 사용하는 코드 request.getContextPath()를 대체 사용하기 위함: 편리
		contextPath = application.getContextPath();
		System.out.printf("\n[INFO] %s 의 %s 서블릿이 로딩 초기화 되었습니다.%n", contextPath, servletName);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// post 요청에 대한 한글 인코딩 설정
		request.setCharacterEncoding("utf-8");
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 동행에 대한 모든 요청을(accompany) 받아서 처리하므로 부가적으로 전달한 action=value를 가져와서 어떤 요청인지 파악
		String action = request.getParameter("action");
		switch(action) {
		case "writeForm":
			writeForm(request, response);
			break;
		case "write":
			write(request, response);
			break;
		case "list":
			list(request, response);
			break;
		case "view":
			view(request, response);
			break;
		case "updateHit":
			updateHit(request, response);
			break;
		case "join":
			join(request, response);
			break;
		case "sort":
			sort(request, response);
		default:
			break;
		}
	}
	
	private void sort(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyfield1 = request.getParameter("keyfield1");
		
		// 그냥 db 접근해서 리스트 뽑아옴
		List<AccompanyDto> list = service.list();
		
		System.out.println(list.size());
		
		if(keyfield1.equals("hit") || keyfield1.equals("regDate"))
			QuickSort.quickSort(list, 0, list.size() - 1, keyfield1); // 퀵소트
		
		request.setAttribute("list", list);		
		request.setAttribute("keyfield1", keyfield1);
		
		forward(request, response, "/accompany/list.jsp");		
	}

	private void updateHit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 쿼리 스트링에 설정된 글번호 정보 가져오기
		int accompanyNo = Integer.parseInt(request.getParameter("accompanyNo"));

		// 조회수 증가
		service.updateHit(accompanyNo);
		
		redirect(response, "/accompany?action=view&accompanyNo=" + accompanyNo);
	}

	private void join(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 쿼리 스트링에 설정된 글번호 정보 가져오기
		int accompanyNo = Integer.parseInt(request.getParameter("accompanyNo"));
		
		// 세션에 설정된 아이디 정보 가져오기
		String userId = null;
		HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute("memberDto") != null) {
			MemberDto dto = (MemberDto)session.getAttribute("memberDto");
			userId = dto.getId();			
		}		
		
		if(userId != null) {
			// 이미 신청됐는지 여부
			int cnt = service.isJoin(accompanyNo, userId);
			
			// 이미 신청되어있다면
			if(cnt == 1) {
				//request.setAttribute("isJoin", true);
				request.setAttribute("msg", "이미 신청 완료되었습니다");
				forward(request, response, "accompany?action=view");
			}
			// 아직 신청되어있지 않다면
			else {
				//request.setAttribute("isJoin", false);
				// 신청
				service.join(accompanyNo, userId);
				forward(request, response, "accompany?action=view");
			}
		}
		
	}

	private void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 쿼리 스트링에 설정된 글번호 정보 가져오기
		int accompanyNo = Integer.parseInt(request.getParameter("accompanyNo"));

		// 세션에 설정된 아이디 정보 가져오기
		String userId = null;
		HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute("memberDto") != null) {
			MemberDto dto = (MemberDto)session.getAttribute("memberDto");
			userId = dto.getId();			
		}	

		if(userId != null) {
			// 이미 신청됐는지 여부
			int cnt = service.isJoin(accompanyNo, userId);
			
			// 이미 신청되어있다면
			if(cnt == 1) {
				request.setAttribute("isJoin", true);
			}
			// 아직 신청되어있지 않다면
			else {
				request.setAttribute("isJoin", false);
			}
		}
		
		// 조회수 증가
		// service.updateHit(accompanyNo);
		
		// 상세 정보 불러옴
		AccompanyDto accompanyDto = service.view(accompanyNo);
		
		request.setAttribute("accompanyDto", accompanyDto);
		forward(request, response, "/accompany/view.jsp");
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<AccompanyDto> list = service.list();
		
		request.setAttribute("list", list);
		
		forward(request, response, "/accompany/list.jsp");
	}

	private void write(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		/* 요청 데이터 추출하기  *//*
		// 파일 업로드와 관련한 요청 처리를 위해서는 MultipartRequest 클래스의 메소드를 사용해야 함!!
		// Request.getParameter()를 사용할 수 없고, MultipartRequest.getParameter()를 사용
		MultipartRequest multi = FileUtil.createFile(request);

		String accompanyTitle = multi.getParameter("accompanyTitle");
		String accompanyContent = multi.getParameter("accompanyContent");
		String accompanyLoc = multi.getParameter("accompanyLoc");
		int accompanyTotal = Integer.parseInt(multi.getParameter("accompanyTotal"));
		String accompanyPhoto = multi.getFilesystemName("accompanyPhoto"); // 이미지 파일 업로드 처리		
		
		// DB에는 날짜(date) 및 시간(time)이 1개의 컬럼(accompany_date)의 timestamp 형태로 저장됨
		String date = multi.getParameter("accompanyDate");
		String time = multi.getParameter("accompanyTime");
		String accompanyDate = date + " " + time + ":00"; // 초를 "00"으로 초기화
		// Timestamp accompanyDate = Timestamp.valueOf(dateTime);

		// 세션에 저장된 사용자 아이디, 사용자 닉네임 정보 이용
		String userId = null;
		String userNickname = null;
		HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute("memberDto") != null) {
			MemberDto dto = (MemberDto) session.getAttribute("memberDto");
			userId = dto.getId();
			userNickname = dto.getName();
		}
		
		*//* 위의 데이터들을 가지고 AccompanyDto 객체 생성 *//*
		AccompanyDto accompanyDto = new AccompanyDto();
		accompanyDto.setAccompanyTitle(accompanyTitle);
		accompanyDto.setAccompanyContent(accompanyContent);
		accompanyDto.setAccompanyLoc(accompanyLoc);
		accompanyDto.setAccompanyTotal(accompanyTotal);
		accompanyDto.setAccompanyDate(accompanyDate);
		accompanyDto.setUserId(userId);
		accompanyDto.setUserNickname(userNickname);
		accompanyDto.setAccompanyPhoto(accompanyPhoto);
		System.out.println(accompanyDto.toString());
		
		*//* Model에 요청 의뢰 및 응답결과 받아서 응답위한 설정 *//*
		int cnt = service.write(accompanyDto);
		if(cnt == 1) { // 성공
			//request.setAttribute("msg", "글 작성 성공");
		} else {
			//request.setAttribute("msg", "글 작성 실패");
		}*/
		
		/* 응답페이지 이동 */
		redirect(response, "/accompany?action=list");
	}

	/** 동행 글쓰기 폼 호출 */
	private void writeForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		redirect(response, "/accompany/write.jsp");
	}

	// forward를 위한 메서드 분리 설계
	protected void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

	// redirect를 위한 메서드 분리 설계
	protected void redirect(HttpServletResponse response, String path) throws ServletException, IOException {
		response.sendRedirect(contextPath + path);
	}
	
}
