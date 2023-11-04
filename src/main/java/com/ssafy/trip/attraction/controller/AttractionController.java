package com.ssafy.trip.attraction.controller;

import com.ssafy.trip.attraction.model.dto.Sido;
import com.ssafy.trip.attraction.model.service.AttractionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import java.util.List;

/**
 * @author yihoney
 */
@Controller
@RequestMapping("/attraction")
public class AttractionController extends HttpServlet {
    private AttractionService attractionService;

    public AttractionController(AttractionService attractionService) {
        this.attractionService = attractionService;
    }

    @GetMapping
    private String view(Model model) {
        List<Sido> sidoList = attractionService.getSidoList();
        model.addAttribute("sidoList", sidoList);
        return "attraction/view";
    }
}
