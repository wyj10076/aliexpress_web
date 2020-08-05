package com.app.aliexpress.contact.admin.controller;

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

import com.app.aliexpress.contact.admin.service.ReplyContactService;
import com.app.aliexpress.contact.admin.service.SelectContactListService;
import com.app.aliexpress.contact.admin.service.SelectContactService;

@Controller("adminContactController")
public class ContactController {

	@Autowired
	private SelectContactListService selectContactListService;
	@Autowired
	private SelectContactService selectContactService;
	@Autowired
	private ReplyContactService replyContactService;
	
	@RequestMapping("/management/contactList.aba")
	public String showContactList() {
		return "admin/contactList";
	}
	
	@PostMapping("/management/selectContactList")
	@ResponseBody
	public Map<String, Object> selectContactList() {
		Map<String, Object> map = new HashMap<String, Object>();
		selectContactListService.selectContactList(map);
		return map;
	}
	
	@RequestMapping("/management/contact.aba")
	public String showContact(HttpServletRequest req, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("contactId", req.getParameter("query"));
		map.put("reply", req.getParameter("reply"));
		
		selectContactService.selectContact(map);
		model.addAllAttributes(map);
		return "admin/contact";
	}
	
	@PostMapping("/management/replyContact")
	@ResponseBody
	public Map<String, Object> replyContact(@RequestBody Map<String, Object> map) {
		replyContactService.replyContact(map);
		return map;
	}
}
