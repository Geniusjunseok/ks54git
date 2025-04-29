package ksmybatis.admin.member.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ksmybatis.admin.common.domain.CommonCode;
import ksmybatis.admin.common.service.CommonService;
import ksmybatis.admin.member.domain.LoginHistory;
import ksmybatis.admin.member.domain.Member;
import ksmybatis.admin.member.service.MemberService;
import ksmybatis.admin.util.PageInfo;
import ksmybatis.admin.util.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/member")
@Slf4j
public class MemberController {
	
	// DI 의존성주입
	private final MemberService memberService;
	private final CommonService commonService;

	
	@GetMapping("/loginHistoryList")
	public String getLoginHistoryList(Pageable pageable, Model model) {

		PageInfo<LoginHistory> loginHistory = memberService.getLoginHistoryList(pageable);

		var loginHistoryList = loginHistory.getContents();
		int currentPage = loginHistory.getCurrentPage();
		int lastPage = loginHistory.getLastPage();
		int startPageNum = loginHistory.getStartPageNum();
		int endPageNum = loginHistory.getEndPageNum();
		int rowPerPage = pageable.getRowPerPage();
		
		model.addAttribute("title", "로그인 이력현황");
		model.addAttribute("loginHistoryList", loginHistoryList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("rowPerPage", rowPerPage);
		
		
		return "admin/member/loginHistoryListView";
	}
	
	
	@GetMapping("/searchMember")
	public String getSearchMember(@RequestParam(name="searchKey", required = false, defaultValue="memberId") String searchKey
								, @RequestParam(name="searchValue", required = false) String searchValue
								, Model model) {
		
		log.info("searchKey: {}, searchValue: {}", searchKey, searchValue);
		
		List<Member> memberList = memberService.getSearchMember(searchKey, searchValue);
		model.addAttribute("title", "회원목록");
		model.addAttribute("memberList", memberList);
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("searchValue", searchValue);
		
		return "admin/member/memberListView";
	}
	
	
	@PostMapping("/removeMember")
	@ResponseBody
	public boolean removeMember(@RequestParam(name="memberId") String memberId,
								@RequestParam(value="memberPw") String memberPw) {
		
		log.info("탈퇴 회원 아이디: {}, 탈퇴 회원 비밀번호: {}", memberId, memberPw);
		
		boolean isDelete = true;
		
		Map<String, Object> resultMap =  memberService.matchMember(memberId, memberPw);
		
		isDelete = (boolean) resultMap.get("isMatched");
		
		// 회원탈퇴 처리
		if(isDelete) {
			Member memberInfo = (Member) resultMap.get("memberInfo");
			String membergrade = memberInfo.getMemberGrade();
			memberService.removeMember(membergrade, memberId);
		}
		
		return isDelete;
	}
	
	/**
	 * 
	 * @param member
	 * @param RedirectAttributes : 리디렉션(서버에서 클라이언트한테 강제 URL 요청) 요청할 때 쿼리파라미터를 안전하게 추가하는 객체
	 * @return
	 */
	@PostMapping("/modifyMember")
	public String modifyMember(Member member, RedirectAttributes reAttr) {
		
		log.info("회원수정: {}", member);
		
		memberService.modifyMember(member);
		
		reAttr.addAttribute("memberId", member.getMemberId());
		
		return "redirect:/admin/member/modifyMember";
	}
	
	@GetMapping("/modifyMember")
	public String modifyMember(@RequestParam(name="memberId") String memberId,
								Model model) {
		
		log.info("회원수정 아이디: {}", memberId);
		
		Member memberInfo = memberService.getMemberInfoById(memberId);
		List<CommonCode> gradeList = commonService.getCommonCodeListByGroupCode("comm_group_1");
		
		String memberTelNo = memberInfo.getMemberTelNo();
		String[] telNoArray = memberTelNo.split("-");
		
		model.addAttribute("title", "회원 수정");
		model.addAttribute("memberInfo", memberInfo);
		model.addAttribute("gradeList", gradeList);
		model.addAttribute("memberTelNo1", telNoArray[0]);
		model.addAttribute("memberTelNo2", telNoArray[1]);
		model.addAttribute("memberTelNo3", telNoArray[2]);
		
		return "admin/member/modifyMemberView";
	}
	
	/**
	 * 쿼리스트링(요청 데이터)를 바인딩하는 방법)
	 * 1. 쿼리파라미터 name과 매개변수의 이름이 동일하게 선언
	 * 2. @RequestParam(name, value : 쿼리파라미터 key | required : 쿼리파라미터 필수 여부 defaultValue : 값이 없을 시 기본값), 
	 * 3. 커맨드 객체
	 *
	 * @ResponseBody (http 응답 데이터) : view 경로가 아닌 데이터를 반환 
	 * @param String memberId : 커맨드 객체처럼 쿼리파라미터 name과 매개변수의 이름이 동일하면 값을 자동 바인딩
	 * @return @RestController 안 method의 return => 응답데이터
	 */
	@PostMapping("/idCheck")
	@ResponseBody
	public boolean idCheck(String memberId, @RequestParam(name = "member", required = false, defaultValue = "id") String userId) {
		
		boolean isDuplicate = false;
		
		log.info("체크아이디: {}", memberId);
		log.info("체크아이디: {}", userId);
		
		isDuplicate = memberService.isIdCheck(memberId);		
		return isDuplicate;
	}
	
	@GetMapping("/test")
	public Member memberInfo() {
		Member memberInfo = new Member();
		memberInfo.setMemberId("ksmartid1");
		memberInfo.setMemberPw("ksmartpw1");
		memberInfo.setMemberGrade("mbr_grd_1");
		memberInfo.setMemberName("홍1");
		memberInfo.setMemberAddress("주소");
		memberInfo.setMemberDetailAddress("상세주소");
		memberInfo.setMemberZip(12345);
		
		return memberInfo;
	}
	
//	@GetMapping("/testList")
//	public List<Member> testList() {
//		
//		
//		return memberMapper.getMemberList();
//	}
	
	/**
	 * 
	 * @param Member memberInfo : 커맨드 객체 (사용자가 입력한 데이터를 DTO로 자동 바인딩)
	 * @return String redirect : (서버측에서 클라이언트 강제 url 요청)
	 */
	@PostMapping("/addMember")
	public String addMember(Member memberInfo) {
		
		log.info("회원등록: {}", memberInfo);
		
		memberService.addMember(memberInfo);
		
		return "redirect:/admin/member/memberList";
	}
	
	@GetMapping("/addMember")
	public String addMember(Model model) {
		
		List<CommonCode> gradeList = commonService.getCommonCodeListByGroupCode("comm_group_1");
		
		log.info("회원등급코드조회: {}", gradeList);
		
		model.addAttribute("title", "회원등록");
		model.addAttribute("gradeList", gradeList);
		
		return "admin/member/addMemberView";
	}
	
	@GetMapping("/memberList")
	public String getMemberList(Model model) {
		
		List<Member> memberList = memberService.getMemberList();
		log.info("memberList: {}", memberList);
		
		model.addAttribute("title", "회원목록");
		model.addAttribute("memberList", memberList);
		
		return "admin/member/memberListView";
	}
}
