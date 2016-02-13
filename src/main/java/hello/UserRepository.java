package hello;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String>{
	public User findOneByLogin(String login);
}
