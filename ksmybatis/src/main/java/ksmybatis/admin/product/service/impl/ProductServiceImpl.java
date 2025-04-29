package ksmybatis.admin.product.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmybatis.admin.order.mapper.OrderMapper;
import ksmybatis.admin.product.domain.Product;
import ksmybatis.admin.product.mapper.ProductMapper;
import ksmybatis.admin.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
	
	private final ProductMapper productMapper;
	private final OrderMapper orderMapper;
	
	@Override
	public boolean removeProduct(String productCode) {
		
		int delCount = 0;
		
		// orderitems 삭제
		delCount += orderMapper.removeOrderItemsByProductCode(productCode);
		// products 삭제
		delCount += productMapper.removeProductByProductCode(productCode);
		
		boolean isDel = delCount > 0 ? true : false;
		
		return isDel;
	}
	
	/**
	 * 상품수정
	 */
	@Override
	public void modifyProduct(Product product) {
		
		productMapper.modifyProduct(product);
	}

	/**
	 * 상품정보조회
	 */
	@Override
	public Product getProductInfoByCode(String productCode) {
		Product productInfo = productMapper.getProductInfoByCode(productCode);
		
		return productInfo;
	}
	
	/**
	 * 상품등록
	 */
	@Override
	public void addProduct(Product product) {
		
		log.info("상품등록 전: {}", product);
		
		productMapper.addProduct(product);
		
		log.info("상품등록 후: {}", product);
	}
	
	/**`	
	 * 상품목록조회
	 */
	@Override
	public List<Product> getProductList() {
		
		List<Product> productList = productMapper.getProductList();
		
		return productList;
	}
	
	
	
}
