package com.hoju.koala.member.model.service;

import java.util.ArrayList;

import com.hoju.koala.admin.model.vo.Supporters;
import com.hoju.koala.board.model.vo.Board;
import com.hoju.koala.board.model.vo.Reply;
import com.hoju.koala.common.model.vo.PageInfo;
import com.hoju.koala.member.model.vo.Attendance;
import com.hoju.koala.member.model.vo.Follow;
import com.hoju.koala.member.model.vo.Member;
import com.hoju.koala.member.model.vo.Profile;

public interface MemberService {

	//로그인
	Member loginMember(Member m);

	//회원조회 활동내역
	Member selectMember(String userId);

	//회원가입
	int insertMember(Member m);

	//소개글 수정
	int updateIntroduce(Member m);

	//팔로우가 되어있는지 확인하는 메소드
	int selectFollow(Follow f);
	
	//팔로우 하기
	int addFollow(Follow f);

	//팔로우 취소하기
	int removeFollow(Follow f);

	//해당 회원 팔로우 수 조회
	int selectFollowCount(int userNo);

	//비밀번호 변경
	int updatePwd(Member loginUser);
	
	//계정 삭제
	int deleteMember(String userId);

	//입력한 이메일에 대한 데이터가 있는지 조회 있다면 회원정보 가져오기
	Member selectEmail(String userEmail);

	//아이디 중복체크
	int idCheck(String inputId);

	//닉네임 중복체크
	int nickCheck(String inputNick);

	//유저가 쓴 게시글 조회
	ArrayList<Board> boardList(PageInfo pi, String userId);

	//유저가 쓴 댓글 조회
	ArrayList<Board> replyList(PageInfo pi,String userId);

	//유저가 추천누른 게시글 조회
	ArrayList<Board> likedList(PageInfo pi, String userId);

	//해당 유저의 팔로잉 조회
	ArrayList<Member> followingList(PageInfo pi, String userId);

	//========================================================= 설희
	//해당 유저의 contributions 조회
	ArrayList<Attendance> selectContributions(int userNo);
	
	//(관리자,서포터즈) libList 조회
	ArrayList<Board> libList(PageInfo pi, String userId);
	
	//로그인 시 출석 등록
	void attendance(int userNo);

	//닉네임 변경
	int updateNick(Member m);

	//프로필 등록
	int insertProfile(Profile p);

	//프로필 조회
	Profile selectProfile(int userNo);
	
	//프로필 삭제
	int deleteProfile(Profile delProfile);

	//닉네임 존재여부 확인
	int selectNick(String inputNick);

	//메신저 닉네임으로 유저 검색
	ArrayList<Member> searchUser(String searchUser);

	int selectblCount(String userId);

	int selectrlCount(String userId);

	int selectllCount(String userId);

	int selectflCount(String userId);

	int selectlibCount(String userId);

	//로그인 유저 서포터즈 판별
	int selectSup(int userNo);

	//해당 유저 서포터즈 인지 확인
	Supporters selectSupport(String userId);

	

	



	

}
