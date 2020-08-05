package com.app.aliexpress.hotproduct.client.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.aliexpress.hotproduct.client.service.GenerateLinkService;
import com.app.aliexpress.hotproduct.client.service.SelectHotProductListService;
import com.app.aliexpress.primarycategory.client.service.SelectPrimaryCategoryListService;

@Controller("clientHotProductController")
public class HotProductController {

	@Autowired
	private SelectPrimaryCategoryListService selectPrimaryCategoryListService;
	@Autowired
	private SelectHotProductListService selectHotProductListService;
	@Autowired
	private GenerateLinkService generateLinkService;
	
	@RequestMapping("/hotProduct.ab")
	public String showHotProduct(Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		selectPrimaryCategoryListService.selectPrimaryCategoryList(map);
		model.addAllAttributes(map);
		return "client/hotProduct";
	}
	
	@PostMapping("/selectHotProductList")
	@ResponseBody
	public Map<String, Object> selectHotProductList(@RequestBody Map<String, Object> map) {
		selectHotProductListService.selectHotProductListService(map);
		return map;
	}
	
	@PostMapping("/generateLink")
	@ResponseBody
	public Map<String, Object> generateLink(@RequestBody Map<String, Object> map) {
		generateLinkService.generateLink(map);
		return map;
	}
}
