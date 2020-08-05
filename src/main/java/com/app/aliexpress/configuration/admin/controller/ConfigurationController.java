package com.app.aliexpress.configuration.admin.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.aliexpress.manager.admin.service.SelectManagerService;
import com.app.aliexpress.manager.admin.service.UpdateManagerService;
import com.app.aliexpress.server.admin.service.CheckServerAppService;
import com.app.aliexpress.server.admin.service.CheckServerEmailServcie;
import com.app.aliexpress.server.admin.service.DeleteServerService;
import com.app.aliexpress.server.admin.service.InsertServerService;
import com.app.aliexpress.server.admin.service.SelectServerListService;
import com.app.aliexpress.server.admin.service.SelectServerService;
import com.app.aliexpress.server.admin.service.UpdateServerService;

@Controller("adminConfigurationController")
public class ConfigurationController {

	@Autowired
	private SelectManagerService selectManagerService;
	@Autowired
	private UpdateManagerService updateManagerService;
	@Autowired
	private SelectServerService selectServerService;
	@Autowired
	private SelectServerListService selectServerListService;
	@Autowired
	private CheckServerAppService checkServerAppService;
	@Autowired
	private CheckServerEmailServcie checkServerEmailService;
	@Autowired
	private InsertServerService insertServerService;
	@Autowired
	private UpdateServerService updateServerService;
	@Autowired
	private DeleteServerService deleteServerService;
	
	@RequestMapping("/management/configuration.aba")
	public String showConfiguration(Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		selectServerListService.selectServerList(map);
		model.addAllAttributes(map);
		return "admin/configuration";
	}
	
	@PostMapping("/management/checkPassword")
	@ResponseBody
	public Map<String, Object> checkPassword(@RequestBody Map<String, Object> map) {
		selectManagerService.selectManager(map);
		return map;
	}
	
	@PostMapping("/management/updateManager")
	@ResponseBody
	public Map<String, Object> updateManager(@RequestBody Map<String, Object> map) {
		updateManagerService.updateManager(map);
		return map;
	}
	
	@PostMapping("/management/selectServerList")
	@ResponseBody
	public Map<String, Object> selectServerList() {
		Map<String, Object> map = new HashMap<String, Object>();
		selectServerListService.selectServerList(map);
		return map;
	}
	
	@PostMapping("/management/checkServerId")
	@ResponseBody
	public Map<String, Object> checkServerId(@RequestBody Map<String, Object> map) {
		selectServerService.selectServer(map);
		return map;
	}
	
	@PostMapping("/management/checkServerApp")
	@ResponseBody
	public Map<String, Object> checkServerApp(@RequestBody Map<String, Object> map) {
		checkServerAppService.checkApp(map);
		return map;
	}
	
	@PostMapping("/management/checkServerEmail")
	@ResponseBody
	public Map<String, Object> checkServerEmail(@RequestBody Map<String, Object> map) {
		checkServerEmailService.checkServerEmail(map);
		return map;
	}
	
	@PostMapping("/management/insertServer")
	@ResponseBody
	public Map<String, Object> insertServer(@RequestBody Map<String, Object> map) {
		insertServerService.insertServer(map);
		return map;
	}
	
	@PostMapping("/management/updateServer")
	@ResponseBody
	public Map<String, Object> updateServer(@RequestBody Map<String, Object> map) {
		updateServerService.updateServerService(map);
		return map;
	}
	
	@PostMapping("/management/deleteServer")
	@ResponseBody
	public Map<String, Object> deleteServer(@RequestBody Map<String, Object> map) {
		deleteServerService.deleteServer(map);
		return map;
	}
}
