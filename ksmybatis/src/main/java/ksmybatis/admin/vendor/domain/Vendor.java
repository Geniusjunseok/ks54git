package ksmybatis.admin.vendor.domain;

import java.util.List;

import ksmybatis.admin.member.domain.Member;
import ksmybatis.admin.product.domain.Product;
import lombok.Data;

@Data
public class Vendor {

	private String vendorCode;
	private String vendorMemberId;
	private String vendorBrno;
	private String vendorName;
	private String vendorAddress;
	private String vendorDetailAddress;
	private Integer vendorZip;
	private String vendorTelNo;

	// 거래처 담당자의 정보 1:1
	private Member sellerInfo;
	
	// 등록한 상품의 정보
	private List<Product> productsList;
}
