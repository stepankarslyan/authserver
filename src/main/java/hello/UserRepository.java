package hello;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserRepository extends CrudRepository<User, String>{
	public User findOneByUsername(String username) throws UsernameNotFoundException;
}
