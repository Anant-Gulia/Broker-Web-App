package broker.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChangePasswordRequest {

	@JsonProperty(value = "password", required = true)
	private String password;

	@JsonProperty(value = "apiKey", required = true)
	private String apiKey;

	@JsonProperty(value = "username", required = true)
	private String username;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public ChangePasswordRequest(String password, String apiKey, String username) {
		super();
		this.password = password;
		this.apiKey = apiKey;
		this.username = username;
	}

	public ChangePasswordRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

}
