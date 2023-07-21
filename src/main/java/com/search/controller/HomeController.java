package com.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.search.model.User;
import com.search.model.UserResponse;
import com.search.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	UserService userService;

	@GetMapping("/app/login")
	public String login(Model model) {
		model.addAttribute("userRequest", new User());
		return "login";
	}
	
	@GetMapping("/app/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/app/login";
	}
	
	@PostMapping("/app/login")
	public String doPostLogin(@ModelAttribute("userRequest") User userRequest, HttpSession session) {
		try {
			UserResponse userResponse = userService.doLogin(userRequest, session);
			if (userResponse != null) {
				session.setAttribute("CURRENT_USER", userResponse);
				return "redirect:/app/search";
			}
			return "redirect:/app/login";
		} catch (Exception e) {
			return "redirect:/app/login";
		}
	}
}
