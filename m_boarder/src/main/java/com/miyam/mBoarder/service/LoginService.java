package com.miyam.mBoarder.service;

import java.util.List;

import com.miyam.mBoarder.model.BoardUserDto;

public interface LoginService {
	public List<BoardUserDto> getAllBoardUser();
	public BoardUserDto loginUser(String userID, String password);
	public int userAdd(String userID, String password, String name, int grade);
}
