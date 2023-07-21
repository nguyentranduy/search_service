package com.search.filter;

import java.io.IOException;
import java.util.Objects;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class SessionFilter implements Filter {
	
	private static final String IGNORED_URL = "/app/login";
	private static final String SEARCH_APP_URL = "/app/search";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		
		if (SEARCH_APP_URL.contains(httpRequest.getServletPath())
				&& Objects.isNull(session.getAttribute("CURRENT_USER"))) {
			httpResponse.sendRedirect(IGNORED_URL);
		} else if (IGNORED_URL.contains(httpRequest.getServletPath())
				&& Objects.nonNull(session.getAttribute("CURRENT_USER"))) {
			httpResponse.sendRedirect(SEARCH_APP_URL);
		} else {	
			chain.doFilter(request, response);
		}
	}
}
