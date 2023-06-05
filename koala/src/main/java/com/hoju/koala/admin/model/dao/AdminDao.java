package com.hoju.koala.admin.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.hoju.koala.admin.model.vo.AllCount;
import com.hoju.koala.admin.model.vo.BlockIp;
import com.hoju.koala.admin.model.vo.CreateSetting;
import com.hoju.koala.admin.model.vo.Supporters;
import com.hoju.koala.board.model.vo.ErrorBoard;
import com.hoju.koala.common.model.vo.PageInfo;
import com.hoju.koala.member.model.vo.Member;


// rawtypes : 제네릭 타입이 불완전하다
@SuppressWarnings({ "unchecked", "rawtypes" })
@Repository
public class AdminDao {
	
	public AllCount selectAllCount(SqlSession sqlSession) {
		return sqlSession.selectOne("adminMapper.allCount");
	}

	public ArrayList<Supporters> selectSupporters(SqlSession sqlSession, PageInfo pi) {
		return (ArrayList) sqlSession.selectList("adminMapper.selectSupporters",null, pi.rowBounds());
	}
	
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

	/* 페이징 처리 보류 
	 * public int boardListCount(SqlSession sqlSession, String board) { return
	 * sqlSession.selectOne("adminMapper.boardCount", board); }
	 */
}
