package com.ssafy.imgonna.qna.inquiry.service;

import com.ssafy.imgonna.qna.inquiry.model.dto.InquiryListResponseDto;
import com.ssafy.imgonna.qna.inquiry.model.dto.InquiryRequestDto;
import com.ssafy.imgonna.qna.inquiry.model.dto.InquiryResponseDto;
import com.ssafy.imgonna.qna.inquiry.model.mapper.InquiryMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InquiryServiceImpl implements InquiryService {
    private InquiryMapper inquiryMapper;

    public InquiryServiceImpl(InquiryMapper inquiryMapper) {
        this.inquiryMapper = inquiryMapper;
    }

    @Override
    @Transactional
    public void createInquiry(InquiryRequestDto inquiryRequestDto) {
        inquiryMapper.createInquiry(inquiryRequestDto);
    }

    @Override
    public InquiryResponseDto getInquiryByInquiryNo(int inquiryNo) {
        return inquiryMapper.getInquiryByInquiryNo(inquiryNo);
    }

    @Override
    public InquiryListResponseDto getInquiryList(Map<String, String> map) {
        Map<String, Object> param = new HashMap<>();
        param.put("word", map.get("word") == null ? "" : map.get("word"));
        int currentPage = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
        int sizePerPage = Integer.parseInt(map.get("spp") == null ? "20" : map.get("spp"));
        int start = currentPage * sizePerPage - sizePerPage;
        param.put("start", start);
        param.put("listsize", sizePerPage);

        String key = map.get("key");
        param.put("key", key == null ? "" : key);
        if ("id".equals(key))
            param.put("key", key == null ? "" : "m.id");
        List<InquiryResponseDto> inquiryList = inquiryMapper.getInquiryList(param);

        if ("id".equals(key))
            param.put("key", key == null ? "" : "id");
        int totalArticleCount = inquiryMapper.getTotalInquiresCount(param);
        int totalPageCount = (totalArticleCount - 1) / sizePerPage + 1;

        InquiryListResponseDto inquiryListResponseDto = new InquiryListResponseDto();
        inquiryListResponseDto.setInquiryList(inquiryList);
        inquiryListResponseDto.setCurrentPage(currentPage);
        inquiryListResponseDto.setTotalPageCount(totalPageCount);

        return inquiryListResponseDto;
    }

    @Override
    @Transactional
    public void modifyInquiry(int inquiryNo, InquiryRequestDto inquiryRequestDto) {
        inquiryRequestDto.setInquiryNo(inquiryNo);
        inquiryMapper.modifyInquiry(inquiryRequestDto);
    }

    @Override
    public void deleteInquiry(int InquiryNo) {
		// 글 삭제시 댓글들 먼저 삭제
		inquiryMapper.deleteCommentAll(InquiryNo);
		
		// 글삭제
        inquiryMapper.deleteInquiry(InquiryNo);
    }
}
