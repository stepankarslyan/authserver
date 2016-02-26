package hello;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface UserFavoriteRepository extends CrudRepository<Place, String> {
	public List<Place> findAllByUserId(String userId);
}
