package com.ssafy.trip.enjoytrip.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.trip.enjoytrip.model.AttractionInfoDto;
import com.ssafy.trip.enjoytrip.model.GugunDto;
import com.ssafy.trip.enjoytrip.model.SidoDto;
import com.ssafy.trip.enjoytrip.model.dao.GugunDao;
import com.ssafy.trip.enjoytrip.model.dao.GugunDaoImpl;
import com.ssafy.trip.enjoytrip.model.dao.SidoDao;
import com.ssafy.trip.enjoytrip.model.dao.SidoDaoImpl;
import com.ssafy.trip.enjoytrip.model.service.AttractionService;
import com.ssafy.trip.enjoytrip.model.service.AttractionServiceImpl;

@WebServlet("/attraction")
public class AttractionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private SidoDao sidoDao = SidoDaoImpl.getInstance();
	private GugunDao gugunDao = GugunDaoImpl.getInstance();
	private AttractionService attractionService = AttractionServiceImpl.getInstance();
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		switch (action) {
			case "view":
				view(request, response);
				break;
			case "gugun":
				gugun(request, response);
				break;
			case "list":
				list(request, response);
				break;
			default:
				response.sendRedirect(request.getContextPath() + "/index.jsp");
				break;
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int sidoCode = Integer.parseInt(request.getParameter("sidoCode"));
		int gugunCode = Integer.parseInt(request.getParameter("gugunCode"));
		int contentTypeId = Integer.parseInt(request.getParameter("contentTypeId"));
		
		Map<String, Integer> map = new HashMap<>();
		map.put("sidoCode", sidoCode);
		map.put("gugunCode", gugunCode);
		map.put("contentTypeId", contentTypeId);
		
		List<AttractionInfoDto> attractionList = attractionService.getAttractionInfo(map);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("attractionList", attractionList);
		response.setContentType("application/json;charset=utf-8");
		mapper.writeValue(response.getWriter(), resultMap);
	}

	private void gugun(HttpServletRequest request, HttpServletResponse response)
			throws IOException, StreamWriteException, DatabindException {
		int sidoCode = Integer.parseInt(request.getParameter("sidoCode"));
		List<GugunDto> gugunList = gugunDao.getGugunBySidoCode(sidoCode);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("gugunList", gugunList);
		response.setContentType("application/json;charset=utf-8");
		mapper.writeValue(response.getWriter(), resultMap);
	}
	
	private void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<SidoDto> sidoList = sidoDao.getSidoList();
		request.setAttribute("sidoList", sidoList);
		request.getRequestDispatcher("/attraction/view.jsp").forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		process(request, response);
	}

}
