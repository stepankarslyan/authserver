package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value =  "/home/" , method = RequestMethod.GET)
	public @ResponseBody Object home() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User loggedInUser = null;
		if(principal instanceof User) {
			loggedInUser = ((User) principal);
			
		}
		
	
		if (principal != null) {
			return principal;
		}
		return "Welcome";
		

	}
	
	@RequestMapping(value =  "/register/" , method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public void save(@RequestBody User user) {
		userRepository.save(user);
	}

}