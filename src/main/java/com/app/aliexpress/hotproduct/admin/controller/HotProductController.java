package com.app.aliexpress.hotproduct.admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.app.aliexpress.hotproduct.admin.service.SaveHotProductService;
import com.app.aliexpress.primarycategory.admin.service.SelectPrimaryCategoryListService;

@Controller("adminHotProductController")
public class HotProductController {

	@Autowired
	private SelectPrimaryCategoryListService selectPrimaryCategoryListService;
	@Autowired
	private SaveHotProductService saveHotProductService;
	
	@RequestMapping("/management/hotProduct.aba")
	public String showHotProduct(Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		selectPrimaryCategoryListService.selectPrimaryCategoryList(map);
		model.addAllAttributes(map);
		return "admin/hotProduct";
	}
	
	@PostMapping("/management/saveHotProduct")
	@ResponseBody
	public Map<String, Object> saveHotProduct(HttpServletRequest req,
												@RequestParam Map<String, Object> map,
												@RequestParam(required=false) MultipartFile hotProductExcel) {
		
		String realPath = req.getSession().getServletContext().getRealPath("");
		map.put("realPath", realPath);
		map.put("hotProductExcel", hotProductExcel);
		
		saveHotProductService.saveHotProduct(map);
		return map;
	}
}
