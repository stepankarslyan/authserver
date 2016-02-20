package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImple implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public User getLoggedInUser() {
		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null) {
			return userRepository.findOneByUsername(authentication.getName());
		} 
		
		return null;
		
	}
	
	@Override
	public void registerUser(User user) {
		user.setEnabled(true);
		UserRole role = new UserRole();
		role.setUsername(user.getUsername());
		role.setRole("ROLE_USER");
		roleRepository.save(role);
		userRepository.save(user);
	}

}
