package com.miyam.mBoarder.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
		
		return mv;
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public ModelAndView progressLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("user/login");
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
		
		return mv;
	}
	
	@RequestMapping(value="/regist.do", method=RequestMethod.POST)
	public ModelAndView progressRegist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("user/register");
		
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
	/*
	@RequestMapping(value="/resetRequest.do", method=RequestMethod.GET)
	public ModelAndView requestPasswordReset() {
		ModelAndView mv = new ModelAndView("/pages/forgotPW");
		return mv;
	}
	
	@RequestMapping(value="/resetRequest.do", method=RequestMethod.POST)
	public ModelAndView requestPasswordReset(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("/pages/forgotPW");
		RandomUtil ru = RandomUtil.getInstance();
		
		String email = request.getParameter("email");
		String ranpw = ru.getRandomPassword(10);
		
		boolean result = managerLoginService.requestResetPassword(email, ranpw);
		
		if (result == true) {
			mv.addObject("isReset", 1);
			return mv;
		} else {
			mv.addObject("isReset", 0);
			return mv;
		}
	}
	
	@RequestMapping(value="/modifyPass.do", method=RequestMethod.GET)
	public ModelAndView modifyPasswordView(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, Exception {
		ModelAndView mv = new ModelAndView("/pages/modifyPW");
		
		String rs = request.getParameter("r");
		String mail = "";
		if (rs == null) rs = "";
		if (rs.length() > 0) {
			ManagerInfoVO loginVO = managerLoginService.getManagerInfoByIdx(Integer.parseInt(rs));

			if (loginVO != null)
				mail = loginVO.getEmail();
		}
		mv.addObject("user_mail", mail);
		mv.addObject("is_reset", rs);
		return mv;
	}
	
	@RequestMapping(value="/modifyPass.do", method=RequestMethod.POST)
	public ModelAndView modifyPassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("/pages/modifyPW");
		
		String is_reset = request.getParameter("is_reset");
		String mail = request.getParameter("email");
		String prevpw = request.getParameter("prevpass");
		String newpw = request.getParameter("passwd");
		
		boolean result = managerLoginService.modifyPassword(is_reset, mail, prevpw, newpw);
		
		if (result == true) {
			mv.addObject("is_reset", is_reset);
			mv.addObject("res", "1");
		} else {
			mv.addObject("is_reset", is_reset);
			mv.addObject("res", "0");
		}
		
		return mv;
	}
	*/
	@RequestMapping(value="/logout.do", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> logOut(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> ret = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		// 세션 삭제 (모든 속성 삭제는 invalidate함수)
		session.removeAttribute(LoginController.SESSION_KEY);
		
		ret.put("logout", "1");
		return ret;
	}
}
