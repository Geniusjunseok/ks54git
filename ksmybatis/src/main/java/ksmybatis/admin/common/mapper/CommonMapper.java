package ksmybatis.admin.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmybatis.admin.common.domain.CommonCode;

@Mapper
public interface CommonMapper {
	
	// 그룹코드에 맞는 공통코드 조회
	List<CommonCode> getCommonCodeListByGroupCode(String groupCode);
}
