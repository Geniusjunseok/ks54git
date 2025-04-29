package ksmybatis.admin.member.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmybatis.admin.member.domain.LoginHistory;
import ksmybatis.admin.member.domain.Member;
import ksmybatis.admin.member.mapper.MemberMapper;
import ksmybatis.admin.member.service.MemberService;
import ksmybatis.admin.order.mapper.OrderMapper;
import ksmybatis.admin.product.mapper.ProductMapper;
import ksmybatis.admin.util.PageInfo;
import ksmybatis.admin.util.Pageable;
import ksmybatis.admin.vendor.mapper.VendorMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService{
	
	// DI 의존성주입
	private final MemberMapper memberMapper;
	private final OrderMapper orderMapper;
	private final ProductMapper productMapper;
	private final VendorMapper vendorMapper;
	
	
	@Override
	public PageInfo<LoginHistory> getLoginHistoryList(Pageable pageable) {
		// 마지막 페이지를 구하기 위해 전체 행의 개수 조회
		int contentRowCount = memberMapper.getLoginHistoryCount();
		
		List<LoginHistory> loginHistoryList = memberMapper.getLoginHistoryList(pageable);

		log.info("contentRowCount: {}", contentRowCount);
		log.info("loginHistoryList: {}", loginHistoryList);
		
		return new PageInfo<>(loginHistoryList, pageable, contentRowCount);
	}
	
	@Override
	public List<Member> getSearchMember(String searchKey, String searchValue) {

		switch (searchKey) {
		case "memberId" -> searchKey = "m.mbr_id";
		case "memberName" -> searchKey = "m.mbr_name";
		case "memberEmail" -> searchKey = "m.mbr_email";
		
		}
		List<Member> memberList = memberMapper.getSearchMember(searchKey, searchValue);
		return memberList;
	}
	
	@Override
	public void removeMember(String memberGrade, String memberId) {

		switch (memberGrade) {
			case "mbr_grd_2" -> {
				// 1. 주문상세
				orderMapper.removeOrderItemsBySellerId(memberId);
				// 2. 상품
				productMapper.removeProductsBySellerId(memberId);
				// 3. 거래처
				vendorMapper.removeVendorBySellerId(memberId);
			}
			case "mbr_grd_3" -> {
				List<String> orderNumList =  orderMapper.getOrdersNumByCustomerId(memberId);
				if(orderNumList != null && orderNumList.size() > 0) {
					// 1. 주문상세
					orderMapper.removeOrderItemsByNum(orderNumList);
					// 2. 주문
					orderMapper.removeOrdersByCustomerId(memberId);
				}
			}
		}
		// 로그인 이력
		memberMapper.removeMemberLoginHistoryById(memberId);
		// 회원
		memberMapper.removeMemberById(memberId);
	}
	
	@Override
	public Map<String, Object> matchMember(String memberId, String memberPw) {

		boolean isMatched = false;
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Member memberInfo = memberMapper.getMemberInfoById(memberId);
		
		if(memberInfo != null) {
			String checkPw = memberInfo.getMemberPw();
			if(checkPw.equals(memberPw)) {
				isMatched = true;
				resultMap.put("memberInfo", memberInfo);
			}
		}
		
		resultMap.put("isMatched", isMatched);
		return resultMap;
	}
	
	/**
	 * 회원 정보 수정
	 */
	@Override
	public void modifyMember(Member member) {
		
		memberMapper.modifyMember(member);
	}
	
	/**
	 * 회원 정보 조회
	 */
	@Override
	public Member getMemberInfoById(String memberId) {
		
		Member memberInfo = memberMapper.getMemberInfoById(memberId);
		
		return memberInfo;
	}
	
	/**
	 * 회원등록
	 */
	@Override
	public void addMember(Member member) {
		
		memberMapper.addMember(member);
	}
	
	/**
	 * 회원아이디 중복체크
	 */
	@Override
	public boolean isIdCheck(String memberId) {
		
		return memberMapper.isIdCheck(memberId);
	}
	
	/**
	 * 회원목록조회
	 */
	
	@Override
	public List<Member> getMemberList() {
		List<Member> memberList = memberMapper.getMemberList();
		return memberList;
	}
	
}

