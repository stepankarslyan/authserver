package hello;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@RequestMapping(value =  "/login/" , method = RequestMethod.GET)
	public @ResponseBody User login() {
		return userServiceImpl.getLoggedInUser();
	}
	
	@RequestMapping(value =  "/logout" , method = RequestMethod.GET)
	public @ResponseBody String logged(HttpServletRequest request, HttpServletResponse response) {
		 Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            cookie.setMaxAge(0);
            cookie.setValue(null);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
           
        return HttpStatus.OK.toString();

	}
	
	@RequestMapping(value =  "/register/" , method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public void save(@RequestBody User user) {
		userServiceImpl.registerUser(user);
	}
}