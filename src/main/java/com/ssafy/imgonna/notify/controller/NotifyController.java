package com.ssafy.imgonna.notify.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.imgonna.accompany.controller.AccompanyController;
import com.ssafy.imgonna.notify.model.dto.Notify;
import com.ssafy.imgonna.notify.model.service.NotifyService;

@RestController
@RequestMapping("/notify")
public class NotifyController {
    private final Logger logger = LoggerFactory.getLogger(AccompanyController.class);

    private NotifyService notifyService;

	public NotifyController(NotifyService notifyService) {
		super();
		this.notifyService = notifyService;
	}
    
    @GetMapping 
    public ResponseEntity<?> getNotifyCount(@RequestParam Map<String, String> map) throws Exception {
    	logger.debug("getNotifyCount map : {}", map);
    	
    	int cnt = notifyService.getNotifyCount(map);
    	
    	HttpHeaders header = new HttpHeaders();
    	header.setContentType(MediaType.APPLICATION_JSON);
    	return ResponseEntity
    			.status(HttpStatus.OK)
    			.headers(header)
    			.body(cnt);    
    }
    
    @GetMapping("/list") 
    public ResponseEntity<?> getNotifyList(@RequestParam Map<String, String> map) throws Exception {
    	logger.debug("getNotifyList map : {}", map);
    	
    	List<Notify> notifyList = notifyService.getNotifyList(map);
    	
    	HttpHeaders header = new HttpHeaders();
    	header.setContentType(MediaType.APPLICATION_JSON);
    	return ResponseEntity
    			.status(HttpStatus.OK)
    			.headers(header)
    			.body(notifyList);    
    }
    
    @GetMapping("/mypage") 
    public ResponseEntity<?> getNotifyListAll(@RequestParam Map<String, String> map) throws Exception {
    	logger.debug("getNotifyListAll map : {}", map);
    	
    	List<Notify> notifyListAll = notifyService.getNotifyListAll(map);
    	
    	HttpHeaders header = new HttpHeaders();
    	header.setContentType(MediaType.APPLICATION_JSON);
    	return ResponseEntity
    			.status(HttpStatus.OK)
    			.headers(header)
    			.body(notifyListAll);    
    }
    
    @GetMapping("/read")
    public ResponseEntity<?> updateNotify(@RequestParam Map<String, String> map) throws Exception {
    	logger.debug("updateNotify map : {}", map);
    	
    	notifyService.updateNotify(Integer.parseInt(map.get("notifyNo")));
    	
    	return ResponseEntity
    			.status(HttpStatus.OK)
    			.build(); 
    }  
    
    @GetMapping("/readAll")
    public ResponseEntity<?> updateNotifyAll(@RequestParam Map<String, String> map) throws Exception {
    	logger.debug("updateNotifyAll map : {}", map);
    	
    	notifyService.updateNotifyAll(map.get("id"));
    	
    	return ResponseEntity
    			.status(HttpStatus.OK)
    			.build(); 
    }   
    
    @PostMapping
    public ResponseEntity<?> deleteNotifyList(@RequestBody List<String> list) throws Exception {
    	logger.debug("deleteNotifyList map : {}", list);
    	
    	notifyService.deleteNotifyList(list);
    	
    	return ResponseEntity
    			.status(HttpStatus.OK)
    			.build(); 
    }   
}
