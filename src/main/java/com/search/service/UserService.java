package com.search.service;

import com.search.model.User;
import com.search.model.UserResponse;

import jakarta.servlet.http.HttpSession;

public interface UserService {

	UserResponse doLogin(User userRequest, HttpSession session) throws Exception;
}
