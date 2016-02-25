package hello;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface UserFavoriteRepository extends CrudRepository<UserFavorite, String> {
	public List<UserFavorite> findAllByUserId(String userId);
}
