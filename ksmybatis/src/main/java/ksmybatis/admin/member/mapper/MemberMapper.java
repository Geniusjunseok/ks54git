package ksmybatis.admin.member.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmybatis.admin.member.domain.LoginHistory;
import ksmybatis.admin.member.domain.Member;
import ksmybatis.admin.util.Pageable;

/**
 * @Repository == @Mapper (데이터베이스 접근 객체(DAO) 생성) - Database Access Object
 */
@Mapper
public interface MemberMapper {

	// 회원 로그인 이력 총 row 개수 조회
	int getLoginHistoryCount();
	
	// 회원 로그인 이력 조회
	List<LoginHistory> getLoginHistoryList(Pageable pageable);
	
	// 회원 검색 조회
	List<Member> getSearchMember(String searchKey, String searchValue);
	
	// 회원 탈퇴
	int removeMemberById(String memberId);
	
	// 회원 로그인 이력 삭제
	int removeMemberLoginHistoryById(String memberId);
	
	// 회원수정
	int modifyMember(Member member);
	
	// 회원조회
	Member getMemberInfoById(String memberId);
	
	// 회원등록
	int addMember(Member member);
	
	// 회원아이디 중복체크
	boolean isIdCheck(String memberId);

	// 회원목록조회
	List<Member> getMemberList();
}
