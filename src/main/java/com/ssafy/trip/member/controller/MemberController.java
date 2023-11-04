package com.ssafy.trip.member.controller;

import com.ssafy.trip.member.model.dto.Member;
import com.ssafy.trip.member.model.dto.MemberFind;
import com.ssafy.trip.member.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author yihoney
 */
@Controller
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * 회원의 아이디를 찾는 메서드
     *
     * @param member 아이디를 찾기 위해 입력 받은 회원의 정보
     * @param model
     * @return 찾은 회원의 아이디
     */
    @PostMapping("/find/id")
    public String findId(MemberFind member, Model model) {

        String id = memberService.getMemberIdByEmailAndName(member);
        String msg = null;
        if (id == null) {
            msg = "해당하는 회원 정보가 없습니다.";
        }

        if (id != null) {
            msg = "아이디: " + id;
        }
        model.addAttribute("msg", msg);
        return "index";

    }

    /**
     * 회원의 비밀번호를 찾는 메서드
     *
     * @param member 비밀번호를 찾기 위해 입력 받은 회원의 정보
     * @param model
     * @return 찾은 회원의 비밀번호
     */
    @PostMapping("/find/pw")
    private String findPassword(MemberFind member, Model model) {

        String password = memberService.getMemberPasswordByIdAndEmailAndPhone(member);

        String msg = null;

        if (password == null) {
            msg = "해당하는 회원 정보가 없습니다.";
        }

        if (password != null) {
            msg = "비밀번호: " + password;
        }
        model.addAttribute("msg", msg);
        return "index";
    }

    /**
     * 회원의 비밀번호를 수정하는 메서드
     *
     * @param map     입력값을 담은 Map
     * @param model
     * @param session
     * @return
     */
    @PostMapping("/modify/pw")
    private String modifyPassword(Map<String, String> map, Model model, HttpSession session) {
        String oldPassword = map.get("memberPw");
        String newPassword1 = map.get("newPassword1");
        String newPassword2 = map.get("newPassword2");

        String msg = null;

        if (oldPassword == null || newPassword1 == null || newPassword2 == null) {
            msg = "모든 값을 입력해야 비밀번호 변경 가능합니다!";
        }

        if (!(oldPassword == null || newPassword1 == null || newPassword2 == null)) {

            Member member = (Member) session.getAttribute("memberDto");

            if (!oldPassword.equals(member.getPassword())) {
                msg = "기존 비밀번호가 맞지 않습니다. 비밀번호를 다시 입력해주세요.";
            }

            if (oldPassword.equals(member.getPassword())) {

                if (!newPassword1.equals(newPassword2)) {
                    msg = "변경할 비밀번호 재입력을 다시 해주세요. 입력한 비밀번호와 같지 않습니다.";
                }

                if (newPassword1.equals(newPassword2)) {

                    String id = member.getId();
                    int statusCode = memberService.updateMemberPasswordById(id, newPassword2);

                    if (statusCode == 1) {
                        msg = "비밀번호 변경에 성공하였습니다!";
                        member.setPassword(newPassword2);
                        session.setAttribute("memberDto", member);
                    } else {
                        msg = "비밀번호 변경을 실패하였습니다. 비밀번호 변경을 다시 시도해주세요.";
                    }
                }
            }
        }

        model.addAttribute("msg", msg);
        return "/member/mypage";
    }

    /**
     * 회원 탈퇴 처리하는 메서드
     *
     * @param inputPwd 입력 받은 기존 비밀번호
     * @param session
     * @param model
     * @return
     */
    @PostMapping("/delete")
    private String delete(@RequestParam("leaveConfirmPwd") String inputPwd, HttpSession session, Model model) {
        Member member = (Member) session.getAttribute("memberDto");
        String memberPwd = member.getPassword();

        if (inputPwd.equals(memberPwd)) {
            String msg = "회원탈퇴를 정상적으로 처리하였습니다. 이용해주셔서 감사합니다.";
            memberService.delete(member.getId());
            session.removeAttribute("memberDto");
            session.invalidate();
            model.addAttribute("msg", msg);
            return "/";
        } else {
            String msg = "비밀번호가 맞지 않습니다. 비밀번호를 다시 입력해주세요.";
            model.addAttribute("msg", msg);
            return "/member/" + session.getId();
        }
    }

    @GetMapping("/mypage")
    private String mypage(HttpSession session, Model model) {
        Member memberDto = (Member) session.getAttribute("memberDto");
        String id = memberDto.getId();
        Member member = memberService.getMemberById(id);
        model.addAttribute("member", member);
        return "member/mypage";
    }

    @PostMapping("/modify")
    private String modify(HttpSession session, @RequestParam Map<String, String> map, Model model) {

        int statusCode = memberService.updateMember(map, session);
        if (statusCode == 1) { // 성공 페이지
            String msg = "회원수정에 성공하였습니다.";
            model.addAttribute("msg", msg);
        } else {
            String msg = "회원수정에 실패하였습니다. 다시 시도해주세요!";
            model.addAttribute("msg", msg);
        }
        return "index";

    }


    @PostMapping("/regist")
    private String regist(@RequestParam Map<String, String> map, Model model, RedirectAttributes redirect) {

        int statusCode = memberService.createMember(map);
        String msg;
        if (statusCode == 1) { // 성공 페이지
            msg = "회원가입에 성공하였습니다. 로그인 해주세요!";
        } else {
            msg = "회원가입에 실패하였습니다. 다시 시도해주세요!";
        }
        redirect.addFlashAttribute("msg", msg);
        return "redirect:/";
    }

    /**
     * 로그아웃을 처리하는 메서드
     *
     * @param session
     * @param model
     * @return
     */
    @GetMapping("/logout")
    private String logout(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("memberDto") != null) {
            session.removeAttribute("memberDto");
        }
        session.invalidate();
        String msg = "로그아웃 되었습니다!";
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/";
    }

    /**
     * 로그인을 처리하는 메서드
     *
     * @param id
     * @param password
     * @param session
     * @param request
     * @param response
     * @param redirect
     * @return
     */
    @PostMapping("/login")
    private String login(@RequestParam("loginId") String id, @RequestParam("loginPwd") String password, HttpSession session, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirect) {
        System.out.println("id " + id + "pw" + password);
        Member member = memberService.getMemberByIdAndPassword(id, password);

        if (member != null) {
            session.setAttribute("memberDto", member);
            saveCookieId(request, response, id);
        } else {
            String msg = "로그인을 실패하였습니다. 다시 시도해주세요!";
            redirect.addFlashAttribute("msg", msg);
        }
        return "redirect:/";
    }

    /**
     * 아이디를 쿠키에 저장하는 메서드
     *
     * @param request
     * @param response
     * @param id
     */
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


}
