package com.miyam.mBoarder.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.miyam.mBoarder.model.BoardUserDto;

@Mapper
public interface BoardUserMapper {
	public List<BoardUserDto> getAllUser();
	public BoardUserDto sLoginUser(BoardUserDto loginInfo);
	public int userAdd(BoardUserDto userInfo);
}
