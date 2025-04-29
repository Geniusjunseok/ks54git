package ksmybatis.admin.member.domain;

import lombok.Data;

/**
 *  @Data = @Getter + @Setter + @ToString + @EqualsAndHashCode + @RequiredArgsConstructor(final 필드, @NotNull로 선언한 필드 기준으로 생성자 메소드 생성)
 */
@Data
public class Member {
	
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberGrade;
	private String memberAddress;
	private String memberDetailAddress;
	private int memberZip;
	private String memberTelNo;
	private String memberEmail;
	private String memberRegDate;
	
	
}
