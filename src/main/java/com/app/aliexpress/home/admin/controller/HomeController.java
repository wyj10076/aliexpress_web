package com.app.aliexpress.home.admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.aliexpress.manager.admin.service.SelectManagerService;
import com.app.aliexpress.server.admin.service.SelectServerService;

@Controller("adminHomeController")
public class HomeController {

	@Autowired
	private SelectManagerService selectManagerService;
	@Autowired
	private SelectServerService selectServerService;
	
	@RequestMapping(value = {"/management", "/management/dashboard.aba"})
	public String showDashboard() {
		return "admin/dashboard";
	}
	
	
	@RequestMapping("/management/login.aba")
	public String showLogin() {
		return "admin_login";
	}
	
	@PostMapping("/management/login")
	@ResponseBody
	public Map<String, Object> login(@RequestBody Map<String, Object> map, HttpSession session) {
		selectManagerService.selectManager(map);
		
		if (map.get("result").equals("success")) {
			map.put("serverUseYN", "Y");
			selectServerService.selectServer(map);
			
			session.setAttribute("manager", map.get("manager"));
			session.setAttribute("server", map.get("server"));
		}
		return map;
	}
	
	@PostMapping("/management/logout")
	@ResponseBody
	public Map<String, Object> logout(HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		if (session.getAttribute("manager") != null) {
			session.invalidate();
			map.put("result", "success");
		} else {
			map.put("result", "fail");
		}
		
		return map;
	}
}
