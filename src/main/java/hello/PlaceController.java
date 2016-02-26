package hello;

import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api")
public class PlaceController {
	
	@Value("${google.place.search.url}")
	private String googleSearchurl;
	
	@Autowired
	private GeoServiceImpl geoServiceImpl;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@RequestMapping(value =  "/places/{id}" , method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Place> findPlaces(@PathVariable("id") String interestId,  
			@RequestParam(value="latitude") Double latitude,
			@RequestParam(value="longitude") Double longitude) throws UnknownHostException {
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%5" + longitude + " " + longitude + " " + interestId);
		UserInterest userInterest = userServiceImpl.findOneUserInterest(interestId);
		return geoServiceImpl.findPlaces(userInterest.getType(), userInterest.getRadius(), longitude, latitude);
		
	}
	
}
