package ksmybatis.admin.member.service;

import java.util.List;
import java.util.Map;

import ksmybatis.admin.member.domain.LoginHistory;
import ksmybatis.admin.member.domain.Member;
import ksmybatis.admin.util.PageInfo;
import ksmybatis.admin.util.Pageable;

public interface MemberService {
	// 회원 로그인 이력 조회
	PageInfo<LoginHistory> getLoginHistoryList(Pageable pageable);
	
	// 회원 검색 조회
	List<Member> getSearchMember(String searchKey, String searchValue);
	
	// 회원탈퇴
	void removeMember(String memberGrade, String memberId);
	
	// 회원정보
	Map<String, Object> matchMember(String memberId, String memberPw);
	
	// 회원 정보 수정
	void modifyMember(Member member);
	
	// 회원 정보 조회
	Member getMemberInfoById(String memberId);
	
	// 회원등록
	void addMember(Member member);
	
	// 회원아이디 중복 체크
	boolean isIdCheck(String memberId);
	
	// 회원 목록 조회
	List<Member> getMemberList();
}
