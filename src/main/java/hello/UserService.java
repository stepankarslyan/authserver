package hello;

import java.util.List;

public interface UserService {
	public User getLoggedInUser();
	public User registerUser(User user);
	public List<Place> findUserFavoritePlaces();
	public Place saveUserFavoritePlace(Place favoritePlace);
	public Place findOne(String id);
	public void deleteUserFavoritePlace(String id);
	public List<UserInterest> findUserInterests();
	public UserInterest saveUserInterest(UserInterest interest);
	public UserInterest findOneUserInterest(String id);
	public void deleteUserInterest(String id);
	public UserInterest updateUserInterest(UserInterest interest, String id);
}
