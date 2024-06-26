package com.ssafy.imgonna.qna.inquiry.service;

import com.ssafy.imgonna.qna.inquiry.model.dto.InquiryListResponseDto;
import com.ssafy.imgonna.qna.inquiry.model.dto.InquiryRequestDto;
import com.ssafy.imgonna.qna.inquiry.model.dto.InquiryResponseDto;

import java.util.Map;


public interface InquiryService {
    void createInquiry(InquiryRequestDto inquiryRequestDto);
    InquiryResponseDto getInquiryByInquiryNo(int inquiryNo);
    InquiryListResponseDto getInquiryList(Map<String, String> map);
    void modifyInquiry(int inquiryNo, InquiryRequestDto inquiryRequestDto);
    void deleteInquiry(int inquiryNo);

}
