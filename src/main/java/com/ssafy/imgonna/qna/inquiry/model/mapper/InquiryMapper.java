package com.ssafy.imgonna.qna.inquiry.model.mapper;

import com.ssafy.imgonna.qna.inquiry.model.dto.InquiryRequestDto;
import com.ssafy.imgonna.qna.inquiry.model.dto.InquiryResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface InquiryMapper {
   void createInquiry(InquiryRequestDto inquiryRequestDto);
   InquiryResponseDto getInquiryByInquiryNo(int inquiryNo);
   List<InquiryResponseDto> getInquiryList(Map<String, Object> map);
   int getTotalInquiresCount(Map<String, Object> param);
   void modifyInquiry(InquiryRequestDto inquiryRequestDto);
   void deleteCommentAll(int InquiryNo);
   void deleteInquiry(int inquiryNo);

}
