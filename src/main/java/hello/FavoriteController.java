package hello;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FavoriteController {
	
	@Autowired
	private UserServiceImpl userServiceImple;
	
	@RequestMapping(value="/favorites/", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public void save(@RequestBody UserFavorite favorite) throws Exception {
		userServiceImple.saveUserFavoritePlace(favorite);	
	}
	
	@RequestMapping(value="/favorites/", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<UserFavorite> findAll() throws Exception {
		return userServiceImple.findUserFavoritePlaces();	
	}
	
	@RequestMapping(value="/favorites/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public UserFavorite findOne(@PathVariable("id") String id) throws Exception {
		return userServiceImple.findOne(id);	
	}
	
	@RequestMapping(value="/favoreites/{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable("id") String id) throws Exception {
		userServiceImple.deleteUserFavoritePlace(id);	
	}
}
