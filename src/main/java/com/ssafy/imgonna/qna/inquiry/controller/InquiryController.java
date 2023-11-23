package com.ssafy.imgonna.qna.inquiry.controller;

import com.ssafy.imgonna.common.annotation.CheckToken;
import com.ssafy.imgonna.qna.inquiry.model.dto.InquiryListResponseDto;
import com.ssafy.imgonna.qna.inquiry.model.dto.InquiryRequestDto;
import com.ssafy.imgonna.qna.inquiry.model.dto.InquiryResponseDto;
import com.ssafy.imgonna.qna.inquiry.service.InquiryService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/qna")
@RestController
public class InquiryController {

    private final InquiryService inquiryService;

    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @PostMapping
    @CheckToken
    public ResponseEntity<?> createInquiry(
            @RequestBody InquiryRequestDto inquiryRequestDto) {
        try {
            inquiryService.createInquiry(inquiryRequestDto);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping
    public ResponseEntity<?> getInquiryList(@RequestParam Map<String, String> map) {
        try {
            InquiryListResponseDto inquiryList = inquiryService.getInquiryList(map);
            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_JSON);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .headers(header)
                    .body(inquiryList);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping("/{inquiryNo}")
    public ResponseEntity<InquiryResponseDto> getInquiryByInquiryNo(
            @PathVariable int inquiryNo) throws Exception {
        InquiryResponseDto inquiryResponseDto = inquiryService.getInquiryByInquiryNo(inquiryNo);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inquiryResponseDto);
    }

    @PutMapping("/{inquiryNo}")
    @CheckToken
    public ResponseEntity<String> modifyInquiry(@PathVariable int inquiryNo, @RequestBody InquiryRequestDto inquiryRequestDto) throws Exception {

        inquiryService.modifyInquiry(inquiryNo, inquiryRequestDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @DeleteMapping("/{inquiryNo}")
    @CheckToken
    public ResponseEntity<String> deleteInquiry(@PathVariable("inquiryNo") int inquiryNo) throws Exception {
        inquiryService.deleteInquiry(inquiryNo);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();

    }

    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error : " + e.getMessage());
    }
}
