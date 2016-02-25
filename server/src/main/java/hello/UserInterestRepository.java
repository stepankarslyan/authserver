package hello;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserInterestRepository extends CrudRepository<UserInterest, String> {
	public List<UserInterest> findAllByUserId(String id);
}
