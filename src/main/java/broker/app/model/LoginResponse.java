package broker.app.model;

public class LoginResponse {
	private String firstName;
	private String lastName;
	private String apiKey;
	private boolean error;
	private String errorMessage;

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

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public LoginResponse(String firstName, String lastName, String apiKey, boolean error, String errorMessage) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.apiKey = apiKey;
		this.error = error;
		this.errorMessage = errorMessage;
	}

	public LoginResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

}