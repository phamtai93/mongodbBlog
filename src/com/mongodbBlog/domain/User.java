package com.mongodbBlog.domain;

import java.util.Date;

import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class User {

	private String _id;
	@Size(min=6, max=50)
	private String name;
	private String urlName;
	private String role;
	private String email;
	@Size(min=6, max=60)
	private String password;
	@DateTimeFormat(iso = ISO.DATE)
	private Date dob;
	private String avatar;
	private String address;
	private String workplace;
	private String token;
	@DateTimeFormat(iso = ISO.DATE)
	private Date tokenCreated;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date created;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date lastLogin;
	private int enable;
	private int status;
	private String describe;
	

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrlName() {
		return urlName;
	}

	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWorkplace() {
		return workplace;
	}

	public void setWorkplace(String workaddr) {
		workplace = workaddr;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getTokenCreated() {
		return tokenCreated;
	}

	public void setTokenCreated(Date tokenCreated) {
		this.tokenCreated = tokenCreated;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public int getEnable() {
		return enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
	
}
