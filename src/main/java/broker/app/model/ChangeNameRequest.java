package broker.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChangeNameRequest {

	@JsonProperty(value = "firstName", required = true)
	private String firstName;

	@JsonProperty(value = "lastName", required = true)
	private String lastName;

	@JsonProperty(value = "apiKey", required = true)
	private String apiKey;

	@JsonProperty(value = "username", required = true)
	private String username;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ChangeNameRequest(String firstName, String lastName, String apiKey, String username) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.apiKey = apiKey;
		this.username = username;
	}

	public ChangeNameRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

}
