package broker.app.model;

public class ChangePasswordResponse {

	private boolean error;
	private String errorMessage;

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

	public ChangePasswordResponse(boolean error, String errorMessage) {
		super();
		this.error = error;
		this.errorMessage = errorMessage;
	}

	public ChangePasswordResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

}
