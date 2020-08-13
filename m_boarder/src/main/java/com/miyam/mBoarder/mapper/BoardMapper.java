package com.miyam.mBoarder.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.miyam.mBoarder.model.BoardDto;

@Mapper
public interface BoardMapper {
	public int getBoardTotalCnt();
	public List<BoardDto> getBoardList(int pageNum, int pageSize);
	public int getBoardTotalCntWithSearch(String searchWord);
	public List<BoardDto> getBoardListSearch(int pageNum, int pageSize, String searchWord);
	public BoardDto selBoardData(BoardDto boardInfo);
	public int boardAdd(BoardDto insertData);
	public int boardUpdate(BoardDto updateData);
	public int boardDelete(int delIdx);
}
