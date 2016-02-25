package hello;

import java.util.List;

public interface UserService {
	public User getLoggedInUser();
	public User registerUser(User user);
	public List<UserFavorite> findUserFavoritePlaces();
	public void saveUserFavoritePlace(UserFavorite favoritePlace);
	public UserFavorite findOne(String id);
	public void deleteUserFavoritePlace(String id);
	public List<UserInterest> findUserInterests();
	public void saveUserInterest(UserInterest interest);
	public UserInterest findOneUserInterest(String id);
	public void deleteUserInterest(String id);
	public void updateUserInterest(UserInterest interest, String id);
}
