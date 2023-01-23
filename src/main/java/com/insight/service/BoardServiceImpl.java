package com.insight.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.insight.common.FileUtils2;
import com.insight.dto.BoardDto;
import com.insight.dto.BoardFileDto;
import com.insight.mapper.BoardMapper;

//@Slf4j
@Service
@Transactional
public class BoardServiceImpl implements BoardService{

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FileUtils2 fileUtils;
	
	@Autowired
	private BoardMapper boardMapper;

	@Override
	public List<BoardDto> selectBoardList() throws Exception {
		return boardMapper.selectBoardList();
	}

	@Override
	public void insertBoard(BoardDto board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
		
		boardMapper.insertBoard(board);
		
		List<BoardFileDto> list = fileUtils.parseFileInfo(board.getBoardIdx(), multipartHttpServletRequest);
		
		if(CollectionUtils.isEmpty(list) == false){			
			boardMapper.insertBoardFileList(list);
		}
	
	}

	@Override
	public BoardDto selectBoardDetail(int boardIdx) throws Exception {
		
		
		BoardDto board = boardMapper.selectBoardDetail(boardIdx);
		
		List<BoardFileDto> fileList = boardMapper.selectBoardFileList(boardIdx);
		board.setFileList(fileList);
				
		boardMapper.updateHitCount(boardIdx);
		return board;
	}
	
	
	@Override
	public BoardFileDto selectBoardFileInformation(int idx, int boardIdx) throws Exception { 
		return boardMapper.selectBoardFileInformation(idx, boardIdx);
	}
	
	

	@Override
	public void updateBoard(BoardDto board) throws Exception {
		boardMapper.updateBoard(board);
		
	}

	@Override
	public void deleteBoard(int boardIdx) throws Exception {
		boardMapper.deleteBoard(boardIdx);
		
	}

	
	
	

}
