package hello;

import java.util.ArrayList;
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
	public List<Place> findPlaces(String search, Long radius, Double longitude, Double latitude) {
		List<Place> foundPlaceses = new ArrayList<Place>();
		RestTemplate restTemplate = new RestTemplate();
		String url = googleSearchurl + "&query=" + search + "&radius=" + radius + "&location=" + latitude + "," + longitude;
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		PlaceSearchResponse response = restTemplate.getForObject(url, PlaceSearchResponse.class);
		for (GooglePlace googlePlace : response.getResults()) {
			Geometry geometry = googlePlace.getGeometry();
			Place place = new Place();
			place.setName(googlePlace.getName());
			place.setAddress(googlePlace.getAddress());
			place.setIcon(googlePlace.getIcon());
			place.setFavorite(false);
			place.setPlaceId(googlePlace.getPlaceId());
			place.setLatitude(geometry.getLocation().getLat());
			place.setLongitude(geometry.getLocation().getLng());
			foundPlaceses.add(place);
		}
		
		return foundPlaceses;
	}

}
