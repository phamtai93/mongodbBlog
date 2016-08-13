package com.mongodbBlog.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mongodbBlog.domain.Article;
import com.mongodbBlog.domain.Topic;
import com.mongodbBlog.domain.User;
import com.mongodbBlog.service.ArticleService;
import com.mongodbBlog.service.CommentService;
import com.mongodbBlog.service.TopicService;
import com.mongodbBlog.service.UserService;

@Controller
public class CommonController {
	
	@Autowired
	UserService userService;
	@Autowired
	TopicService topicService;
	@Autowired
	ArticleService articleService;
	@Autowired
	CommentService commentService;
	
	@RequestMapping("/home.html")
	public ModelAndView goToHonePage(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		System.out.println("\nhome---------------");
		mav.setViewName("home");
		
//		Cookie userLoginCookie = null;
//		Cookie[] cookies = request.getCookies();
//		if(cookies!=null){
//			for(Cookie ck : cookies){
//				if(ck.getName().equals("mongodbBlog.com_cookie")){
//					userLoginCookie = ck;
//					System.out.println("info cookie home: "+ userLoginCookie.toString()); 
//					System.out.println("getComment() : " + ck.getComment() + "getDomain() : " + ck.getDomain() + " getMaxAge() : " + ck.getMaxAge() + 
//							" getName() : " + ck.getName() + " getPath() : " + ck.getPath() + " getSecure() : "+ ck.getSecure() + " getValue() : "+ ck.getValue() + 
//							" getVersion() : "+ ck.getVersion() + " isHttpOnly() : " + ck.isHttpOnly());
//					break;
//				}
//			}
//		}
		
		int pageNum=1;
		int size=10;

		if(request.getParameterMap().containsKey("p")){
			pageNum = Integer.parseInt(request.getParameter("p"));
		}
		if(request.getParameterMap().containsKey("s")){
			size = Integer.parseInt(request.getParameter("s"));
		}
		Page<Article> page = articleService.findAll(pageNum -1, size);
		int current = page.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 10, page.getTotalPages());
		
		List<Article> list = page.getContent();
		Iterator<Article> it = list.iterator();
		Article temp = new Article();
		HashMap<String, String> countComment = new HashMap<String,String>(); 
		while(it.hasNext()){
			temp = it.next();
			String _id = temp.get_id(); // _id of article --> discussion_id
			HashMap<String, Object> hm = commentService.CountCommentsOfTheArticle(_id);
			countComment.put(""+_id, hm.get("count").toString());
			System.out.println("count " + hm.get("count").toString());
		}
		
		mav.addObject("page", page);
		mav.addObject("currentIndex", current);
		mav.addObject("endIndex", end);
		mav.addObject("beginIndex", begin);

