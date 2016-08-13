package com.mongodbBlog.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;

public class CustomLogoutHandlerFilter implements LogoutHandler {

	@Override
    public void logout(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication) {

    	/* handle before login */
    	/* .... */
    	
    	/*invalid authentication . this may be  unnecessary*/
    	SecurityContextHolder.getContext().setAuthentication(null);
    	
    	/*invalid session*/
    	HttpSession session = request.getSession();
    	if(session!=null) {session.invalidate();}
    	
    	System.out.println("\nCustomLogoutHandlerFilter --------------");
    	/* Delete cookie */
    	Cookie userLoginCookie = null;
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for(Cookie ck : cookies){
				if(ck.getName().equals("mongodbBlog.com_cookie")){
					userLoginCookie = ck;
					System.out.println("getComment() : " + ck.getComment() + "getDomain() : " + ck.getDomain() + " getMaxAge() : " + ck.getMaxAge() + 
							" getName() : " + ck.getName() + " getPath() : " + ck.getPath() + " getSecure() : "+ ck.getSecure() + " getValue() : "+ ck.getValue() + 
							" getVersion() : "+ ck.getVersion() + " isHttpOnly() : " + ck.isHttpOnly());
					break;
				}
			}
		}
		if(userLoginCookie!=null ){
			userLoginCookie.setMaxAge(0);
			response.addCookie(userLoginCookie);
		}
		
		/*
		try {
			redirectStrategy.sendRedirect(request, response, "");
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
    }
    

}
