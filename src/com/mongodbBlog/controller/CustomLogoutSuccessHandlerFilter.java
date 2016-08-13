package com.mongodbBlog.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

public class CustomLogoutSuccessHandlerFilter extends SimpleUrlLogoutSuccessHandler {
	private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
       
    	//System.out.println("i'm logoutsuccessHandler");         
    	/* no thing special to do*/
        StringBuilder redirect = new StringBuilder("/home.html"); // "redirect" is not important, it may contain any values 
        redirectStrategy.sendRedirect(request, response, redirect.toString());
    }

}
