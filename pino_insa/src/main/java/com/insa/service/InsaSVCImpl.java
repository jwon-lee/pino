package com.insa.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.insa.data.dao.inf.IInsaDAO;
import com.insa.data.vo.InsaComVO;
import com.insa.data.vo.InsaVO;
import com.insa.data.vo.SearchVO;

@Service
public class InsaSVCImpl implements IInsaSVC{

	@Autowired
	@Qualifier("insaMybatis")
	private IInsaDAO isDao;
	
	@Override
	public List<InsaVO> showAllInsaInfomation(SearchVO search) {
		int pn = search.getPn();
//		System.out.println("서비스 pn : " + pn);
		int page = ((pn-1) * 10) + 1;
		int offset = page + 10;
		search.setPn(page);
		search.setOffset(offset);
		System.out.println("pn : " + pn + ", offset : " + offset + ", page : " + page);
		return isDao.showAllInsaInfomation(search);
	}
	
	@Override
	public InsaVO showOneInsaDetail(int sabun) {
		return isDao.showOneInsaDetail(sabun);
	}
	
	@Override
	public InsaVO showOneInsaDetail(String userId) {
		return isDao.showOneInsaDetail(userId);
	}

	@Override
	public int showInsaCount(String userId) {
		return isDao.showInsaCount(userId);
	}

	@Override
	public InsaVO showInsaCount(int sabun) {
		return isDao.showInsaCount(sabun);
	}

	@Override
	public int insertInsaData(InsaVO insa) {
		return isDao.insertInsaData(insa);
	}

	@Override
	public int updateInsaData(InsaVO insa) {
		return isDao.updateInsaData(insa);
	}

	@Override
	public int deleteInsaData(int sabun) {
		return isDao.deleteInsaData(sabun);
	}

	@Override
	public List<InsaComVO> showCom(String gubun) {
		return isDao.showCom(gubun);
	}

	@Override
	public int computeMaxPageNumber() {
		return 0;
	}

	@Override
	public int computeMaxPageNumber(SearchVO search) {
		int boardCount = isDao.countNumberOfBoards(search);
//		System.out.println("boardCount : " + boardCount);
//		int offset = search.getOffset();
		int maxPn = boardCount / 10 + (boardCount % 10 == 0 ? 0 : 1);
		return maxPn;
	}
	
	
}
