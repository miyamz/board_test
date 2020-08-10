package com.miyam.mBoarder.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.miyam.mBoarder.common.enumclass.PermissionCheckURL;
import com.miyam.mBoarder.common.enumclass.SiteAuthority;
import com.miyam.mBoarder.controller.LoginController;
import com.miyam.mBoarder.model.BoardUserDto;

@Component
public class HttpInterceptor extends HandlerInterceptorAdapter {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String url = request.getRequestURI();
		if(logger.isInfoEnabled() == true) {
			logger.info("==================================   START   ======================================");
			logger.info("Request URI \t: " + url);
		}
		
		try {
			if (exceptCheckURL(url, SiteAuthority.ALL) == true) {
				return true;
			}
			
			CommonCraft commonMethod = CommonCraft.getInstance();
			String sessionValue = (String)WebUtils.getSessionAttribute(request, LoginController.SESSION_KEY);
			BoardUserDto loginDto = commonMethod.getSessionObj(sessionValue, BoardUserDto.class);
			
			if (loginDto == null || loginDto.getUser_id() == null) {
				logger.error("empty user session!! go to login page..");
				String redirectUrl = request.getContextPath() + "/login.do";
				response.sendRedirect(redirectUrl);
				return false;
			}
			else {
				if (exceptCheckURL(url, SiteAuthority.valueOf(loginDto.getGrade())) == false) {
					// 권한 없음 페이지로 이동
					logger.error("Unregistered url. Permission denied");
					String redirectUrl = request.getContextPath() + "/login.do";
					response.sendRedirect(redirectUrl);
					return false;
				}
			}
				
			return super.preHandle(request, response, handler);
		} catch (Exception ex) {
			StringBuilder sb = new StringBuilder();
			sb.append(String.format("%s \n", ex.getMessage()));
			sb.append(String.format("%s \n", ex.getStackTrace().toString()));
			logger.error(sb.toString());
			
			String redirectUrl = request.getContextPath() + "/error";
			response.sendRedirect(redirectUrl);
			return false;
		}
	}
	
	private boolean exceptCheckURL(String url, SiteAuthority auth) throws Exception {
		switch(auth) {
		case MANAGER:
		case GUEST:
			if (urlChecker(url, PermissionCheckURL.VIEWPAGE_URL) == true)
				return true;
		default:
			// auth가 없는 경우(로그인 안된 경우.. 세션이 날라간 경우...) 로그인 페이지와 가입페이지, 비밀번호 찾는페이지, 에러페이지는 보인다.
			if(urlChecker(url, PermissionCheckURL.LOGIN_URL) == true)
				return true;
			
			if(urlChecker(url, PermissionCheckURL.REGIST_URL) == true)
				return true;
			
			if(urlChecker(url, PermissionCheckURL.ERROR_URL) == true)
				return true;
			
			if(urlChecker(url, PermissionCheckURL.RESOURCE_PATH) == true)
				return true;
		}
		return false;
	}
	
	private boolean urlChecker(String url, PermissionCheckURL urls) throws Exception {
		for (String checkUrl : urls.GetCheckUrlList()) {
			if (url.indexOf(checkUrl) > -1) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if(logger.isInfoEnabled() == true) {
			logger.info("==================================   END   ======================================");
		}
	}
}
