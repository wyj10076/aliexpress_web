package com.app.aliexpress.primarycategory.admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.aliexpress.primarycategory.admin.service.SavePrimaryCategoryService;
import com.app.aliexpress.primarycategory.admin.service.SelectPrimaryCategoryListService;

@Controller("adminPrimaryCategoryController")
public class PrimaryCategoryController {

	@Autowired
	private SelectPrimaryCategoryListService selectPrimaryCategoryListService;
	@Autowired
	private SavePrimaryCategoryService savePrimaryCategoryService;
	
	@RequestMapping("/management/categoryList.aba")
	public String showCategory() {
		return "admin/categoryList";
	}
	
	@PostMapping("/management/selectPrimaryCategoryList")
	@ResponseBody
	public Map<String, Object> selectPrimaryCategoryList() {
		Map<String, Object> map = new HashMap<String, Object>();
		selectPrimaryCategoryListService.selectPrimaryCategoryList(map);
		return map;
	}
	
	@PostMapping("/management/savePrimaryCategoryList")
	@ResponseBody
	public Map<String, Object> savePrimaryCategoryList(HttpServletRequest req, @RequestBody Map<String, Object> map) {
		String realPath = req.getSession().getServletContext().getRealPath("");
		map.put("realPath", realPath);
		savePrimaryCategoryService.savePriamryCategoryList(map);
		return map;
	}
}
