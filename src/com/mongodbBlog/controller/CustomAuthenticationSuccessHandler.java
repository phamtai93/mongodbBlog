package com.mongodbBlog.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.mongodbBlog.service.UserService;
import com.mongodbBlog.domain.User;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Autowired
	private UserService userService;
	
	@Override
	public void onAuthenticationSuccess(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse,
			Authentication authentication) throws IOException, ServletException {
		handle(httpServletRequest, httpServletResponse, authentication);
		clearAuthenticationAttributes(httpServletRequest);
		newHttpSession(httpServletRequest, httpServletResponse);
	}
	
	protected String determineTargetUrl(Authentication authentication) {
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			if(grantedAuthority.getAuthority().equals("role_user")){
				return "/home.html";
			}
			else{
				if(grantedAuthority.getAuthority().equals("role_admin")){
					return "/console.html";
				}
			}
		}
		return "/home.html";
		
	}
	
	protected void handle(HttpServletRequest request, HttpServletResponse response, 
						Authentication authentication) throws IOException {
		String targetUrl = determineTargetUrl(authentication);
		if (response.isCommitted()) {
			return;
		}
		
//		response.setStatus(HttpServletResponse.SC_OK);
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}
	
	
	protected void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
	
	protected void newHttpSession(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession(false);
		org.springframework.security.core.userdetails.User userDetails
							= (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext()
											.getAuthentication().getPrincipal();

		String name = userDetails.getUsername(); // thực ra name ở đây chính là email 
		System.out.println("name retrive: " + name);
		User authUser = userService.getUserByEmail(name);
		
		System.out.println("CustomAuthenticationSuccessHandler : " + authUser.getName() + " " + authUser.getRole());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		session.setAttribute("id", authUser.get_id());
		session.setAttribute("userName", authUser.getName());
		session.setAttribute("role", (authUser.getRole()));
		session.setAttribute("lastLogin", sdf.format(authUser.getLastLogin()));

		userService.saveDateTimeLogin(authUser.getName()); // lưu lại lần cuối cùng login

		if ((authUser.getAvatar()).equals("0")) {
			session.setAttribute("avatar", "resources/front/img/Default-avatar.jpg");

		} else {
			session.setAttribute("avatar", authUser.getAvatar());

		}
	}
}