		mav.addObject("countComment", countComment);
		return mav;
	}
	
	@RequestMapping("/get-user-by-id.html")
	public ModelAndView getUserBy_id(@PathParam(value="_id") String _id){
		ModelAndView mav = new ModelAndView();
		System.out.println("_id = " + _id);
		User u = new User();
		u = userService.getUserBy_id(_id);
		System.out.println("user id: " + u.get_id());
		System.out.println("user name " + u.getName());
		mav.addObject("user",u);
		mav.setViewName("user-info");
		return mav;
	}
	
	@ModelAttribute("topic")
	public List<Topic> getAllTopic(){
		try{
			return topicService.getAllTopic();
		}
		catch(Exception e){
			return null;
		}
	}
	
	@RequestMapping("/login.html")
	public ModelAndView callLoginPage(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}
	
	@RequestMapping("/signup.html")
	public ModelAndView callSignupPage(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("signup");
		return mav;
	}
	
	@RequestMapping(value="/register-processing", method = RequestMethod.POST)
	public ModelAndView register_processing(@Valid User userForm, BindingResult br, //@Valid tells Spring to validate the User object (this's important !)
			HttpServletRequest request, HttpServletResponse response, final RedirectAttributes redirectAttributes){
		
		ModelAndView mav = new ModelAndView();
		String name = "" + userForm.getName().trim();
		String email = "" + userForm.getEmail().trim();
		String password = "" + userForm.getPassword();
		String passwordconfirm = ""; 
		String describe = "" + userForm.getDescribe();
		String address = "" + userForm.getAddress();
		String workplace = "" + userForm.getWorkplace();
		
		System.out.println("name " + name + "\n dob "+ userForm.getDob() + "\n avatar " + userForm.getAvatar() );
		
		if(name.length() < 6 || name.length() > 30){
			br.rejectValue("name", "error.User", "tên quá ngắn, hoặc quá dài (độ dài từ 6 đến 30 kí tự)"); 
																			  // error.User : bao gồm error (mặc định), User là com.mongodbBlog.domain.User object
																			  // trong signup.jsp tại tag <form:form> phải có modelAttribute="user"
		}
		else{
			final String regexName = "[a-zA-Z0-9.-_@]*"; 
			if(!name.matches(regexName)){
			br.rejectValue("name", "error.User", "tên chỉ chứa ký tự chữ cái, số, dấu . - _ @ ");
			}
			else{
				User u1 = userService.getUserByName(name);
				try{
					if(u1.get_id() != null){
						br.rejectValue("name", "error.User", "tên này đã có người dùng, bạn hãy chọn tên khác..");
					}
				}
				catch(NullPointerException n){}
			}
		}
		
		
		
		if(email.length() > 254){
			br.rejectValue("email","error.User","email quá dài..!");
		}
		else{
			final String regexEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
			if(!email.matches(regexEmail)){
				br.rejectValue("email", "error.User", "email có vẻ như không hợp lệ ?!");
			}
			else{
				User u2 = userService.getUserByEmail(email);
				try{
					if(u2.get_id() != null){
					br.rejectValue("email", "error.User", "email này đã đăng ký rồi, hãy chọn email khác hoặc đăng nhập");
					}
				}
				catch(NullPointerException n){
				}
			}
		}
		
		if(request.getParameterMap().containsKey("passwordconfirm")){
			passwordconfirm = request.getParameter("passwordconfirm");
		}
		if(password.length() < 6 || password.length() > 60){
			br.rejectValue("password", "error.User", "mật khẩu quá ngắn hoặc quá dài (độ dài từ 6 đến 60 kí tự)");
		}
		else
			if(!password.equals(passwordconfirm)){
				br.rejectValue("password", "error.User", "mật khẩu không khớp");
			}
		
		if(describe.length() > 500){
			br.rejectValue("describe", "error.User", "giới thiệu quá dài (độ dài dưới 500 kí tự)");
		}
		if(address.length() > 60){
			br.rejectValue("address", "error.User", "địa chỉ quá dài (độ dài dưới 60 kí tự)");
		}
		if(workplace.length() > 60){
			br.rejectValue("workplace", "error.User", "nơi làm việc quá dài (độ dài dưới 60 kí tự)");
		}
		
		String _id_return="";
		if(br.hasErrors()){
			System.out.println("HAS ERROR : " +br.toString());
			mav.setViewName("signup");
			return mav;
		}
		else{
			_id_return = "" + userService.insertUser(userForm);
			if(_id_return.equals("")){
				Map<String,String> map = new HashMap<String,String>();
				map.put("message", "xin lỗi bạn, đã xảy ra lỗi ở server, bạn vui lòng đăng ký lại sau ...");
				redirectAttributes.addFlashAttribute("object",map);
				mav.setViewName("redirect:/register-fail.html");
				return mav;
			}
			else{
				Map<String,String> map = new HashMap<String,String>();
				map.put("name", name);
				map.put("email", email);
				System.out.println("ĐÊN ĐÂY RỒI ... ");
				redirectAttributes.addFlashAttribute("object",map);
				mav.setViewName("redirect:/register-success.html");
				return mav;
			}
		}
	}
	
	@RequestMapping(value="register-fail", method=RequestMethod.GET)
	public ModelAndView register_fail(@ModelAttribute("object") HashMap<String,String> map){
		System.out.println("đã đến register-fail");
		ModelAndView mav = new ModelAndView();
		if(map.containsKey("message")){
			mav.addObject("object", map);
			mav.setViewName("register-fail");
			return mav;
		}
		mav.setViewName("redirect:/home.html");
		return mav;
	}
	
	
	@RequestMapping(value="register-success", method=RequestMethod.GET)
	public ModelAndView register_success(@ModelAttribute("object") HashMap<String,String> map){
		System.out.println("đã đến register-success");
		ModelAndView mav = new ModelAndView();
		if(map.containsKey("name")){ // name or email
			mav.addObject("object", map);
			mav.setViewName("register-success");
			System.out.println("return register-success");
			return mav;
		}
		mav.setViewName("redirect:/home.html");
		return mav;
	}
	
	@RequestMapping(value="active_user", method=RequestMethod.GET, params={"username","token"})
	public ModelAndView active_user(@RequestParam("username") String name, @RequestParam("token") String token){
		ModelAndView mav = new ModelAndView();
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("user info", "user-info.html");
		mav.addObject("breadcrumb", map); // breadcrumb là một css class dùng cho thẻ div trong header --> xem header.jsp 
		
		String result = userService.activateAccount(name, token);
		switch(result){
		case "invalid-name":
			mav.setViewName("activating-fail");
			mav.addObject("message", "tên tài khoản (name) không tồn tại, hoặc mã kích hoạt không khớp, "
					+ "bạn hãy kiểm tra lại email, đảm bảo bạn truy cập vào đúng đường dẫn mà chúng tôi đã gửi !");
			return mav;
		case "url-broken":
			mav.setViewName("activating-fail");
			mav.addObject("message", "url này đã hỏng, tài khoản đã được kích hoạt từ trước rồi ..");
			return mav;
		case "non-active-account":
			mav.setViewName("activating-fail");
			mav.addObject("message", "tài khoản đã bị xóa hoặc bị khóa ..");
			return mav;
		case "invalid-token":
			mav.setViewName("activating-fail");
			mav.addObject("message", "tài khoản không kích hoạt được, do url không chính xác, bạn hãy kiểm tra lại email ...");
			return mav;
		case "error":
			mav.setViewName("activating-fail");
			mav.addObject("message", "sorry, đã xảy lỗi ở server, hiện tại tài khoản vẫn chưa được kích hoạt, phiền bạn kích hoạt lại sau ...");
			return mav;
		case "activated":
			mav.setViewName("activating-success");
			mav.addObject("name", name);
			mav.addObject("message", "tài khoản đã được kích hoạt, lần nữa, chúng tôi xin cảm ơn bạn đã đăng ký tài khoản ...");
			return mav;
		}
		mav.setViewName("activating-fail");
		mav.addObject("message", "bạn đang cố kích hoạt tài khoản? "
				+ "nhưng rất tiếc đã xảy ra lỗi, xin vui lòng thử lại sau, hoặc liên lạc với admin để khắc phục");
		return mav;
	}
	
	@RequestMapping("invalid-session")
	public void invalid_session(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException,IOException{
		System.out.println("\ninvalid-session---------------");
		response.sendRedirect("home.html");
	}

	
	//session-authentcation-error
	//session-expired
	@RequestMapping("session-authentcation-error")
	public void session_authentcation_error(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException,IOException{
		response.sendRedirect("home.html");
	}
	
	@RequestMapping("session-expired")
	public void session_expired(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException,IOException{
		response.sendRedirect("home.html");
	}
	
	@RequestMapping(value="user-info", params={"n"}, method=RequestMethod.GET)
	public ModelAndView getUserInfo(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes,
			@RequestParam("n") String name){
		ModelAndView mav = new ModelAndView();
		User user = new User();
		HttpSession session = request.getSession(false);
		if(session !=null){
			String usernameFromSession = (String)session.getAttribute("userName"); 
			if(name.equals(usernameFromSession)){
				mav.setViewName("redirect:/authUser/user-info.html");
				return mav;
			}
		}
		mav.addObject("changeable", "isnotallowed"); // cho jsp ở tầng View biết có thực hiện thêm form thay đổi thông tin user không --> trong trường hợp này là ko
		
		try{
			user  = userService.getUserByName(name);getClass();
			//....
			int status = user.getStatus();
			if(status==3){
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
			int enable = user.getEnable();
			if(enable==0){
				mav.addObject("enable", "chưa kích hoạt");
			}
			else{
				mav.addObject("enable", "đã kích hoạt");
			}
			//....
			String avatar = user.getAvatar();
			if (avatar.equals("0")){
				mav.addObject("avatar", "resources/front/img/Default-avatar.jpg");
			}
			else{
				mav.addObject("avatar", avatar);
			}
			mav.addObject("user", user);
			mav.addObject("messageFromGetUserInfo","show user info"); // cho jsp ở tầng View biết có thực hiện show thông tin user lên html không 
			mav.setViewName("user-info");
			return mav;
		}
		catch(Exception e){
			redirectAttributes.addFlashAttribute("messageFromGetUserInfo", "Không tồn tại thông tin người dùng mà bạn muốn xem "
					+ "hoặc đã xảy ra lỗi trong lúc lấy thông tin, bạn hãy quay lại sau...");
			redirectAttributes.addFlashAttribute("changeable", "isnotallowed");
			mav.setViewName("user-info.html?error=true");
			return mav;
		}
	}
	
	@RequestMapping(value="user-info", params={"error"}, method=RequestMethod.GET)
	public ModelAndView getUserInfoHasError(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes,
			@RequestParam("error") String error, @ModelAttribute("changeable") String changeable, @ModelAttribute("messageFromGetUserInfo") String message){
		ModelAndView mav = new ModelAndView();
		if(error.equals("unauthorized")){
			mav.addObject("changeable", changeable);
			mav.addObject("messageFromGetUserInfo", message);
		}
		else{
			// chuyển đến 404.jsp
		}
		return mav;
	}
	
	@RequestMapping("test")
	public ModelAndView Test(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
		HashMap<String, Object> hm = commentService.CountCommentsOfTheArticle("579087c5536b522ff5f97913");
		System.out.println("count Result is " + hm.toString() + hm.get("count"));
		return mav;
	}

//	@RequestMapping(value="", method=RequestMethod.GET)
//	  public List<User> getAll(int page) {
//	    Pageable pageable = new PageRequest(page, 5); //get 5 profiles on a page
//	    Page<Article> page = aticleService.findAll(pageable);
//	    return List.newArrayList(page);
//	}
	
	
	
	
	/*
	 * 
		String name = "";
		String email = "";
		String password = "";
		String passwordconfirm = "";
		Date dob ;
		String address = "";
		String workplace = "";
		String describe = "";
		
		Boolean flag = false;

		
		if(request.getParameterMap().containsKey("name")){
			name = request.getParameter("name");
		}
		else{
			mav.addObject("error","Đăng ký không thành công do server không nhận được dữ liệu");
			return mav;
		}
		if(request.getParameterMap().containsKey("email")){
			email = request.getParameter("email");
		}
		else{
			mav.addObject("error","Đăng ký không thành công do server không nhận được dữ liệu");
			return mav;
		}
		if(request.getParameterMap().containsKey("password")){
			password = request.getParameter("password");
		}
		else{
			mav.addObject("error","Đăng ký không thành công do server không nhận được dữ liệu");
			return mav;
		}
		if(request.getParameterMap().containsKey("passwordconfirm")){
			passwordconfirm = request.getParameter("passwordconfirm");
		}
		else{
			mav.addObject("error","Đăng ký không thành công do server không nhận được dữ liệu");
			return mav;
		}
		if(request.getParameterMap().containsKey("address")){
			address = request.getParameter("address");
		}
		else{
			mav.addObject("error","Đăng ký không thành công do server không nhận được dữ liệu");
			return mav;
		}
		if(request.getParameterMap().containsKey("workplace")){
			workplace = request.getParameter("workplace");
		}
		else{
			mav.addObject("error","Đăng ký không thành công do server không nhận được dữ liệu");
			return mav;
		}
		if(request.getParameterMap().containsKey("describe")){
			describe = request.getParameter("describe");
		}
		else{
			mav.addObject("error","Đăng ký không thành công do server không nhận được dữ liệu");
			return mav;
		}
		
		final String regexName = "^[a-zA-Z0-9.-_@]$"; 
		if(name==""){
			mav.addObject("error","tên không được để trống");
		}
		else{
			if(!name.matches(regexName)){
			mav.addObject("error","tên không hợp lệ");
		}
		}
	 */
	
}
