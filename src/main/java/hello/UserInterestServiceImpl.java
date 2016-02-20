package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInterestServiceImpl implements UserInterestService{

	@Autowired
	private UserInterestRepository userInterestRepository;
	
	@Autowired
	private UserServiceImple userServiceImpl;
	
	@Override
	public void save(UserInterest userInterest) throws Exception{
		User user = userServiceImpl.getLoggedInUser();
		if(user != null) {
			userInterest.setUserId(user.getId());
		}
		userInterestRepository.save(userInterest);
	}

}
