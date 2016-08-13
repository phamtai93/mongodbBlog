package com.mongodbBlog.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mongodbBlog.domain.User;
import com.mongodbBlog.service.UserService;

public class UserDetailsServiceLogin implements UserDetailsService{
	
	@Autowired
	private UserService userService;

	@Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        User user = userService.getUserByEmail(email);
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        System.out.println("UserDetailsServiceLogin : "+email +" "+user.getRole());
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
        		user.getPassword(), enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked,
                getAuthorities(user.getRole()));
    }
    
   //user.getRole() trả về một string, 
    
	
    public Collection<? extends GrantedAuthority> getAuthorities(String role) {
        List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
        return authList;
    }

    
    public List<String> getRoles(String role) {
        List<String> roles = new ArrayList<String>();
        if ("user_role".equals(role)) {
            roles.add("user_role");
        } else if ("admin_role".equals(role)) {
            roles.add("admin_role");
        }
        return roles;
    }


    public List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}
