package com.miyam.mBoarder.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.miyam.mBoarder.common.CommonCraft;
import com.miyam.mBoarder.model.BoardDto;
import com.miyam.mBoarder.model.BoardUserDto;
import com.miyam.mBoarder.service.BoardService;

@Controller
public class BoardController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping(value= "/index.do", method= RequestMethod.GET)
	public ModelAndView viewMainPage() {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("pageTitle", "Main Page");
		
		return mv;
	}
	
	@RequestMapping(value= "/boardList.do", method= RequestMethod.GET)
	public ModelAndView viewBoardListPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("board/boardList");
		mv.addObject("pageTitle", "BoardList Page");
		
		String pageNumStr = request.getParameter("pn");
		String pageSizeStr = request.getParameter("ps");
		
		int pageNum = 1;
		if (NumberUtils.isDigits(pageNumStr) == true)
			pageNum = Integer.parseInt(pageNumStr);

		int pageSize = 10;
		if (NumberUtils.isDigits(pageSizeStr) == true)
			pageSize = Integer.parseInt(pageSizeStr);
		
		List<BoardDto> boardList = boardService.getBoardList(pageNum, pageSize);
		int totalCnt = boardService.getBoardTotalCnt();
		// 필요한 숫자 전부 계산
		int totalPageCnt = totalCnt % pageSize != 0 ? (totalCnt / pageSize) + 1 : (totalCnt / pageSize);
		int startPageNum = pageNum % pageSize == 0 ? (((pageNum / pageSize) - 1) * pageSize) + 1 : ((pageNum / pageSize) * pageSize) + 1;
		int endPageNum = startPageNum + (pageSize - 1);
		endPageNum = endPageNum > totalPageCnt ? totalPageCnt : endPageNum;
		int prevPageNum = startPageNum - 1;
		int nextPageNum = endPageNum + 1;
		nextPageNum = nextPageNum > totalPageCnt ? 0 : nextPageNum;
		
		mv.addObject("boardList", boardList);
		mv.addObject("totalCnt", totalCnt);
		mv.addObject("totalPageCnt", totalPageCnt);
		mv.addObject("startPageNum", startPageNum);
		mv.addObject("endPageNum", endPageNum);
		mv.addObject("prevPageNum", prevPageNum);
		mv.addObject("nextPageNum", nextPageNum);
		
		return mv;
	}
	
	@RequestMapping(value= "/boardView.do", method= RequestMethod.GET)
	public ModelAndView viewBoardViewPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("board/boardView");
		mv.addObject("pageTitle", "BoardView Page");
		
		String boardIdxStr = request.getParameter("i");
		
		if (boardIdxStr == null) {
			mv.addObject("isLoad", 0);
			return mv;
		}
		
		int boardIdx = 0;
		if (NumberUtils.isDigits(boardIdxStr) == true)
			boardIdx = Integer.parseInt(boardIdxStr);
		
		String password = request.getParameter("pw");
		BoardDto bData = boardService.selBoardData(boardIdx, password);
		
		if (bData == null) {
			mv.addObject("isLoad", 0);
			return mv;
		}
		
		// 게시글에 비밀번호가 있는데 입력비밀번호가 없거나 틀린경우 게시글 못봄
		if (bData.getPassword().length() > 0) {
			if (password == null || (password != null && bData.getPassword().equals(password) == false)) {
				mv.addObject("isLoad", 2);
				return mv;
			}
		}

		// 본인글일때 나타날 버튼을 위해 세션정보를 넘기도록 하자
		CommonCraft commonMethod = CommonCraft.getInstance();
		String sessionValue = (String)WebUtils.getSessionAttribute(request, LoginController.SESSION_KEY);
		BoardUserDto loginDto = commonMethod.getSessionObj(sessionValue, BoardUserDto.class);

		mv.addObject("isLoad", 1);
		mv.addObject("loginSession", loginDto);
		mv.addObject("boardData", bData);
		
		return mv;
	}
	
	@RequestMapping(value= "/boardWrite.do", method= RequestMethod.GET)
	public ModelAndView viewBoardWritePage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("board/boardWrite");
		mv.addObject("pageTitle", "BoardWrite Page");
		
		String boardIdxStr = request.getParameter("bi");
		if (boardIdxStr != null && boardIdxStr.length() > 0) {
			// 글 수정 일때..
			int boardIdx = 0;
			if (NumberUtils.isDigits(boardIdxStr) == true)
				boardIdx = Integer.parseInt(boardIdxStr);

			BoardDto bData = boardService.selBoardData(boardIdx, null);

			CommonCraft commonMethod = CommonCraft.getInstance();
			String sessionValue = (String)WebUtils.getSessionAttribute(request, LoginController.SESSION_KEY);
			BoardUserDto loginDto = commonMethod.getSessionObj(sessionValue, BoardUserDto.class);
			
			// 로그인한 유저가 쓴글이 아니면 수정할 수 없다. 
			if (bData.getWriter_idx() != loginDto.getIdx())
				mv.addObject("isMod", 0);
			
			mv.addObject("isMod", 1);
			mv.addObject("boardData", bData);
		}

		String parentIdx = request.getParameter("pi");
		if (parentIdx != null && parentIdx.length() > 0) {
			// 댓글쓰기
			mv.addObject("parentBoardIdx", parentIdx);
		}
		
		return mv;
	}
	
	@RequestMapping(value= "/pwCheckPopup.do", method= RequestMethod.GET)
	public ModelAndView viewPasswdCheckPage() {
		ModelAndView mv = new ModelAndView("board/boardPasswordCheck");
		mv.addObject("pageTitle", "PWCheck Page");
		
		return mv;
	}
	
	@RequestMapping(value= "/boardWrite.do", method= RequestMethod.POST)
	public ModelAndView boardWrite(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("board/boardWrite");
		
		// 로그인 처리만 한다.. 그 뒤 index페이지로 redirect
		String title = request.getParameter("boardTitle");
		String body = request.getParameter("hdboardContentStr");
		String passwd = request.getParameter("boardPasswd");
		String modIdx = request.getParameter("modBoardIdx");
		String parentIdxStr = request.getParameter("parentBoardIdx");

		// interceptor에서 세션객체의 여부는 체크하니 들어있다고 가정
		CommonCraft commonMethod = CommonCraft.getInstance();
		String sessionValue = (String)WebUtils.getSessionAttribute(request, LoginController.SESSION_KEY);
		BoardUserDto loginDto = commonMethod.getSessionObj(sessionValue, BoardUserDto.class);

		if (modIdx.length() != 0) {
			// 글 수정
			int bModIdx = 0;
			if (NumberUtils.isDigits(modIdx) == true)
				bModIdx = Integer.parseInt(modIdx);
			BoardDto bData = boardService.selBoardData(bModIdx, null);
			
			bData.setTitle(title);
			bData.setBody(body);
			
			if (passwd.length() > 0)
				bData.setPassword(passwd);
			
			int ret = boardService.boardUpdate(bData);

			if (ret != 0)
				mv.addObject("isSuccess", 1);
			else
				mv.addObject("isSuccess", 0);
			
			return mv;
		} else {
			int parentIdx = 0;
			if (NumberUtils.isDigits(parentIdxStr) == true) {
				parentIdx = Integer.parseInt(parentIdxStr);
			}
			Long writer = loginDto.getIdx();
			
			int addIdx = boardService.boardAdd(parentIdx, title, body, passwd, writer);
			
			if (addIdx != 0)
				mv.addObject("isSuccess", 1);
			else
				mv.addObject("isSuccess", 0);
		}
		
		return mv;
	}
	
	@RequestMapping(value= "/boardDelete.do", method= RequestMethod.POST)
	public ModelAndView deleteBoard(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("board/boardView");
		
		String boardIdxStr = request.getParameter("hdBoardIdx");
		int boardIdx = 0;
		if (NumberUtils.isDigits(boardIdxStr) == true)
			boardIdx = Integer.parseInt(boardIdxStr);

		BoardDto bData = boardService.selBoardData(boardIdx, null);

		CommonCraft commonMethod = CommonCraft.getInstance();
		String sessionValue = (String)WebUtils.getSessionAttribute(request, LoginController.SESSION_KEY);
		BoardUserDto loginDto = commonMethod.getSessionObj(sessionValue, BoardUserDto.class);
		
		// 로그인한 유저가 쓴글이 아니면 지울수 없다. 
		// (히든 태그를 이용해 viewPage에서 파라미터로 받을수도 있지만 툴을 이용한 post호출까지는 막을 수 없다. 따라서 한번더 보드정보 조회)
		if (bData.getWriter_idx() != loginDto.getIdx())
			mv.addObject("isDelete", 0);
			
		int ret = boardService.boardDelete(boardIdx);
		
		if (ret > 0)
			mv.addObject("isDelete", 1);
		else
			mv.addObject("isDelete", 0);
		
		return mv;
	}
}
