package ksmybatis.admin.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
	
	// orderitems 삭제
	int removeOrderItemsByProductCode(String productCode);
	
	// 주문 이력 삭제
	int removeOrdersByCustomerId(String customerId);
	
	// 주문 상세 이력 삭제
	int removeOrderItemsByNum(List<String> orderNumList);
	
	// 구매이력 번호 조회
	List<String> getOrdersNumByCustomerId(String customerId);
	
	// 판매자가 등록한 상품을 구매한 이력 삭제
	int removeOrderItemsBySellerId(String sellerId);
}
