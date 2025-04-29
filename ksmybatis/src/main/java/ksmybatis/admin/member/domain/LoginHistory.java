package ksmybatis.admin.member.domain;

import lombok.Data;

@Data
public class LoginHistory {

	private Integer loginNum;
	private String loginId;
	private String loginIp;
	private String loginDate;

	private Member memberInfo;
}
