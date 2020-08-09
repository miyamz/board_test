package com.miyam.mBoarder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.miyam.mBoarder.model.BoardUserDto;
import com.miyam.mBoarder.service.LoginService;

@Controller
public class LoginController {
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value= "/login.do", method= RequestMethod.GET)
	public ModelAndView viewLoginPage() {
		ModelAndView mv = new ModelAndView("login");
		
		return mv;
	}
}
