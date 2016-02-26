package hello;

import java.util.List;

public interface GeoService {
	public List<Place> findPlaces(String search, Long radius, Double longitude, Double latitude);
}
