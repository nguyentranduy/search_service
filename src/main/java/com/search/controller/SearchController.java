package com.search.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.search.model.ProductResponse;
import com.search.service.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class SearchController {
	
	@Autowired
	ProductService productService;

	@GetMapping("/app/search")
	public String doGetApp() {
		return "search";
	}
	
	@PostMapping("/app/search")
	public String doPostSearch(@ModelAttribute("searchText") String searchText,
						       HttpSession session, Model model) {
		List<ProductResponse> productResponse = new ArrayList<>();
		
		if (!searchText.isBlank()) {
			productResponse = productService.find(searchText, session);
		}
		
		model.addAttribute("products", productResponse);
		return "search";
	}
}
