package com.app.aliexpress.secondarycategory.client.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.aliexpress.secondarycategory.client.service.SelectSecondaryCategoryListService;

@Controller("clientSecondaryCategoryController")
public class SecondaryCategoryController {

	@Autowired
	private SelectSecondaryCategoryListService selectSecondaryCategoryListService;
	
	@PostMapping("/selectSecondaryCategoryList")
	@ResponseBody
	public Map<String, Object> selectSecondaryCategoryList(@RequestBody Map<String, Object> map) {
		selectSecondaryCategoryListService.selectSecondaryCategoryList(map);
		return map;
	}
}
