package com.miyam.mBoarder.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.miyam.mBoarder.model.BoardDto;

@Mapper
public interface BoardMapper {
	public List<BoardDto> getBoardList();
	public BoardDto selBoardData(BoardDto boardInfo);
	public int boardAdd(BoardDto insertData);
	public int boardUpdate(BoardDto updateData);
	public int boardDelete(int delIdx);
}
