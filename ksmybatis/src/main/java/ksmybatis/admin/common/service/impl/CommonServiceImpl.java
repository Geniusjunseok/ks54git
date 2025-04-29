package ksmybatis.admin.common.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmybatis.admin.common.domain.CommonCode;
import ksmybatis.admin.common.mapper.CommonMapper;
import ksmybatis.admin.common.service.CommonService;
import lombok.RequiredArgsConstructor;

/**
 * @Transaction : 트랜잭션(ACID)
 */
@Service
@Transactional
@RequiredArgsConstructor

public class CommonServiceImpl implements CommonService{
	
	private final CommonMapper commonMapper;

	/**
	 * 그룹코드에 맞는 공통코드 조회
	 */
	@Override
	public List<CommonCode> getCommonCodeListByGroupCode(String groupCode) {
		 
		
		return commonMapper.getCommonCodeListByGroupCode(groupCode);
	}
}
