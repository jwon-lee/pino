package com.insa.data.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.insa.data.dao.inf.IInsaDAO;
import com.insa.data.vo.InsaComVO;
import com.insa.data.vo.InsaVO;
import com.insa.data.vo.SearchVO;

@Repository("insaMybatis")
public class InsaDAOImpl implements IInsaDAO {

	@Autowired
	SqlSessionTemplate ssTem;
	
	@Override
	public List<InsaVO> showAllInsaInfomation(SearchVO search) {
//		System.out.println("사원정보 전체 조회");
//		System.out.println(search);
		
//		List<InsaVO> rtn = ssTem.selectList("InsaDAO.SQL_SHOW_ALL_INSA_INFOMATION", search);
		
//		System.out.println("dao.rtn : " + rtn);
		
		return ssTem.selectList("InsaDAO.SQL_SHOW_ALL_INSA_INFOMATION", search);
	}

	@Override
	public InsaVO showOneInsaDetail(int sabun) {
		return ssTem.selectOne("InsaDAO.SQL_SHOW_ONE_INSA_DETAIL_SABUN", sabun);
	}
	
	@Override
	public InsaVO showOneInsaDetail(String userId) {
		return ssTem.selectOne("InsaDAO.SQL_SHOW_ONE_INSA_DETAIL_USERID", userId);
	}

	@Override
	public int showInsaCount(String userId) {
		return ssTem.selectOne("InsaDAO.SQL_SHOW_INSA_COUNT", userId);
	}
	
	@Override
	public InsaVO showInsaCount(int sabun) {
		return ssTem.selectOne("InsaDAO.SQL_SHOW_ONE_INSA_DETAIL_SABUN", sabun);
	}

	@Override
	public int insertInsaData(InsaVO insa) {
		return ssTem.insert("InsaDAO.SQL_INSERT_INSA_INFOMATION", insa);
	}

	@Override
	public int updateInsaData(InsaVO insa) {
		return ssTem.update("InsaDAO.SQL_UPDATE_INSA_INFOMATION", insa);
	}

	@Override
	public int deleteInsaData(int sabun) {
		return ssTem.delete("InsaDAO.SQL_DELETE_INSA_INFOMATION", sabun);
	}

	@Override
	public List<InsaComVO> showCom(String gubun) {
		return ssTem.selectList("InsaDAO.SQL_SHOW_COM", gubun);
	}

	@Override
	public int computeMaxPageNumber() {
		return 0;
	}

	@Override
	public int computeMaxPageNumber(SearchVO search) {
		return 0;
	}

	@Override
	public int countNumberOfBoards() {
		return 0;
	}

	@Override
	public int countNumberOfBoards(SearchVO search) {
		int rtn = ssTem.selectOne("InsaDAO.SQL_INSA_COUNT", search);
		if(rtn == 0) {
			rtn = 1;
		}
		return rtn;
	}
	
}
