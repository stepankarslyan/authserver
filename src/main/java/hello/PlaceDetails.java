package hello;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceDetails {

	@JsonProperty("name")
	private String name;
 
	@JsonProperty("icon")
	private String icon;
 
	@JsonProperty("formatted_address")
	private String address;

	@JsonProperty("geometry")
	private PlaceGeometry geometry;

	@JsonProperty("photos")
  	private List<PlacePhoto> photos;

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getIcon() {
		return icon;
	}
	
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public PlaceGeometry getGeometry() {
		return geometry;
	}
	
	public void setGeometry(PlaceGeometry geometry) {
		this.geometry = geometry;
	}
	
	public List<PlacePhoto> getPhotos() {
		return photos;
	}
	
	public void setPhotos(List<PlacePhoto> photos) {
		this.photos = photos;
	}
	 
}
