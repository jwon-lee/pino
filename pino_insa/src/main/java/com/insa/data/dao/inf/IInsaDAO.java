package com.insa.data.dao.inf;

import java.util.List;
import java.util.Map;

import com.insa.data.vo.InsaComVO;
import com.insa.data.vo.InsaVO;
import com.insa.data.vo.SearchVO;

public interface IInsaDAO {

	//1. 인사정보 전체 조회
	public List<InsaVO> showAllInsaInfomation(SearchVO search);
	
	//1-2. 인사정보 1개 조회
	public InsaVO showOneInsaDetail(int sabun);
	
	//1-3. 인사정보 1개 조회
	public InsaVO showOneInsaDetail(String userId);
	
	//1-4. 아이디 중복 체크
	public int showInsaCount(String userId);
	
	//1-5. 현재 아이디와 동일한지 체크
	public InsaVO showInsaCount(int sabun);
	
	//2. 인사정보 입력
	public int insertInsaData(InsaVO insa);
	
	//3. 인사정보 수정
	public int updateInsaData(InsaVO insa);
	
	//4. 인사정보 삭제
	public int deleteInsaData(int sabun);
	
	//5. 공통코드
	public List<InsaComVO> showCom(String gubun);
	
	//6. 맥스페이지
	public int computeMaxPageNumber();
	public int computeMaxPageNumber(SearchVO search);
	
	//7. 보드카운트
	public int countNumberOfBoards();
	public int countNumberOfBoards(SearchVO search);
}
