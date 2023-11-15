package com.ssafy.trip.common.advisor;


import com.ssafy.trip.exception.member.MemberNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * rest controller에서 예외 발생 시 처리
 *
 * @author yihoney
 */
@RestControllerAdvice
public class RestControllerAdvisor {

    /**
     * 400에 해당하는 예외들을 한번에 처리하는 메서드
     *
     * @param e 실제 발생한 예외객체
     * @return 에러 메세지를 response entity에 담아 전송
     */
    @ExceptionHandler(value = {
            MemberNotFoundException.class
    })
    public ResponseEntity<String> badRequestException400(Exception e) {

        String errMsg = e.getMessage();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errMsg);
    }

//    /**
//     * 401에 해당하는 예외들을 한번에 처리하는 메서드
//     * *
//     * * @param e 실제 발생한 예외객체
//     * * @return 에러 메세지를 response entity에 담아 전송
//     */
//    @ExceptionHandler(value = {
//
//    })
//    public ResponseEntity<String> unauthorizedException401(Exception e) {
//
//        String errMsg = e.getMessage();
//
//        return ResponseEntity
//                .status(HttpStatus.UNAUTHORIZED)
//                .body(errMsg);
//    }

//    /**
//     * 403에 해당하는 예외들을 한번에 처리하는 메서드
//     * *
//     * * @param e 실제 발생한 예외객체
//     * * @return 에러 메세지를 response entity에 담아 전송
//     */
//    @ExceptionHandler(value = {
//
//    })
//    public ResponseEntity<String> forbiddenException403(Exception e) {
//
//        String errMsg = e.getMessage();
//
//        return ResponseEntity
//                .status(HttpStatus.FORBIDDEN)
//                .body(errMsg);
//    }

    /**
     * 500에 해당하는 예외들을 한번에 처리하는 메서드
     * *
     * * @param e 실제 발생한 예외객체
     * * @return 에러 메세지를 response entity에 담아 전송
     */
    @ExceptionHandler(value = {RuntimeException.class, Exception.class})
    public ResponseEntity<String> internalErrorException500(Exception e) {

        String errMsg = e.getMessage();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errMsg);
    }

}
