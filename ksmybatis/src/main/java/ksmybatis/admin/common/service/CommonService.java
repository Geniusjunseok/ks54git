package ksmybatis.admin.common.service;

import java.util.List;

import ksmybatis.admin.common.domain.CommonCode;

public interface CommonService {

	// 그룹코드에 맞는 공통코드 조회
	List<CommonCode> getCommonCodeListByGroupCode(String groupCode);
}
