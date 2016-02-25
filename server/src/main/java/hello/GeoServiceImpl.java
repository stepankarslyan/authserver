package hello;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeoServiceImpl implements GeoService {
	
	@Value("${google.place.search.url}")
	private String googleSearchurl;

	@Override
	public List<Place> findPlaces(String search, Long radius, Location location) {
		RestTemplate restTemplate = new RestTemplate();
		String url = googleSearchurl + "&query=" + search + "&radius=" + radius + "&location=" + location.getLat() + "," + location.getLng();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		PlaceSearchResponse response = restTemplate.getForObject(url, PlaceSearchResponse.class);
		
		return response.getResults();
	}

}
