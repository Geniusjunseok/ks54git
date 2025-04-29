package ksmybatis.admin.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {
	
	@GetMapping({"/admin", "/admin/"})
	public String adminMainPage(Model model) {
		
		model.addAttribute("title", "관리자페이지");	
		
		return "admin/main";
	}
}
