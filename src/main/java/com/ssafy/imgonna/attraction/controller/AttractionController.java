package com.ssafy.imgonna.attraction.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.imgonna.attraction.model.dto.AttractionInfo;
import com.ssafy.imgonna.attraction.model.dto.AttractionRequestDto;
import com.ssafy.imgonna.attraction.model.dto.Sido;
import com.ssafy.imgonna.attraction.model.service.AttractionService;

/**
 * @author yihoney
 */
@RestController
@RequestMapping("/attractions")
@CrossOrigin("*")
public class AttractionController {
    private AttractionService attractionService;

    public AttractionController(AttractionService attractionService) {
        this.attractionService = attractionService;
    }

    
    @GetMapping
    private ResponseEntity<List<AttractionInfo>> getAttractionList(AttractionRequestDto attractionRequestDto) {
        Map<String, Integer> map = new HashMap<>();
        map.put("sidoCode", attractionRequestDto.getSidoCode());
        map.put("gugunCode", attractionRequestDto.getGugunCode());
        map.put("contentTypeId", attractionRequestDto.getContentTypeId());

        return ResponseEntity
        		.status(HttpStatus.OK)
        		.body(attractionService.getAttractionInfo(map));
    }
    
    @GetMapping("/gugun")
    private ResponseEntity<?> gugun(@RequestParam(name = "sido") int sidoCode) {
        return ResponseEntity
        		.status(HttpStatus.OK)
        		.body(attractionService.getGugunBySidoCode(sidoCode));
    }

    @GetMapping("/sido")
	public ResponseEntity<List<Sido>> sido() throws Exception {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(attractionService.getSidoList());
	}
    


}
