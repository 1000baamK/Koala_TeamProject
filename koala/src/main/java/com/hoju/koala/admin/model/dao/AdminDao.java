package com.hoju.koala.admin.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.hoju.koala.admin.model.vo.AllCount;
import com.hoju.koala.admin.model.vo.CreateSetting;
import com.hoju.koala.admin.model.vo.Supporters;
import com.hoju.koala.board.model.vo.ErrorBoard;


// rawtypes : ���׸�Ÿ���� ��Ư���� ��
@SuppressWarnings({ "unchecked", "rawtypes" })
@Repository
public class AdminDao {
	
	// ��ü ����Ʈ ī��Ʈ �� ��ȸ
	public AllCount selectAllCount(SqlSession sqlSession) {
		return sqlSession.selectOne("adminMapper.allCount");
	}

	// �������� ���� ��ȸ
	public ArrayList<Supporters> selectSupporters(SqlSession sqlSession) {
		return (ArrayList) sqlSession.selectList("adminMapper.selectSupporters");
	}
	// ������� ���̺귯 �� ���� ��ȸ
	public ArrayList<CreateSetting> selectCreateSetting(SqlSession sqlSession) {
		return (ArrayList)sqlSession.selectList("adminMapper.selectWaitingLibrary");
	}

	public ArrayList<ErrorBoard> selectErrorBoard(SqlSession sqlSession) {
		return (ArrayList)sqlSession.selectList("adminMapper.selectErrorBoard");
	}
}
