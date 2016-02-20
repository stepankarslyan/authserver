package hello;

import java.net.UnknownHostException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserPlacesRepository userPlaceRepository;
	
	@Autowired
	private GeoServiceImpl geoService;
	

	@Autowired
	private UserServiceImple userServiceImpl;
	
	@RequestMapping(value =  "/login/" , method = RequestMethod.GET)
	public @ResponseBody String home() {
		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication != null) {
			System.out.println("*************************** " + authentication.toString() + " ***************************");
			User user = userRepository.findOneByUsername(authentication.getName());
			return user.getId();
		} else {
			return null;
		}

	}
	
	@RequestMapping(value =  "/places/" , method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public PlaceDetailsResponse getPlaces() throws UnknownHostException {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://maps.googleapis.com/maps/api/place/textsearch/json?radius=30000&types=bar&key=AIzaSyDy3ioBP0XQyWIXGZ3Tp_gJffdK0WcwYAc";
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		PlaceDetailsResponse response = restTemplate.getForObject(url, PlaceDetailsResponse.class);
		geoService.saveUserPlace(response);
		
		/*List<UserPlace> places = new ArrayList<UserPlace>();
		for(PlaceDetails placeDetails : response.getResults()) {
			UserPlace favoritePlace = new UserPlace();
			List<PlacePhoto> placePhotos = placeDetails.getPhotos();
			System.out.println(places.toString() + " fdfddfdfddf");
			PlaceGeometry placeGeometry = placeDetails.getGeometry();
			
			favoritePlace.setName(placeDetails.getName());
			favoritePlace.setAddress(placeDetails.getAddress());
			favoritePlace.setIcon(placeDetails.getIcon());
			
			if(placePhotos.size() != 0) {
				for(PlacePhoto placePhoto : placePhotos) {
					favoritePlace.setPhote(placePhoto.getReference());
				}
			}
			
			favoritePlace.setLatitude(placeGeometry.getLocation().getLat());
			favoritePlace.setLongitude(placeGeometry.getLocation().getLng());
			places.add(favoritePlace);
		}*/
		
		return response;

	}
	
	@RequestMapping(value =  "/logged" , method = RequestMethod.GET)
	public @ResponseBody String logged(HttpServletRequest request, HttpServletResponse response) {
		 Cookie[] cookies = request.getCookies();
	        for (Cookie cookie : cookies) {
	            cookie.setMaxAge(0);
	            cookie.setValue(null);
	            cookie.setPath("/");
	            response.addCookie(cookie);
	        }
	            return "Logged out";

	}
	
	@RequestMapping(value =  "/register/" , method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public void save(@RequestBody User user) {
		userServiceImpl.registerUser(user);
	}

}