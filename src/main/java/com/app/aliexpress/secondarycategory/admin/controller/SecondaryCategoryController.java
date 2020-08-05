package com.app.aliexpress.secondarycategory.admin.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.aliexpress.secondarycategory.admin.service.DeleteFileSecondaryCategoryService;
import com.app.aliexpress.secondarycategory.admin.service.SaveSecondaryCategoryService;
import com.app.aliexpress.secondarycategory.admin.service.SelectSecondaryCategoryListService;
import com.app.aliexpress.secondarycategory.admin.service.SelectSecondaryCategoryService;

@RestController("adminSecondaryCategoryController")
public class SecondaryCategoryController {

	@Autowired
	private SelectSecondaryCategoryListService selectSecondaryCategoryListService;
	@Autowired
	private SaveSecondaryCategoryService saveSecondaryCategoryService;
	@Autowired
	private SelectSecondaryCategoryService selectSecondaryCategoryService;
	@Autowired
	private DeleteFileSecondaryCategoryService deleteFileSecondaryCategoryService;
	
	@PostMapping("/management/selectSecondaryCategoryList")
	public Map<String, Object> selectSecondaryCategoryList(@RequestBody Map<String, Object> map) {
		selectSecondaryCategoryListService.selectSecondaryCategoryList(map);
		return map;
	}
	
	@PostMapping("/management/saveSecondaryCategoryList")
	public Map<String, Object> saveSecondaryCategoryList(HttpServletRequest req, @RequestBody Map<String, Object> map) {
		String realPath = req.getSession().getServletContext().getRealPath("");
		map.put("realPath", realPath);
		saveSecondaryCategoryService.saveSecondaryCategoryList(map);
		return map;
	}
	
	@PostMapping("/management/selectSecondaryCategory")
	public Map<String, Object> selectSecondaryCategory(@RequestBody Map<String, Object> map) {
		selectSecondaryCategoryService.selectSecondaryCategory(map);
		return map;
	}
	
	@PostMapping("/management/deleteFileSecondaryCategory")
	public Map<String, Object> deleteFileSecondaryCategory(HttpServletRequest req, @RequestBody Map<String, Object> map) {
		String realPath = req.getSession().getServletContext().getRealPath("");
		map.put("realPath", realPath);
		deleteFileSecondaryCategoryService.deleteFileSecondaryCategory(map);
		return map;
	}
}
