package com.ssafy.trip.member.controller;

import com.ssafy.trip.member.model.MemberDto;
import com.ssafy.trip.member.service.MemberService;
import com.ssafy.trip.member.service.MemberServiceImpl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/member")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = MemberServiceImpl.getInstance();

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String action = request.getParameter("action");

		switch (action) {
		case "regist":
			regist(request, response);
			break;

		case "login":
			login(request, response);
			break;

		case "findId":
			findId(request, response);
			break;

		case "findPassword":
			findPassword(request, response);
			break;

		case "modify":
			modify(request, response);
			break;

		case "modifyPassword":
			modifyPassword(request, response);
			break;

		case "logout":
			logout(request, response);
			break;

		case "delete":
			delete(request, response);
			break;

		default:
			break;
		}
	}

	private void findId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("findIdEmail");
		String name = request.getParameter("findIdName");

		String id = memberService.getMemberIdByEmailAndName(email, name);

		if (id == null) {
			String msg = "해당하는 회원 정보가 없습니다.";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/").forward(request, response);
		}
		
		if (id != null) {
			String msg = "아이디: " + id;
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/").forward(request, response);
		}
	}

	private void findPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("findPwId");
		String email = request.getParameter("findPwEmail");
		String phone = request.getParameter("findPwPhone");

		String password = memberService.getMemberPasswordByIdAndEmailAndPhone(id, email, phone);

		if (password == null) {
			String msg = "해당하는 회원 정보가 없습니다.";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/").forward(request, response);
		}
		
		if (password != null) {
			String msg = "비밀번호: " + password;
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/").forward(request, response);
		}
	}

	private void modifyPassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String oldPassword = request.getParameter("oldPassword");
		String newPassword1 = request.getParameter("newPassword1");
		String newPassword2 = request.getParameter("newPassword2");

		if (oldPassword == null || newPassword1 == null || newPassword2 == null) {
			String msg = "모든 값을 입력해야 비밀번호 변경 가능합니다!";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/member/mypage.jsp").forward(request, response);
		} else {

			HttpSession session = request.getSession();
			MemberDto memberDto = (MemberDto) session.getAttribute("memberDto");

			if (!oldPassword.equals(memberDto.getPassword())) {
				String msg = "기존 비밀번호가 맞지 않습니다. 비밀번호를 다시 입력해주세요.";
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("/member/mypage.jsp").forward(request, response);
			}

			if (oldPassword.equals(memberDto.getPassword())) {

				if (!newPassword1.equals(newPassword2)) {
					String msg = "변경할 비밀번호 재입력을 다시 해주세요. 입력한 비밀번호와 같지 않습니다.";
					request.setAttribute("msg", msg);
					request.getRequestDispatcher("/member/mypage.jsp").forward(request, response);
				}

				if (newPassword1.equals(newPassword2)) {

					String id = memberDto.getId();
					int statusCode = memberService.updateMemberPasswordById(id, newPassword2);

					if (statusCode == 1) {
						String msg = "비밀번호 변경에 성공하였습니다!";
						request.setAttribute("msg", msg);
						memberDto.setPassword(newPassword2);
						session.setAttribute("memberDto", memberDto);
						request.getRequestDispatcher("/member/mypage.jsp").forward(request, response);
					} else {
						String msg = "비밀번호 변경을 실패하였습니다. 비밀번호 변경을 다시 시도해주세요.";
						request.setAttribute("msg", msg);
						request.getRequestDispatcher("/member/mypage.jsp").forward(request, response);
					}
				}
			}
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String inputPwd = request.getParameter("leaveConfirmPwd");
		MemberDto memberDto = (MemberDto) request.getSession().getAttribute("memberDto");
		String memberPwd = memberDto.getPassword();

		if (inputPwd.equals(memberPwd)) {
			String msg = "회원탈퇴를 정상적으로 처리하였습니다. 이용해주셔서 감사합니다.";
			memberService.delete(memberDto.getId());
			HttpSession session = request.getSession();
			session.removeAttribute("memberDto");
			session.invalidate();
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} else {
			String msg = "비밀번호가 맞지 않습니다. 비밀번호를 다시 입력해주세요.";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/member/mypage.jsp").forward(request, response);
		}
	}

	private void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email") + request.getParameter("emailAdd");
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("memberDto");
		memberDto.setId(request.getParameter("id"));
		memberDto.setEmail(email);
		memberDto.setName(request.getParameter("name"));
		memberDto.setNickname(request.getParameter("nickname"));
		memberDto.setPhone(request.getParameter("phone"));

		int statusCode = memberService.updateMember(memberDto);
		if (statusCode == 1) { // 성공 페이지
			session.setAttribute("memberDto", memberDto);
			String msg = "회원수정에 성공하였습니다.";
			request.setAttribute("msg", msg);
		} else {
			String msg = "회원수정에 실패하였습니다. 다시 시도해주세요!";
			request.setAttribute("msg", msg);
		}
		request.getRequestDispatcher("/index.jsp").forward(request, response);

	}

	private void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("registerEmail") + request.getParameter("registerEmailAdd");
		MemberDto memberDto = new MemberDto();
		memberDto.setRole(request.getParameter("role"));
		memberDto.setId(request.getParameter("registerId"));
		memberDto.setEmail(email);
		memberDto.setName(request.getParameter("registerName"));
		memberDto.setPassword(request.getParameter("registerPw"));
		memberDto.setPhone(request.getParameter("registerPhone"));
		memberDto.setNickname(request.getParameter("registerNickname"));

		int statusCode = memberService.createMember(memberDto);
		if (statusCode == 1) { // 성공 페이지
			String msg = "회원가입에 성공하였습니다. 로그인 해주세요!";
			request.setAttribute("msg", msg);
		} else {
			String msg = "회원가입에 실패하였습니다. 다시 시도해주세요!";
			request.setAttribute("msg", msg);
		}
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("memberDto");
		session.invalidate();
		String msg = "로그아웃 되었습니다!";
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String id = request.getParameter("loginId");
		String password = request.getParameter("loginPwd");

		MemberDto memberDto = memberService.getMemberByIdAndPassword(id, password);

		System.out.println(memberDto);

		if (memberDto != null) {
			HttpSession session = request.getSession();
			session.setAttribute("memberDto", memberDto);

			saveCookieId(request, response, id);

			response.sendRedirect(request.getContextPath());
		} else {
			String msg = "로그인을 실패하였습니다. 다시 시도해주세요!";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}

	private void saveCookieId(HttpServletRequest request, HttpServletResponse response, String id) {

		String[] saveId = request.getParameterValues("saveId");

		// 아이디 체크 저장 처리
		if (saveId != null) {
			if ("checked".equals(saveId[0])) {
				Cookie cookie = new Cookie("memberId", id);
				cookie.setPath(request.getContextPath());
				cookie.setMaxAge(60 * 60 * 24 * 7); // 일주일간 저장
				response.addCookie(cookie);
			}

			if (!"checked".equals(saveId[0])) {
				Cookie cookies[] = request.getCookies();
				if (cookies != null) {
					for (Cookie cookie : cookies) {
						if ("memberId".equals(cookie.getName())) {
							cookie.setMaxAge(0);
							response.addCookie(cookie);
							break;
						}
					}
				}
			}
		}
	}

	public MemberController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
