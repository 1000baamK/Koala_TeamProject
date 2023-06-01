package com.hoju.koala.admin.model.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.hoju.koala.admin.model.vo.AllCount;

@Repository
public class AdminDao {
	
	// ��ü ����Ʈ ī��Ʈ �� ��ȸ
	public AllCount selectAllCount(SqlSession sqlSession) {
		return sqlSession.selectOne("adminMapper.allCount");
	}

	

	
}
