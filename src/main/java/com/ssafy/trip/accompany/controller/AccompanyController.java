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
import org.springframework.beans.factory.annotation.Autowired;
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
import com.ssafy.trip.accompany.model.FileInfoDto;
import com.ssafy.trip.member.model.Member;
import com.ssafy.trip.accompany.model.service.AccompanyService;
import com.ssafy.trip.accompany.model.service.AccompanyServiceImpl;
//import com.ssafy.util.FileUtil;
//import com.ssafy.util.QuickSort;

@Controller
@RequestMapping("/accompany")
public class AccompanyController{
	private final Logger logger = LoggerFactory.getLogger(AccompanyController.class);
	
	@Value("${file.path}")
	private String uploadPath;
	
	@Value("${file.path.upload-images}")
	private String uploadImagePath;
	
	@Value("${file.path.upload-files}")
	private String uploadFilePath;
	
	@Autowired
	private ServletContext servletContext;
	
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
	@PostMapping("/write")
	public String write(AccompanyDto accompanyDto, @RequestParam String accompanyDate, @RequestParam String accompanyTime,
			@RequestParam("upfile") MultipartFile[] files, HttpSession session,
			RedirectAttributes redirectAttributes) throws Exception {
		Member memberDto = (Member) session.getAttribute("memberDto");
		accompanyDto.setId(memberDto.getId());
		accompanyDto.setNickname(memberDto.getNickname());
		
		accompanyDate = accompanyDate + " " + accompanyTime + ":00"; // 초를 "00"으로 초기화
		accompanyDto.setAccompanyDate(accompanyDate);
		logger.debug("write AccompanyDto : {}", accompanyDto);
		
		// FileUpload 관련 설정
		logger.debug("uploadPath : {}, uploadImagePath : {}, uploadFilePath : {}", uploadPath, uploadImagePath, uploadFilePath);
		logger.debug("MultipartFile.isEmpty : {}", files[0].isEmpty());
		if (!files[0].isEmpty()) { // 파일 1개라도 업로드했다면
			String realPath = servletContext.getRealPath("/upload");
			String today = new SimpleDateFormat("yyMMdd").format(new Date());
			String saveFolder = uploadPath + File.separator + today;
			logger.debug("저장 폴더 : {}", saveFolder);
			File folder = new File(saveFolder);
			if (!folder.exists()) // 해당 폴더(upload 및 날짜별 폴더)가 존재하지 않을경우, 생성해줌
				folder.mkdirs();
			List<FileInfoDto> fileInfos = new ArrayList<FileInfoDto>();
			for (MultipartFile mfile : files) {
				FileInfoDto fileInfoDto = new FileInfoDto();
				String originalFileName = mfile.getOriginalFilename(); // 원본 파일 이름
				if (!originalFileName.isEmpty()) {
					// java.util.UUID 클래스를 사용하여 UUID(Universally Unique Identifier)를 생성하고 문자열로 반환
					String saveFileName = UUID.randomUUID().toString()
							+ originalFileName.substring(originalFileName.lastIndexOf('.')); // 파일 확장자([ex].jpg)만 가져옴
					fileInfoDto.setSaveFolder(today); // 해당 날짜별 폴더에 저장되어있음
					fileInfoDto.setOriginalFile(originalFileName);
					fileInfoDto.setSaveFile(saveFileName);
					logger.debug("원본 파일 이름 : {}, 실제 저장 파일 이름 : {}", mfile.getOriginalFilename(), saveFileName);
					mfile.transferTo(new File(folder, saveFileName));
				}
				fileInfos.add(fileInfoDto);
			}
			accompanyDto.setFileInfos(fileInfos);
		}

		accompanyService.write(accompanyDto);
//		redirectAttributes.addAttribute("pgno", "1");
//		redirectAttributes.addAttribute("key", "");
//		redirectAttributes.addAttribute("word", "");
		return "redirect:/accompany/list";
	}
	
	/** 글 상세 */
	@GetMapping("/view")
	public String view(@RequestParam int accompanyNo, @RequestParam Map<String, String> map, Model model)
			throws Exception {
		logger.debug("view accompanyNo : {}", accompanyNo);
		AccompanyDto accompanyDto = accompanyService.getAccompanyByAccompanyNo(accompanyNo);
		logger.debug("view accompanyDto : {}", accompanyDto);
//		accompanyService.updateHit(accompanyNo);
		model.addAttribute("accompanyDto", accompanyDto);
		
//		model.addAttribute("pgno", map.get("pgno"));
//		model.addAttribute("key", map.get("key"));
//		model.addAttribute("word", map.get("word"));
		return "accompany/view";
	}

	
	/** 글 수정폼 호출 */
	@GetMapping("/modify")
	public String modify(@RequestParam int accompanyNo, @RequestParam Map<String, String> map, Model model)
			throws Exception {
		logger.debug("modify accompanyNo : {}", accompanyNo);
		AccompanyDto accompanyDto = accompanyService.getAccompanyByAccompanyNo(accompanyNo);
		logger.debug("view accompanyDto : {}", accompanyDto);
		
		
		String[] splitDate = accompanyDto.getAccompanyDate().split(" ");
		String accompanyDate = splitDate[0];
		String accompanyTime = splitDate[1];
		
		logger.debug("accompanyDate : {}, accompanyTime : {}", accompanyDate, accompanyTime);
		model.addAttribute("accompanyDto", accompanyDto);
		model.addAttribute("accompanyDate", accompanyDate);
		model.addAttribute("accompanyTime", accompanyTime);
		
//		model.addAttribute("pgno", map.get("pgno"));
//		model.addAttribute("key", map.get("key"));
//		model.addAttribute("word", map.get("word"));
		return "accompany/modify";
	}

	/** 글 수정 */
//	@PostMapping("/modify")
//	public String modify(AccompanyDto accompanyDto, @RequestParam Map<String, String> map,
//			RedirectAttributes redirectAttributes) throws Exception {
//		logger.debug("modify AccompanyDto : {}", accompanyDto);
//		accompanyService.modifyAccompany(accompanyDto);
//		
////		redirectAttributes.addAttribute("pgno", map.get("pgno"));
////		redirectAttributes.addAttribute("key", map.get("key"));
////		redirectAttributes.addAttribute("word", map.get("word"));
//		return "redirect:/accompany/list";
//	}

	/** 글 삭제 */
	@GetMapping("/delete")
	public String delete(@RequestParam int accompanyNo, @RequestParam Map<String, String> map,
			RedirectAttributes redirectAttributes) throws Exception {
		logger.debug("delete accompanyNo : {}", accompanyNo);

		accompanyService.deleteAccompany(accompanyNo, uploadPath);
		
//		redirectAttributes.addAttribute("pgno", map.get("pgno"));
//		redirectAttributes.addAttribute("key", map.get("key"));
//		redirectAttributes.addAttribute("word", map.get("word"));
		return "redirect:/accompany/list";
	}
}
