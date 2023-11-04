package com.ssafy.trip.attraction.controller;

import com.ssafy.trip.attraction.model.dto.AttractionInfo;
import com.ssafy.trip.attraction.model.dto.Gugun;
import com.ssafy.trip.attraction.model.service.AttractionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yihoney
 */
@RestController
@RequestMapping("/attraction")
@CrossOrigin("*")
public class AttractionRestController {
    private AttractionService attractionService;

    public AttractionRestController(AttractionService attractionService) {
        this.attractionService = attractionService;
    }

    @GetMapping("/gugun")
    private ResponseEntity<?> gugun(@RequestParam int sidoCode) {
        List<Gugun> gugunList = attractionService.getGugunBySidoCode(sidoCode);
        return ResponseEntity.ok().body(gugunList);
    }

    @GetMapping("/list")
    private ResponseEntity<?> list(@RequestParam int sidoCode, @RequestParam int gugunCode, @RequestParam int contentTypeId) {
        Map<String, Integer> map = new HashMap<>();
        map.put("sidoCode", sidoCode);
        map.put("gugunCode", gugunCode);
        map.put("contentTypeId", contentTypeId);

        List<AttractionInfo> attractionList = attractionService.getAttractionInfo(map);
        return ResponseEntity.ok().body(attractionList);
    }
}
