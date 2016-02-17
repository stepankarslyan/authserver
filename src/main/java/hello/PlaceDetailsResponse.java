package hello;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import hello.PlaceDetails;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceDetailsResponse {
	@JsonProperty("results")
	private List<PlaceDetails> results;

	public List<PlaceDetails> getResults() {
		return results;
	}

	public void setResults(List<PlaceDetails> results) {
		this.results = results;
	}
 
	
}
