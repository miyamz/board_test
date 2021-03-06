package com.miyam.mBoarder.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.miyam.mBoarder.model.BoardUserDto;
import com.miyam.mBoarder.service.LoginService;

@Controller
public class LoginController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	public static final String SESSION_KEY = "managerLogin";
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value= "/login.do", method= RequestMethod.GET)
	public ModelAndView viewLoginPage() {
		ModelAndView mv = new ModelAndView("user/login");
		mv.addObject("pageTitle", "Login Page");
		
		return mv;
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public ModelAndView progressLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("user/login");
		mv.addObject("pageTitle", "Login Page");
		// 로그인 처리만 한다.. 그 뒤 index페이지로 redirect
		String userID = request.getParameter("userID");
		String passwd = request.getParameter("passwd");
		
		BoardUserDto userInfo = loginService.loginUser(userID, passwd);
		
		if (userInfo == null) {
			mv.addObject("loginFail", "1");

			return mv;
		} else {
			/*if (managerInfo.getPw_findkey() != null && managerInfo.getPw_findkey().length() > 0) {
				// 비밀번호 초기화 페이지로 이동
				String redirectUrl = request.getContextPath() + "/modifyPass.do?r=" + managerInfo.getIdx();
				response.sendRedirect(redirectUrl);
				return null;
			} else {
				HttpSession session = request.getSession();
				session.setAttribute(LoginController.SESSION_KEY, managerInfo.toString());
			}*/
			HttpSession session = request.getSession();
			session.setAttribute(LoginController.SESSION_KEY, userInfo.toString());
		}
		
		mv.addObject("loginFail", "0");
		return mv;
	}
	
	@RequestMapping(value="/regist.do", method=RequestMethod.GET)
	public ModelAndView managerRegistView() throws Exception {
		ModelAndView mv = new ModelAndView("user/register");
		mv.addObject("pageTitle", "Register Page");
		
		return mv;
	}
	
	@RequestMapping(value="/regist.do", method=RequestMethod.POST)
	public ModelAndView progressRegist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("user/register");
		mv.addObject("pageTitle", "Register Page");
		
		String userID = request.getParameter("userID");
		String passwd = request.getParameter("passwd");
		String name = request.getParameter("name");
		int grade = 90;
		
		int resultKey = loginService.userAdd(userID, passwd, name, grade);
		
		if (resultKey > 0) {
			mv.addObject("isClose", 1);
			return mv;
		} else {
			mv.addObject("isClose", 2);
			return mv;
		}
	}

	@RequestMapping(value="/logout.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> logOut(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> ret = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		// 세션 삭제 (모든 속성 삭제는 invalidate함수)
		session.removeAttribute(LoginController.SESSION_KEY);
		
		ret.put("logout", "1");
		return ret;
	}
	
	// 페이스북 정보를 이용한 가입
	@RequestMapping(value="/fbRegist.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> facebookRegistUser(@RequestBody Map<String, Object> jsonData) throws Exception {
		String id = (String)jsonData.get("id");
		String name = (String)jsonData.get("name");
		String password = "facebook:" + id;
		Map<String, Object> ret = new HashMap<String, Object>();
		int grade = 90;
		
		int resultKey = loginService.userAdd(id, password, name, grade);
		
		if (resultKey > 0) {
			ret.put("isRegist", 1);
		} else {
			ret.put("isRegist", 0);
		}
		
		return ret;
	}
	
	// 페이스북 정보를 이용한 로그인
	@RequestMapping(value="/fbLogin.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> facebookLoginUser(@RequestBody Map<String, Object> jsonData, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = (String)jsonData.get("id");
		String password = "facebook:" + id;
		Map<String, Object> ret = new HashMap<String, Object>();
		
		BoardUserDto userInfo = loginService.loginUser(id, password);
		
		if (userInfo == null) {
			ret.put("isLogin", 0);
		} else {
			HttpSession session = request.getSession();
			session.setAttribute(LoginController.SESSION_KEY, userInfo.toString());
			ret.put("isLogin", 1);
		}
		
		return ret;
	}
	
	// 페이스북 정보를 이용한 로그아웃
	@RequestMapping(value="/fbLogout.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> facebookLogOutUser(HttpServletRequest request) throws Exception {
		Map<String, Object> ret = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		// 세션 삭제 (모든 속성 삭제는 invalidate함수)
		session.removeAttribute(LoginController.SESSION_KEY);
		
		ret.put("logout", "1");
		
		return ret;
	}
}
