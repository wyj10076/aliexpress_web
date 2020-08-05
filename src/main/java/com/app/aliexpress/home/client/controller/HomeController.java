package com.app.aliexpress.home.client.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.aliexpress.maincontent.client.service.SelectMainContentListService;

@Controller("clientHomeController")
public class HomeController {
	
	@Autowired
	private SelectMainContentListService selectMainContentListService;
	
	@RequestMapping(value = {"/", "/home.ab"})
	public String showHome(Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mainContentType", "banner");
		selectMainContentListService.selectMainContentList(map);
		map.put("mainContentType", "front");
		selectMainContentListService.selectMainContentList(map);
		
		model.addAllAttributes(map);
		return "client/home";
	}
	
}
