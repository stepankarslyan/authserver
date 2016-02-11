package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    
	@Autowired
	private UserRepository userRepository;
	
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
    
    @RequestMapping(value="/users", consumes = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public void save(@RequestBody User user) {
    	if(user!=null) {
    		userRepository.save(user);
    	}
    	else {
    		User newuser = new User();
        	newuser.setLogin("Stepan");
        	newuser.setPasswrd("password");
        	userRepository.save(newuser);
    	}	
        
    }
    
    @RequestMapping(value="/users", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<User> findAll() {	
    	return userRepository.findAll();
  
    }
    
}
