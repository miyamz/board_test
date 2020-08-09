package com.miyam.mBoarder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miyam.mBoarder.mapper.BoardUserMapper;
import com.miyam.mBoarder.model.BoardUserDto;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private BoardUserMapper buMapper;
	
	public List<BoardUserDto> getAllBoardUser() {
		return buMapper.getAllUser();
	}
}
