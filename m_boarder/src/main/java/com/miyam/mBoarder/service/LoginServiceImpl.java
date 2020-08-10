package com.miyam.mBoarder.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miyam.mBoarder.common.SHA256Util;
import com.miyam.mBoarder.mapper.BoardUserMapper;
import com.miyam.mBoarder.model.BoardUserDto;

@Service
public class LoginServiceImpl implements LoginService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BoardUserMapper buMapper;
	
	public List<BoardUserDto> getAllBoardUser() {
		return buMapper.getAllUser();
	}
	
	public BoardUserDto loginUser(String userID, String password) {
		BoardUserDto retUserInfo = null;
		
		try {
			BoardUserDto user = new BoardUserDto();
			// password 암호화
			String encPasswd = SHA256Util.getEncrypt(password);
			
			user.setUser_id(userID);
			user.setPassword(encPasswd);
			
			retUserInfo = buMapper.sLoginUser(user);
		} catch(Exception ex) {
			logger.error("Login error: [%s] - DB Error...", ex.getStackTrace());
		}
		
		return retUserInfo;
	}
	
	public int userAdd(String userID, String password, String name, int grade) {
		int resultIdx = 0;
		
		try {
			BoardUserDto user = new BoardUserDto();
			// password 암호화
			String encPasswd = SHA256Util.getEncrypt(password);
			
			user.setUser_id(userID);
			user.setPassword(encPasswd);
			user.setName(name);
			user.setGrade(grade);
			
			resultIdx = buMapper.userAdd(user);
		} catch(Exception ex) {
			logger.error("Login error: [%s] - DB Error...", ex.getStackTrace());
		}
		
		return resultIdx;
	}
}
