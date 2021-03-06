package hello;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api")
public class InterestController {
	
	@Autowired
	private UserServiceImpl userServiceImple;
	
	@RequestMapping(value="/interests/", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public UserInterest save(@RequestBody UserInterest interest) throws Exception {
		return userServiceImple.saveUserInterest(interest);
	}
	
	@RequestMapping(value="/interests/", method=RequestMethod.GET)
	public List<UserInterest> findAll() throws Exception {
		return userServiceImple.findUserInterests();	
	}
	
	@RequestMapping(value="/interests/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public UserInterest findOne(@PathVariable("id") String id) throws Exception {
		return userServiceImple.findOneUserInterest(id);	
	}
	
	@RequestMapping(value="/interests/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE )
	public UserInterest update(@RequestBody UserInterest interest, @PathVariable("id") String id) throws Exception {
		return userServiceImple.updateUserInterest(interest, id);	
	}
	
	@RequestMapping(value="/interests/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") String id) throws Exception {
		userServiceImple.deleteUserInterest(id);	
		return "Deleted";
	}

}
