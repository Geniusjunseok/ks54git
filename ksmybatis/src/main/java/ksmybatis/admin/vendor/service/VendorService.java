package ksmybatis.admin.vendor.service;

import java.util.List;

import ksmybatis.admin.vendor.domain.Vendor;

public interface VendorService {

	// 거래처별 상품현황 조회
	List<Vendor> getProductsListByVendor();
	
	// 거래처 목록 조회
	List<Vendor> getVendorList();
}
