package hello;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserPlacesRepository extends CrudRepository<UserPlace, String> {
	public List<UserPlace> findAll();
}
