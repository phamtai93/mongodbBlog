package com.mongodbBlog.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodbBlog.domain.Topic;
import com.mongodbBlog.domain.User;
import com.mongodbBlog.service.TopicService;
import com.mongodbBlog.service.UserService;

@Controller
@RequestMapping("authUser")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	TopicService topicService;
	
	@ModelAttribute("topic")
	public List<Topic> getAllTopic(){
		try{
			return topicService.getAllTopic();
		}
		catch(Exception e){
			return null;
		}
	}
	
	@RequestMapping("user-info.html")
	public ModelAndView getUserInfo(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes){
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = request.getSession();
		org.springframework.security.core.userdetails.User userDetails
							= (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext()
											.getAuthentication().getPrincipal();
		try{
			String name = userDetails.getUsername(); // thực ra name ở đây chính là email 
			User authUser = userService.getUserByEmail(name);
			//mav.addObject("user", authUser);
			//....
			int status = authUser.getStatus();
			if(status==3){ //có thể sẽ không xảy ra nếu lúc login có lọc user theo cách: nếu status==3 thì không cho login, như vậy user đã login chắc chắn sẽ có status!=3
				redirectAttributes.addFlashAttribute("messageFromGetUserInfo", "Thông tin người dùng này không còn nữa, vì tài khoản đã bị xóa..");
				redirectAttributes.addFlashAttribute("changeable", "isnotallowed");
				mav.setViewName("redirect:/user-info.html?error=unavailable");
				return mav;
			}
			else{
				if(status==2) mav.addObject("status", "đang tạm khóa");
					else mav.addObject("status", "hoạt động bình thường");
			}
			
			//....
			int enable = authUser.getEnable();
			if(enable==0){
				mav.addObject("enable", "chưa kích hoạt");
			}
			else{
				mav.addObject("enable", "đã kích hoạt");
			}
			//....
			String avatar = authUser.getAvatar();
			if (avatar.equals("0")){
				mav.addObject("avatar", "resources/front/img/Default-avatar.jpg");
			}
			else{
				mav.addObject("avatar", avatar);
			}
			mav.addObject("messageFromGetUserInfo","show user info"); // cho jsp ở tầng View biết có thực hiện show thông tin user lên html không 
			mav.addObject("changeable", "isallowed"); // cho jsp ở tầng View biết có thực hiện thêm form thay đổi thông tin user không
			mav.addObject("user", authUser);
		}
		catch(Exception e){
	    	SecurityContextHolder.getContext().setAuthentication(null);
	    	if(session!=null) {session.invalidate();}
	    	Cookie userLoginCookie = null;
			Cookie[] cookies = request.getCookies();
			if(cookies!=null){
				for(Cookie ck : cookies){
					if(ck.getName().equals("mongodbBlog.com_cookie")){
						userLoginCookie = ck;
						break;
					}
				}
			}
			if(userLoginCookie!=null ){
				userLoginCookie.setMaxAge(0);
				response.addCookie(userLoginCookie);
			}
			mav.setViewName("redirect:/user-info.html?error=unauthorized");
			redirectAttributes.addFlashAttribute("changeable", "isnotallowed");
			redirectAttributes.addFlashAttribute("messageFromGetUserInfo", "Bạn vừa mới thay đổi email trong thông tin cá nhân? "
					+ "Nếu đúng như vậy, bạn hãy đăng nhập lại, bạn đã bị đăng xuất để đảm bảo an toàn thông tin <br>"
					+ "Nếu không phải, chúng tôi không xác minh được danh tính của bạn, hiện tại bạn đã bị đăng xuất, "
					+ "bạn hãy đăng nhập lại, xin lỗi vì sự phiền phức này..");
			return mav;
		}
		mav.setViewName("user-info");
		return mav;
	}
	
	
	// produces = "application/json; charset=utf-8" --> response ajax mới có thể nhận đầy đủ nguyên vẹn chuỗi trả về mà không lỗi font 
	@RequestMapping(value="save-change-user-info", method=RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String saveChangeUserInfo(@RequestBody String jsonString, HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		ObjectMapper obm = new ObjectMapper();
		HashMap<?,?> result = new HashMap<String,Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String,String> reply = new HashMap<String, String>();
		boolean atLeastOneInputWrong = false;
		boolean emailIsChanged = false;
		try{
			result = obm.readValue(jsonString, HashMap.class); 
			if(!result.containsKey("name")){
				return "{ \"end\" : \"fail\", \"message\" : \"server từ chối yêu cầu của bạn\"}";
			}
			
			if(result.size() <=1){// chỉ chứa name thôi, thì không có trường nào để thay đổi.
				return "{ \"end\" : \"success\", \"message\" : \"không có trường nào thay đổi\"}";
			}
			
			String name = result.get("name").toString();
			String nameFromSession = request.getSession().getAttribute("userName").toString();
			if(!name.equals(nameFromSession)){
				return "{ \"end\" : \"fail\", \"message\" : \"server từ chối yêu cầu của bạn\"}";
			}
			
			for(Map.Entry<?, ?> entry : result.entrySet()){ // tối đa 6 entry: address, workplace, dob, email, describe, name   
				String regexEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
				String key = entry.getKey().toString().trim();
				String value = entry.getValue().toString().trim();
				switch (key){
					case "address": 
						if(value.length()>60){
							reply.put("address", "độ dài dưới 60 kí tự");
							atLeastOneInputWrong = true;
							break;
						}
						else{
							map.put("address", value);
							reply.put("address", "ok");
							break;
						}
						
					case "workplace":
						if(value.length()>60){
							reply.put("workplace", "độ dài dưới 60 kí tự");
							atLeastOneInputWrong = true;
							break;
						}
						else{
							map.put("workplace", value);
							reply.put("workplace", "ok");
							break;
						}
					case "dob":
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Date date = new Date();
						try{
							date = sdf.parse(value); 
							DateTime dob = new DateTime(date,DateTimeZone.UTC); 
							map.put("dob", dob.toDate());
							reply.put("dob", "ok");
						}
						catch(ParseException  ex){
							atLeastOneInputWrong = true;
							System.out.println("error at saveChangeUserInfo " + ex);
						}
						break;
					case "email":
						if(value.length()>254){
							reply.put("email", "email quá dài");
							atLeastOneInputWrong = true;
							break;
						}
						else
							if(!value.matches(regexEmail)){
								reply.put("email", "email không hợp lệ");
								atLeastOneInputWrong = true;
								break;
							}
							else{
								emailIsChanged = true;
								reply.put("email", "ok");
								String token = BCrypt.hashpw("" + name + (new Random().nextInt(9001) + 1000), BCrypt.gensalt());
								map.put("email", value);
								map.put("enable", 0);
								map.put("token", token);
								String [] address ={value};
								// gửi lại đường dẫn kích hoạt account
								userService.sendMailForActivatingAccount(address, name, token);
								break;
							}
						
					case "describe":
						if(value.length()>500){
							reply.put("describe", "giới thiệu dưới 500 ký tự");
							atLeastOneInputWrong = true;
							break;
						}
						else{
							map.put("describe", value);
							reply.put("describe", "ok");
							break;
						}
						
					//case "name": // nếu đã thực hiện đến đây thì luôn tồn tại 'name' không cần phải thêm name vào map
						//map.put("name", value);
						//break;
				}
			}
			
			if(map.size() != 0){ // = 0 nghĩa là không có trường nào hợp lệ -> không thay đổi được trường nào cả
				String dosave = userService.saveChangeUserInfo(name, map);
				if(dosave.equals("fail")){
					return "{ \"end\" : \"fail\", \"message\": \"sorry, đã có lỗi xảy ra, bạn thử lại sau\"}";
				}
				if(emailIsChanged){
					// email đã bị thay đổi --> phải login user
					SecurityContextHolder.getContext().setAuthentication(null);
			    	if(session!=null) {session.invalidate();}
			    	Cookie userLoginCookie = null;
					Cookie[] cookies = request.getCookies();
					if(cookies!=null){
						for(Cookie ck : cookies){
							if(ck.getName().equals("mongodbBlog.com_cookie")){
								userLoginCookie = ck;
								break;
							}
						}
					}
					if(userLoginCookie!=null ){
						userLoginCookie.setMaxAge(0);
						response.addCookie(userLoginCookie);
					}
				}
			}
		}
		catch(Exception e){
			System.out.println("error at saveChangeUserInfo :" + e);
			return "{ \"end\" : \"fail\", \"message\" : \"sorry, đã có lỗi xảy ra, bạn thử lại sau\"}";
		}
		
		if(atLeastOneInputWrong){ // có it nhất một trường ko hợp lệ
			try{
				reply.put("end", "inputwrong");
				return obm.writeValueAsString(reply);
			}
			catch(JsonProcessingException j){
				return "{ \"end\" : \"fail\", \"message\" : \"có ít nhất 1 trường không thay đổi được\"}";
			}
		}
		else{
			return "{ \"end\" : \"success\", \"message\" : \"thay đổi hoàn tất\"}";
		}
		
	}
	
}
