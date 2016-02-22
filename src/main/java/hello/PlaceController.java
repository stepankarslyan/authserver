package hello;

import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
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
	
	@RequestMapping(value =  "/places/" , method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Place> findPlaces(@RequestParam(value="search") String search, 
			@RequestParam(value="radius") Long radius, 
			@RequestParam(value="location") Location location) throws UnknownHostException {
		return geoServiceImpl.findPlaces(search, radius, location);
	}
	
}
