package broker.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateAccountRequest {
	
	@JsonProperty(value = "username", required = true)
	private String username;
	@JsonProperty(value = "password", required = true)
	private String password;
	@JsonProperty(value = "firstName", required = true)
	private String firstName;
	@JsonProperty(value = "lastName", required = true)
	private String lastName;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

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

	public CreateAccountRequest(String username, String password, String firstName, String lastName) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public CreateAccountRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

}
