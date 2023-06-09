package com.hoju.koala.member.model.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoju.koala.admin.model.vo.Supporters;
import com.hoju.koala.board.model.vo.Board;
import com.hoju.koala.board.model.vo.Reply;
import com.hoju.koala.common.model.vo.PageInfo;
import com.hoju.koala.member.model.dao.MemberDao;
import com.hoju.koala.member.model.vo.Attendance;
import com.hoju.koala.member.model.vo.Follow;
import com.hoju.koala.member.model.vo.Member;
import com.hoju.koala.member.model.vo.Profile;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
	//로그인
	@Override
	public Member loginMember(Member m) {
		
		Member loginUser = memberDao.loginMember(sqlSession, m);
		
		return loginUser;
	}


	//회원 정보 조회
	@Override
	public Member selectMember(String userId) {

		Member m = memberDao.selectMember(sqlSession, userId);
		
		return m;
	}


	//회원가입
	@Override
	public int insertMember(Member m) {

		int result = memberDao.insertMember(sqlSession, m);
		
		return result;
	}


	//소개글 수정
	@Override
	public int updateIntroduce(Member m) {
		
		int result = memberDao.updateIntroduce(sqlSession, m);
		
		return result;
	}

	//팔로우가 되어있는지 확인하는 메소드
	@Override
	public int selectFollow(Follow f) {
		
		int cnt = memberDao.selectFollow(sqlSession, f);
		
		return cnt;
	}
	

	//팔로우 하기
	@Override
	public int addFollow(Follow f) {
		
		int result = memberDao.addFollow(sqlSession, f);
		
		return result;
	}


	//팔로우 취소
	@Override
	public int removeFollow(Follow f) {

		int result = memberDao.removeFollow(sqlSession, f);
		
		return result;
	}


	//팔로우 수 조회
	@Override
	public int selectFollowCount(int userNo) {
		
		int cnt = memberDao.selectFollowCount(sqlSession, userNo);
		
		return cnt;
	}


	//비밀번호 변경
	@Override
	public int updatePwd(Member loginUser) {
		
		int result = memberDao.updatePwd(sqlSession, loginUser);
		
		return result;
	}
	
	//계정삭제
	@Override
	public int deleteMember(String userId) {
		
		int result = memberDao.deleteMember(sqlSession, userId);
		
		return result;
	}


	//입력한 이메일에 대한 데이터가 있는지 조회 있다면 아이디만 가져오기
	@Override
	public Member selectEmail(String userEmail) {

		Member m = memberDao.selectEmail(sqlSession, userEmail);
		
		return m;
	}


	//아이디 중복체크
	@Override
	public int idCheck(String inputId) {
		
		int result = memberDao.idCheck(sqlSession, inputId);
		
		return result;
	}


	//닉네임 중복체크
	@Override
	public int nickCheck(String inputNick) {
		
		int result = memberDao.nickCheck(sqlSession, inputNick);
		
		return result;
	}


	//유저가 쓴 게시글 조회
	@Override
	public ArrayList<Board> boardList(PageInfo pi, String userId) {
		
		ArrayList<Board> blist = memberDao.boardList(sqlSession, pi, userId);
		
		return blist;
	}


	//유저가 쓴 댓글쓴 게시글 조회
	@Override
	public ArrayList<Board> replyList(PageInfo pi, String userId) {
		
		ArrayList<Board> rlist = memberDao.replyList(sqlSession, pi, userId);
		
		return rlist;
	}


	//유저가 추천누른 게시글 조회
	@Override
	public ArrayList<Board> likedList(PageInfo pi, String userId) {
		
		ArrayList<Board> lList = memberDao.likedList(sqlSession, pi, userId);
		
		return lList;
	}


	//해당 유저의 팔로잉 조회
	@Override
	public ArrayList<Member> followingList(PageInfo pi, String userId) {
		
		ArrayList<Member> fList = memberDao.followingList(sqlSession, pi, userId);
		
		return fList;
	}
	
	
	//========================================================= 설희
	//해당 유저의 contributions 조회
	@Override
	public ArrayList<Attendance> selectContributions(int userNo) {
		
		return memberDao.selectContributions(sqlSession, userNo);
	}
	
	//(관리자,서포터즈) libList 조회
	@Override
	public ArrayList<Board> libList(PageInfo pi, String userId) {
		
		ArrayList<Board> libList = memberDao.libList(sqlSession, pi, userId);
		
		return libList;
	}
	
	
	//로그인 시 출석 등록
	@Override
	public void attendance(int userNo) {
		
		memberDao.attendance(sqlSession, userNo);
	}


	//닉네임 변경
	@Override
	public int updateNick(Member m) {

		int result = memberDao.updateNick(sqlSession, m);
		
		return result;
	}


	//프로필 등록
	@Override
	public int insertProfile(Profile p) {
		
		int result = memberDao.insertProfile(sqlSession, p);
		
		return result;
	}


	//프로필 조회
	@Override
	public Profile selectProfile(int userNo) {

		Profile delProfile = memberDao.selectProfile(sqlSession, userNo);
		
		return delProfile;
	}
	
	
	//프로필 삭제
	@Override
	public int deleteProfile(Profile delProfile) {

		int result = memberDao.deleteProfile(sqlSession, delProfile);
		
		return result;
	}


	//닉네임 조회
	@Override
	public int selectNick(String inputNick) {

		int cnt = memberDao.selectNick(sqlSession, inputNick);
		
		return cnt;
	}

	//메신저 닉네임으로 유저 검색
	@Override
	public ArrayList<Member> searchUser(String searchUser) {
		
		ArrayList<Member> mlist = memberDao.searchUser(sqlSession, searchUser);
		
		return mlist;
	}


	//검색된 보드리스트 카운트
	@Override
	public int selectblCount(String userId) {
		
		int result = memberDao.selectblCount(sqlSession, userId);
		
		return result;
	}


	@Override
	public int selectrlCount(String userId) {
		
		int result = memberDao.selectrlCount(sqlSession, userId);
		
		return result;
	}


	@Override
	public int selectllCount(String userId) {
		
		int result = memberDao.selectllCount(sqlSession, userId);
		
		return result;
	}


	@Override
	public int selectflCount(String userId) {
		
		int result = memberDao.selectflCount(sqlSession, userId);
		
		return result;
	}


	@Override
	public int selectlibCount(String userId) {

		int result = memberDao.selectlibCount(sqlSession, userId);
		
		return result;
	}

	
	//로그인 유저 서포터즈 판별
	@Override
	public int selectSup(int userNo) {

		int result = memberDao.selectSup(sqlSession, userNo);
		
		return result;
	}


	//해당 유저 서포터즈인지 확인
	@Override
	public Supporters selectSupport(String userId) {

		Supporters sup = memberDao.selectSupport(sqlSession, userId);
		
		return sup;
	}


	








	
	


	
	

}
