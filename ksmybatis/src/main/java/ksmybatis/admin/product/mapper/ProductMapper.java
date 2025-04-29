package ksmybatis.admin.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmybatis.admin.product.domain.Product;

@Mapper
public interface ProductMapper {
	// products 삭제
	int removeProductByProductCode(String productCode);
	
	// 판매자가 등록한 상품 삭제
	int removeProductsBySellerId(String sellerId);
	
	// 상품 수정
	int modifyProduct(Product product);
	
	// 상품정보조회
	Product getProductInfoByCode(String productCode);
	
	// 상품 등록
	int addProduct(Product product);
	
	// 상품목록 조회
	List<Product> getProductList();
	
}
