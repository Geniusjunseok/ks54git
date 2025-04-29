package ksmybatis.admin.vendor.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmybatis.admin.vendor.domain.Vendor;
import ksmybatis.admin.vendor.mapper.VendorMapper;
import ksmybatis.admin.vendor.service.VendorService;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {

	private final VendorMapper vendorMapper;
	
@Override
	public List<Vendor> getProductsListByVendor() {
		
		var vendorList = vendorMapper.getProductsListByVendor();
		return vendorList;
	}
	
	@Override
	public List<Vendor> getVendorList() {
		
		return vendorMapper.getVendorList();
	}

}
