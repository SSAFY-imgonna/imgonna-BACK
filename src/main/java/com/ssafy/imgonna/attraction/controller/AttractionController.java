package com.ssafy.imgonna.attraction.controller;

import com.ssafy.imgonna.attraction.model.dto.AttractionInfo;
import com.ssafy.imgonna.attraction.model.dto.AttractionRequestDto;
import com.ssafy.imgonna.attraction.model.dto.Sido;
import com.ssafy.imgonna.attraction.model.service.AttractionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
        return ResponseEntity
        		.status(HttpStatus.OK)
        		.body(attractionService.getAttractionInfo(attractionRequestDto));
    }

    @GetMapping("/{contentIdList}")
    private ResponseEntity<List<AttractionInfo>> getAttractionListByContentIdList(@PathVariable List<Integer> contentIdList) {
        System.out.println(contentIdList);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(attractionService.getAttractionListByContentIdList(contentIdList));
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
