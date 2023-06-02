package com.hoju.koala.admin.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.hoju.koala.admin.model.vo.AllCount;
import com.hoju.koala.admin.model.vo.BlockIp;
import com.hoju.koala.admin.model.vo.CreateSetting;
import com.hoju.koala.admin.model.vo.Supporters;
import com.hoju.koala.board.model.vo.ErrorBoard;
import com.hoju.koala.member.model.vo.Member;


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

	public ArrayList<BlockIp> selectBolckIp(SqlSession sqlSession) {
		return (ArrayList)sqlSession.selectList("adminMapper.selectBolckIp");
	}

	public BlockIp selectBlockIpUser(String ip, SqlSession sqlSession) {
		return sqlSession.selectOne("adminMapper.selectBlockIpUser", ip);
	}

	public int insertBlockIpUser(String ip, SqlSession sqlSession) {
		return sqlSession.insert("adminMapper.insertBlockIpUser", ip);
	}

	public int updateBlockIpUser(String ip, SqlSession sqlSession) {
		return sqlSession.update("adminMapper.updateBlockIpUser", ip);
	}

	public int blockBlockIpUser(String ip, SqlSession sqlSession) {
		return sqlSession.update("adminMapper.blockBlockIpUser", ip);
	}

	public ArrayList<Member> selectMemberList(SqlSession sqlSession) {
		return (ArrayList)sqlSession.selectList("adminMapper.selectMemberList");
	}
}
