package hello;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserInterestRepository userInterestRepository;
	
	@Autowired
	private UserFavoriteRepository userfavoriteRepository;
	
	@Override
	public User getLoggedInUser() {
		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) {
			return userRepository.findOneByUsername(authentication.getName());
		} 
		
		return null;
		
	}
	
	@Override
	public User registerUser(User user) {
		user.setEnabled(true);
		UserRole role = new UserRole();
		role.setUsername(user.getUsername());
		role.setRole("ROLE_USER");
		roleRepository.save(role);
		return userRepository.save(user);
	}

	@Override
	public List<UserFavorite> findUserFavoritePlaces() {
		User loggedInUser = this.getLoggedInUser(); 
		return userfavoriteRepository.findAllByUserId(loggedInUser.getId());
	}

	@Override
	public void saveUserFavoritePlace(UserFavorite favoritePlace) {
		User loggedInUser = this.getLoggedInUser(); 
		favoritePlace.setUserId(loggedInUser.getId());
		userfavoriteRepository.save(favoritePlace);
		
	}

	@Override
	public UserFavorite findOne(String id) {
		return userfavoriteRepository.findOne(id);
	}

	@Override
	public void deleteUserFavoritePlace(String id) {
		userfavoriteRepository.delete(id);
	}

	@Override
	public List<UserInterest> findUserInterests() {
		User loggedInUser = this.getLoggedInUser();
		return userInterestRepository.findAllByUserId(loggedInUser.getId());
	}

	@Override
	public void saveUserInterest(UserInterest interest) {
		User loggedInUser = this.getLoggedInUser();
		interest.setUserId(loggedInUser.getId());
		userInterestRepository.save(interest);
	}

	@Override
	public UserInterest findOneUserInterest(String id) {		
		return userInterestRepository.findOne(id);
	}

	@Override
	public void deleteUserInterest(String id) {
		userInterestRepository.delete(id);
	}

	@Override
	public void updateUserInterest(UserInterest interest, String id) {
		interest.setId(id);
		userInterestRepository.save(interest);
	}
}
