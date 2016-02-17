package hello;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeoServiceImpl implements GeoService {

	@Autowired
	private UserPlacesRepository userPlaceRepository;
	
	@Override
	public void saveUserPlace(PlaceDetailsResponse placeDetailsResponse) {
		
		for(PlaceDetails placeDetails : placeDetailsResponse.getResults()) {
			UserPlace favoritePlace = new UserPlace();
			List<PlacePhoto> placePhotos = placeDetails.getPhotos();
			PlaceGeometry placeGeometry = placeDetails.getGeometry();
			
			favoritePlace.setName(placeDetails.getName());
			favoritePlace.setAddress(placeDetails.getAddress());
			favoritePlace.setIcon(placeDetails.getIcon());
			
			/*if(!placePhotos.isEmpty()) {
				for(PlacePhoto placePhoto : placePhotos) {
					favoritePlace.setPhote(placePhoto.getReference());
				}
			}*/
			
			favoritePlace.setLatitude(placeGeometry.getLocation().getLat());
			favoritePlace.setLongitude(placeGeometry.getLocation().getLng());
		
			
			userPlaceRepository.save(favoritePlace);
		}
		
	}

}
