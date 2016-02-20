package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api")
public class InterestController {
	
	@Autowired
	private UserInterestServiceImpl userInterestServiceImple;
	
	@RequestMapping(value="/interests", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public void save(@RequestBody UserInterest userInterest) throws Exception {
		userInterestServiceImple.save(userInterest);	
	}

}
