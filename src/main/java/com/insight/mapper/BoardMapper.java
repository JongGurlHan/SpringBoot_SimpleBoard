package com.insight.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.insight.dto.BoardDto;
import com.insight.dto.BoardFileDto;

@Mapper
public interface BoardMapper {
	
	List<BoardDto> selectBoardList() throws Exception;

	void insertBoard(BoardDto board) throws Exception;
	
	void insertBoardFileList(List<BoardFileDto> list) throws Exception;
	
	List<BoardFileDto> selectBoardFileList(int boardIdx) throws Exception;
	
	BoardFileDto selectBoardFileInformation(@Param("idx") int idx,
											@Param("boardIdx") int boardIdx);
	
	void updateHitCount(int boardIdx) throws Exception;
	
	BoardDto selectBoardDetail(int boardidx) throws Exception;

	void updateBoard(BoardDto board);

	void deleteBoard(int boardIdx);
	
}
