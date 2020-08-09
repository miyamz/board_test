package com.miyam.mBoarder.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.miyam.mBoarder.model.BoardUserDto;

@Mapper
public interface BoardUserMapper {
	public void userAdd(BoardUserDto user);
	public List<BoardUserDto> getAllUser();
}
