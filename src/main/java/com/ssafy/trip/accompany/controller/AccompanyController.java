package com.ssafy.trip.accompany.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.trip.accompany.model.AccompanyCommDto;
import com.ssafy.trip.accompany.model.AccompanyDto;
import com.ssafy.trip.member.model.Member;
import com.ssafy.trip.accompany.model.service.AccompanyService;
import com.ssafy.trip.accompany.model.service.AccompanyServiceImpl;
//import com.ssafy.util.FileUtil;
//import com.ssafy.util.QuickSort;

@Controller
@RequestMapping("/accompany")
public class AccompanyController{
	private final Logger logger = LoggerFactory.getLogger(AccompanyController.class);

	private AccompanyService accompanyService;
	
	public AccompanyController(AccompanyService accompanyService) {
		super();
		this.accompanyService = accompanyService;
	}
	
	/** 글 목록 */
	@GetMapping("/list")
	private ModelAndView list(@RequestParam Map<String, String> map) throws Exception{
		logger.debug("accompany list parameter pgno : {}", map.get("pgno"));
		List<AccompanyDto> list = accompanyService.list(map);
		
//		PageNavigation pageNavigation = boardService.makePageNavigation(map);
//		mav.addObject("navigation", pageNavigation);
//		mav.addObject("pgno", map.get("pgno"));
//		mav.addObject("key", map.get("key"));
//		mav.addObject("word", map.get("word"));

		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("accompany/list");
		return mav;		
	}
	
	/** 글 작성폼 호출 */
	@GetMapping("/write")
	public String write(@RequestParam Map<String, String> map, Model model) {
		logger.debug("write call parameter {}", map);
//		model.addAttribute("pgno", map.get("pgno"));
//		model.addAttribute("key", map.get("key"));
//		model.addAttribute("word", map.get("word"));

		return "accompany/write";
	}

	/** 글 작성 */
//	@PostMapping("/write")
//	public String write(BoardDto boardDto, @RequestParam("upfile") MultipartFile[] files, HttpSession session,
//			RedirectAttributes redirectAttributes) throws Exception {
//		logger.debug("write boardDto : {}", boardDto);
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//		boardDto.setUserId(memberDto.getUserId());
//
////		FileUpload 관련 설정.
//		logger.debug("uploadPath : {}, uploadImagePath : {}, uploadFilePath : {}", uploadPath, uploadImagePath, uploadFilePath);
//		logger.debug("MultipartFile.isEmpty : {}", files[0].isEmpty());
//		if (!files[0].isEmpty()) {
////			String realPath = servletContext.getRealPath(UPLOAD_PATH);
////			String realPath = servletContext.getRealPath("/resources/img");
//			String today = new SimpleDateFormat("yyMMdd").format(new Date());
//			String saveFolder = uploadPath + File.separator + today;
//			logger.debug("저장 폴더 : {}", saveFolder);
//			File folder = new File(saveFolder);
//			if (!folder.exists())
//				folder.mkdirs();
//			List<FileInfoDto> fileInfos = new ArrayList<FileInfoDto>();
//			for (MultipartFile mfile : files) {
//				FileInfoDto fileInfoDto = new FileInfoDto();
//				String originalFileName = mfile.getOriginalFilename();
//				if (!originalFileName.isEmpty()) {
//					String saveFileName = UUID.randomUUID().toString()
//							+ originalFileName.substring(originalFileName.lastIndexOf('.'));
//					fileInfoDto.setSaveFolder(today);
//					fileInfoDto.setOriginalFile(originalFileName);
//					fileInfoDto.setSaveFile(saveFileName);
//					logger.debug("원본 파일 이름 : {}, 실제 저장 파일 이름 : {}", mfile.getOriginalFilename(), saveFileName);
//					mfile.transferTo(new File(folder, saveFileName));
//				}
//				fileInfos.add(fileInfoDto);
//			}
//			boardDto.setFileInfos(fileInfos);
//		}
//
//		boardService.writeArticle(boardDto);
//		redirectAttributes.addAttribute("pgno", "1");
//		redirectAttributes.addAttribute("key", "");
//		redirectAttributes.addAttribute("word", "");
//		return "redirect:/article/list";
//	}

}
