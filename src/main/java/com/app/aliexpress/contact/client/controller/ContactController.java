package com.app.aliexpress.contact.client.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.aliexpress.contact.client.service.InsertContactService;

@Controller("clientContactController")
public class ContactController {

	@Autowired
	private InsertContactService insertContactService;
	
	@RequestMapping("/contact.ab")
	public String showContact() {
		return "client/contact";
	}
	
	@RequestMapping("/insertContact")
	@ResponseBody
	public Map<String, Object> insertContact(@RequestBody Map<String, Object> map) {
		insertContactService.insertContact(map);
		return map;
	}
}
