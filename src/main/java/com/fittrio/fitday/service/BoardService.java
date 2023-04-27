package com.fittrio.fitday.service;

import java.util.HashMap;
import java.util.List;

import com.fittrio.fitday.dto.BoardDTO;

public interface BoardService {

	int getAllBoardCnt();

	List<BoardDTO> getAllBoardList(int limit);

	BoardDTO getOneBoard(int boardSeq);

	void insertBoard(BoardDTO dto);
    
	void deleteBoard(int boardSeq);
	
	void updateViewCount(int boardSeq);
	
	void updateBoard(HashMap<String,Object> map);

	List<BoardDTO> getAllMissionList();

	void insertMission(BoardDTO dto);
	
	void deleteBoardByUserSeq(int userSeq);

	int getBoardUserSeq(int boardSeq);

}
