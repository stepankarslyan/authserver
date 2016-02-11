package hello;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class UserController {
    
	@Autowired
	private UserRepository userRepository;
	
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
    
    @RequestMapping(value="/users", method = RequestMethod.POST)
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
    public List<User> findAll() {	
    	return userRepository.findAll();
  
    }
    
}
