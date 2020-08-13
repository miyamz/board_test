package com.miyam.mBoarder.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miyam.mBoarder.common.SHA256Util;
import com.miyam.mBoarder.mapper.BoardMapper;
import com.miyam.mBoarder.model.BoardDto;
import com.miyam.mBoarder.model.BoardUserDto;

@Service
public class BoardServiceImpl implements BoardService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	BoardMapper bMapper;
	
	public int getBoardTotalCnt(String searchWord) {
		int retCnt = 0;
		try {
			if (searchWord == null || searchWord.trim().length() == 0)
				retCnt = bMapper.getBoardTotalCnt();
			else
				retCnt = bMapper.getBoardTotalCntWithSearch(searchWord);
		} catch(Exception ex) {
			logger.error(String.format("Board error: [%s] - DB Select TotalCnt Error...", ex.getStackTrace()));
		}
		return retCnt;
	}
	
	public List<BoardDto> getBoardList(int pageNum, int pageSize, String searchWord) {
		List<BoardDto> ret = null;
		
		try {
			if (searchWord == null || searchWord.trim().length() == 0)
				ret = bMapper.getBoardList(pageNum, pageSize);
			else
				ret = bMapper.getBoardListSearch(pageNum, pageSize, searchWord);
		} catch(Exception ex) {
			logger.error(String.format("Board error: [%s] - DB Select List Error...", ex.getStackTrace()));
		}
		
		return ret;
	}
	
	public BoardDto selBoardData(int boardIdx, String passwd) {
		BoardDto data = null;
		
		try {
			data = new BoardDto();
			
			data.setIdx(boardIdx);
			data.setPassword(passwd);
			
			data = bMapper.selBoardData(data);
		} catch(Exception ex) {
			logger.error(String.format("Board error: [%s] - DB Select Error...", ex.getStackTrace()));
		}
		
		return data;
	}
	
	public int boardAdd(int parent_idx, String title, String body, String password, long writer) {
		int insertIdx = 0;
		
		try {
			BoardDto insertData = new BoardDto();

			insertData.setParent_idx(parent_idx);
			insertData.setTitle(title);
			insertData.setBody(body);
			insertData.setPassword(password);
			insertData.setWriter_idx(writer);
			
			insertIdx = bMapper.boardAdd(insertData);
		} catch(Exception ex) {
			logger.error(String.format("Board error: [%s] - DB Insert Error...", ex.getStackTrace()));
		}
		
		return insertIdx;
	}
	
	public int boardUpdate(BoardDto updateData) {
		int successRow = 0;
		
		try {
			successRow = bMapper.boardUpdate(updateData);
		} catch(Exception ex) {
			logger.error(String.format("Board error: [%s] - DB Update Error...", ex.getStackTrace()));
		}
		
		return successRow;
	}
	
	public int boardDelete(int delIdx) {
		int successRow = 0;
		
		try {
			successRow = bMapper.boardDelete(delIdx);
		} catch(Exception ex) {
			logger.error(String.format("Board error: [%s] - DB Delete Error...", ex.getStackTrace()));
		}
		
		return successRow;
	}
}
