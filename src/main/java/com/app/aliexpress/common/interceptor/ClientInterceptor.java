package com.app.aliexpress.common.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.app.aliexpress.server.client.service.SelectServerService;

@Component("clientInterceptor")
public class ClientInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private SelectServerService clientSelectServerService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		HttpSession session = request.getSession();
		
		if (session.getAttribute("locale") == null) {
			session.setAttribute("locale", request.getLocale().toString());
		}
		
		String locale = request.getParameter("locale");
		
		if (locale != null) {
			session.setAttribute("locale", locale);
		}
		
		if (session.getAttribute("clientServerYoutube") == null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("serverUseYN", "Y");
			clientSelectServerService.selectServer(map);
			session.setAttribute("clientServerYoutube", map.get("SERVER_YOUTUBE"));
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
	}
}
