package com.ssafy.trip.member.controller;

import com.ssafy.trip.member.model.dto.Member;
import com.ssafy.trip.member.model.dto.MemberFind;
import com.ssafy.trip.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author yihoney
 */
@RestController
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
     * @return 찾은 회원의 아이디
     */
    @PostMapping("/find/id")
    public ResponseEntity<String> findMemberId(MemberFind member) {

        String id = memberService.getMemberIdByEmailAndName(member);

        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

    /**
     * 회원의 비밀번호를 찾는 메서드
     *
     * @param member 비밀번호를 찾기 위해 입력 받은 회원의 정보
     * @return 찾은 회원의 비밀번호
     */
    @PostMapping("/find/pw")
    private ResponseEntity<String> findMemberPassword(MemberFind member, Model model) {

        String password = memberService.getMemberPasswordByIdAndEmailAndPhone(member);

        return ResponseEntity.status(HttpStatus.OK).body(password);
    }


    /**
     * 회원의 비밀번호를 수정하는 메서드
     *
     * @param id 수정할 회원의 id
     * @param map  수정할 정보를 담은 Map
     * @param redirectAttributes
     * @param session
     * @return
     */
    @PutMapping("/{id}/pw")
    private ResponseEntity<List<Member>> modifyMemberPassword(@PathVariable String id, @RequestParam Map<String, String> map, RedirectAttributes redirectAttributes, HttpSession session) {

        memberService.updateMemberPasswordById(id, map);
        List<Member> list = memberService.getMemberList(null);

        return ResponseEntity.status(HttpStatus.OK).body(list);

    }



    /**
     * 회원 탈퇴 처리하는 메서드
     *
     * @param id 탈퇴 처리할 회원
 * @param inputPwd  입력 받은 기존 비밀번호
     * @param session
     * @param redirectAttributes
     * @return
     */
    @DeleteMapping("/{id}}")
    private ResponseEntity<List<Member>> deleteMember(@PathVariable String id, @RequestParam("leaveConfirmPwd") String inputPwd, HttpSession session, RedirectAttributes redirectAttributes) {
        memberService.delete(id, inputPwd);
        List<Member> list = memberService.getMemberList(null);

        return ResponseEntity.status(HttpStatus.OK).body(list);

    }

    @GetMapping("/{id}")
    private ResponseEntity<Member> getMemberById(@PathVariable String id) {
        Member member = memberService.getMemberById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(member);
    }


    /**
     * 회원 정보를 수정하는 메서드
     *
     * @param id
     * @param session
     * @param map 이름, 전화번호, 닉네임, mbti, 소개글
     * @return
     */
    @PutMapping("/{id}")
    private ResponseEntity<List<Member>> modifyMember(@PathVariable String id, HttpSession session, @RequestParam Map<String, String> map) {

        memberService.updateMember(id, map, session);
        List<Member> list = memberService.getMemberList(null);

        return ResponseEntity.status(HttpStatus.OK).body(list);

    }


    /**
     * 회원 가입
     * @param map 가입할 회원이 입력한 정보
     * @return
     */
    @PostMapping("/regist")
    private ResponseEntity<List<Member>> registMember(@RequestParam Map<String, String> map) {

        memberService.createMember(map);
        List<Member> list = memberService.getMemberList(null);
        return ResponseEntity.status(HttpStatus.OK)
                .body(list);
    }

//    /**
//     * 로그아웃을 처리하는 메서드
//     *
//     * @param session
//     * @return
//     */
//    @GetMapping("/logout")
//    private String logout(HttpSession session) {
//        if (session.getAttribute("memberDto") != null) {
//            session.removeAttribute("memberDto");
//        }
//        session.invalidate();
//        return "redirect:/";
////    }
////
////    /**
////     * 로그인을 처리하는 메서드
////     *
////     * @param id
////     * @param password
////     * @param session
////     * @param request
////     * @param response
////     * @param redirect
////     * @return
////     */
////    @PostMapping("/login")
////    private String login(@RequestParam("loginId") String id, @RequestParam("loginPwd") String password, HttpSession
////            session, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirect) {
////        System.out.println("id " + id + "pw" + password);
////        Member member = memberService.getMemberByIdAndPassword(id, password);
////
////        if (member != null) {
////            session.setAttribute("memberDto", member);
////            saveCookieId(request, response, id);
////        } else {
////            String msg = "로그인을 실패하였습니다. 다시 시도해주세요!";
////            redirect.addFlashAttribute("msg", msg);
////        }
////        return "redirect:/";
////    }
//
//    /**
//     * 아이디를 쿠키에 저장하는 메서드
//     *
//     * @param request
//     * @param response
//     * @param id
//     */
//    private void saveCookieId(HttpServletRequest request, HttpServletResponse response, String id) {
//
//        String[] saveId = request.getParameterValues("saveId");
//
//        // 아이디 체크 저장 처리
//        if (saveId != null) {
//            if ("checked".equals(saveId[0])) {
//                Cookie cookie = new Cookie("memberId", id);
//                cookie.setPath(request.getContextPath());
//                cookie.setMaxAge(60 * 60 * 24 * 7); // 일주일간 저장
//                response.addCookie(cookie);
//            }
//
//            if (!"checked".equals(saveId[0])) {
//                Cookie cookies[] = request.getCookies();
//                if (cookies != null) {
//                    for (Cookie cookie : cookies) {
//                        if ("memberId".equals(cookie.getName())) {
//                            cookie.setMaxAge(0);
//                            response.addCookie(cookie);
//                            break;
//                        }
//                    }
//                }
//            }
//        }
//    }


}
