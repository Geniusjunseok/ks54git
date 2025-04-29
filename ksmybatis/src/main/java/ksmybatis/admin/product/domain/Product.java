package ksmybatis.admin.product.domain;

import lombok.Data;

@Data
public class Product {
	
	private String productCode;
	private String vendorCode;
	private String productName;
	private Integer productUnitPrice;
	private String productRegDate;

}
