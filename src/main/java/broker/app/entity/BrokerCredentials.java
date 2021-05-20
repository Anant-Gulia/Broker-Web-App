package broker.app.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BROKER_CREDENTIALS")
public class BrokerCredentials {

	@Id
	@Column(name = "BROKER_CREDENTIALS_ID")
	private Integer brokerCredentialsId;
	@Column(name = "FIRST_NAME")
	private String firstName;
	@Column(name = "LAST_NAME")
	private String lastName;
	@Column(name = "USERNAME")
	private String username;
	@Column(name = "API_KEY")
	private String apiKey;
	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;
	@Column(name = "UPDATED_DATE")
	private LocalDateTime updatedDate;
	@Column(name = "PASSWORD")
	private String password;

	public Integer getBrokerCredentialsId() {
		return brokerCredentialsId;
	}

	public void setBrokerCredentialsId(Integer emailCredentialsId) {
		this.brokerCredentialsId = emailCredentialsId;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BrokerCredentials(Integer emailCredentialsId, String firstName, String lastName, String username,
			String apiKey, LocalDateTime createdDate, LocalDateTime updatedDate, String password) {
		super();
		this.brokerCredentialsId = emailCredentialsId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.apiKey = apiKey;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.password = password;
	}

	public BrokerCredentials() {
		super();
		// TODO Auto-generated constructor stub
	}

}
