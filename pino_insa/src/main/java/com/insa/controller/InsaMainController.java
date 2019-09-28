package com.insa.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class InsaMainController{
	
	@RequestMapping(value = "/insa_main.do", method = RequestMethod.GET)
	public String insaMain(HttpSession ses) {
		return "index";
	}
	
}