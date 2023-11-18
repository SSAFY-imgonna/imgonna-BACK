package com.ssafy.imgonna.member.controller;

import com.ssafy.imgonna.member.model.dto.*;
import com.ssafy.imgonna.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 회원 컨트롤러입니다.
 *
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
     * 회원 아이디 중복 체크를 하는 메서드
     *
     * @param id 중복 체크를 할 아이디 입력값
     * @return
     */
    @GetMapping("/check/id")
    public ResponseEntity<String> checkDuplicateMemberId(@RequestParam String id) {

        memberService.checkDuplicateId(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(id);
    }

    /**
     * 회원의 아이디를 찾는 메서드
     *
     * @param member 아이디를 찾기 위해 입력 받은 회원의 정보
     * @return 찾은 회원의 아이디
     */
    @PostMapping("/find/id")
    public ResponseEntity<String> findMemberId(@RequestBody MemberFindRequestDto member) {

        String id = memberService.getMemberIdByEmailAndName(member);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(id);
    }

    /**
     * 회원의 비밀번호를 찾는 메서드
     *
     * @param member 비밀번호를 찾기 위해 입력 받은 회원의 정보
     * @return 찾은 회원의 비밀번호
     */
    @PostMapping("/find/pw")
    public ResponseEntity<String> findMemberPassword(@RequestBody MemberFindRequestDto member) {

        String password = memberService.getMemberPasswordByIdAndEmailAndPhone(member);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(password);
    }


    /**
     * 회원의 비밀번호를 변경하는 메서드
     *
     * @param id         수정할 회원의 id
     * @param requestDto 수정할 정보
     * @return
     */
    @PutMapping("/{id}/pw")
    public ResponseEntity<Void> modifyMemberPassword(@PathVariable String id,
                                                     @RequestBody MemberModifyPwRequestDto requestDto) {

        memberService.updateMemberPasswordById(id, requestDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();

    }


    /**
     * 회원 탈퇴 처리하는 메서드
     *
     * @param id         탈퇴 처리할 회원
     * @param requestDto 입력 받은 기존 비밀번호
     * @return
     */
    @PostMapping("/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable String id,
                                               @RequestBody MemberDeleteRequestDto requestDto) {
        memberService.delete(id, requestDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(id);

    }

    /**
     * 회원 정보 조회하는 메서드
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable String id) {
        Member member = memberService.getMemberById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(member);
    }


    /**
     * 회원 정보를 수정하는 메서드
     *
     * @param id
     * @param requestDto
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<MemberDetailsDto> modifyMember(@PathVariable String id, MemberModifyRequestDto requestDto,
                                                         @RequestPart(required = false) MultipartFile upfile) throws IOException {

        MemberDetailsDto member = memberService.updateMember(id, requestDto, upfile);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(member);

    }


    /**
     * 회원 가입
     *
     * @param requestDto 가입할 회원이 입력한 정보
     * @return
     */
    @PostMapping
    public ResponseEntity<MemberDetailsDto> registMember(MemberSignUpRequestDto requestDto,
                                                         @RequestPart(required = false) MultipartFile upfile) throws IOException {
        MemberDetailsDto member = memberService.createMember(requestDto, upfile);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(member);
    }

    /**
     * 로그인을 처리하는 메서드
     *
     * @param requestDto
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<MemberLoginResponseDto> login(@RequestBody MemberLoginRequestDto requestDto) {

        MemberLoginResponseDto responseDto = memberService.loginMember(requestDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseDto);
    }

    /**
     * 회원 인증
     *
     * @param id 인증할 회원의 아이디
     * @return
     */
    @GetMapping("/info/{id}")
    public ResponseEntity<MemberDetailsDto> getInfo(
            @PathVariable String id, @RequestHeader("Authorization") String authorization) {

        MemberDetailsDto member = memberService
                .getInfo(id, authorization);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(member);
    }

    /**
     * 로그아웃 - 토큰 제거
     */
    @GetMapping("/logout/{id}")
    public ResponseEntity<Void> removeToken(@PathVariable String id) {

        memberService.deleteRefreshToken(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    /**
     * Access Token 재발급
     */

    @PostMapping("/refresh")
    public ResponseEntity<String> refreshToken(@RequestBody String id,
                                               @RequestHeader("refreshToken") String token)
            throws Exception {

        String accessToken = memberService.refreshToken(token, id);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(accessToken);
    }


//
//    /**
//     * 아이디를 쿠키에 저장하는 메서드
//     *
//     * @param request
//     * @param response
//     * @param id
//     */
//    public void saveCookieId(HttpServletRequest request, HttpServletResponse response, String id) {
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
