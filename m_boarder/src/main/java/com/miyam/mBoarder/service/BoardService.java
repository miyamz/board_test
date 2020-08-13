package com.miyam.mBoarder.service;

import java.util.List;

import com.miyam.mBoarder.model.BoardDto;

public interface BoardService {
	public int getBoardTotalCnt(String searchWord);
	public List<BoardDto> getBoardList(int pageNum, int pageSize, String searchWord);
	public BoardDto selBoardData(int boardIdx, String passwd);
	public int boardAdd(int parent_idx, String title, String body, String password, long writer);
	public int boardUpdate(BoardDto updateData);
	public int boardDelete(int delIdx);
}
