package com.app.aliexpress.maincontent.admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.aliexpress.maincontent.admin.service.SaveMainContentListService;
import com.app.aliexpress.maincontent.admin.service.SelectMainContentListService;
import com.app.aliexpress.maincontent.dto.MainContentListDto;

@Controller("adminMainContentController")
public class MainContentController {

	@Autowired
	private SelectMainContentListService selectMainContentListService;
	@Autowired
	private SaveMainContentListService saveMainContentListService;
	
	@RequestMapping("/management/mainContent.aba")
	public String showMainContent(HttpServletRequest req, Model model) {
		model.addAttribute("type", req.getParameter("query"));
		return "admin/mainContent";
	}
	
	@PostMapping("/management/selectMainContentList")
	@ResponseBody
	public Map<String, Object> selectMainContentList(@RequestBody Map<String, Object> map) {
		selectMainContentListService.selectMainContentList(map);
		return map;
	}
	
	@PostMapping("/management/saveMainContentList")
	@ResponseBody
	public Map<String, Object> saveMainContentList(MainContentListDto dto, HttpServletRequest req) {
		Map<String, Object> map = new HashMap<String, Object>();
		String realPath = req.getSession().getServletContext().getRealPath("");
		map.put("dto", dto);
		map.put("realPath", realPath);
		saveMainContentListService.saveMainContentList(map);
		return map;
	}
}
