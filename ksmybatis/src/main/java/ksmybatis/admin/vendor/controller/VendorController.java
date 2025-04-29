package ksmybatis.admin.vendor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ksmybatis.admin.vendor.service.VendorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin/vendor")
public class VendorController {
	
	private final VendorService vendorService;
	
	@GetMapping("/productListByVendor")
	public String getProductListByVendor(Model model) {
		
		var vendorList = vendorService.getProductsListByVendor();
		
		log.info("vendorList: {}", vendorList);
		
		model.addAttribute("title", "거래처별 상품현황");
		model.addAttribute("vendorList", vendorList);
		
		return "admin/vendor/productListByVendorView";
	}
}
