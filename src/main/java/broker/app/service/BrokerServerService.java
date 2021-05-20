package broker.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import broker.app.entity.BrokerCredentials;
import broker.app.entity.BrokerHoldings;
import broker.app.model.CreateAccountRequest;
import broker.app.model.CreateAccountResponse;
import broker.app.model.GetHoldingsRequest;
import broker.app.model.GetHoldingsResponse;
import broker.app.model.HoldingsRequest;
import broker.app.model.HoldingsResponse;
import broker.app.model.LoginRequest;
import broker.app.model.LoginResponse;
import broker.app.model.LogoutRequest;
import broker.app.model.LogoutResponse;
import broker.app.model.UpdateHoldingsRequest;
import broker.app.model.UpdateHoldingsResponse;
import broker.app.repository.BrokerCredentialsRepository;
import broker.app.repository.BrokerHoldingsRepository;

@Service
@Transactional
public class BrokerServerService {

	@Autowired
	private BrokerCredentialsRepository brokerCredentialsRepository;
	
	@Autowired
	private BrokerHoldingsRepository brokerHoldingsRepository;
	
	public LoginResponse loginUser(LoginRequest request) {
		BrokerCredentials userCredentialsDb = brokerCredentialsRepository.getCredentialsUsingUsername(request.getUsername());
		LoginResponse response = new LoginResponse();
		if(userCredentialsDb == null) {
			response.setError(true);
			response.setErrorMessage("Username not found");
			return response;
		}
		
		if(userCredentialsDb.getPassword().equals(request.getPassword())) {
			String apiKey = Utils.createApiKey(10);
			brokerCredentialsRepository.saveApiKey(apiKey, Utils.getTime(), userCredentialsDb.getBrokerCredentialsId());
			response.setApiKey(apiKey);
			response.setError(false);
			response.setFirstName(userCredentialsDb.getFirstName());
			response.setLastName(userCredentialsDb.getLastName());
			return response;
		} else {
			response.setError(true);
			response.setErrorMessage("Incorrect passowrd");
			return response;
		}
	}
	
	public LogoutResponse logoutUser(LogoutRequest request) {
		BrokerCredentials userCredentialsDb = brokerCredentialsRepository.getCredentialsUsingUsername(request.getUsername());
		LogoutResponse response = new LogoutResponse();
		if(!userCredentialsDb.getApiKey().equals(request.getApikey())) {
			response.setError(true);
			response.setErrorMessage("Incorrect API Key");
			return response;
		} else if (userCredentialsDb.getUsername() == null) {
			response.setError(true);
			response.setErrorMessage("Username not found");
			return response;
		} else {
			brokerCredentialsRepository.deleteApiKey(Utils.getTime(), userCredentialsDb.getBrokerCredentialsId());
			response.setError(false);
			return response;
		}
	}
	
	public CreateAccountResponse createAccount(CreateAccountRequest request) {
		BrokerCredentials userCredentialsDb = brokerCredentialsRepository.getCredentialsUsingUsername(request.getUsername());
		CreateAccountResponse response = new CreateAccountResponse();
		if(userCredentialsDb != null) {
			response.setError(true);
			response.setErrorMessage("Username already exists");
			return response;
		} else {
			brokerCredentialsRepository.createAccount(request.getUsername(), request.getPassword(), request.getFirstName(), request.getLastName(), Utils.createApiKey(10), Utils.getTime());
			response.setError(false);
			return response;
		}
	}
	
	public GetHoldingsResponse getUserHoldings(GetHoldingsRequest request) {
		BrokerCredentials userCredentialsDb = brokerCredentialsRepository.getCredentialsUsingUsername(request.getUsername());
		GetHoldingsResponse response = new GetHoldingsResponse();
		if(!request.getApikey().equals(userCredentialsDb.getApiKey())) {
			response.setError(true);
			response.setErrorMessage("User not logged in");
			return response;
		} else {
			response.setHoldings(brokerHoldingsRepository.getHoldingsUsingBrokerId(userCredentialsDb.getBrokerCredentialsId()));
			response.setError(false);
			return response;
		}
	}
	
	public UpdateHoldingsResponse updateUserHoldings(UpdateHoldingsRequest request) {
		BrokerCredentials userCredentialsDb = brokerCredentialsRepository.getCredentialsUsingUsername(request.getUsername());
		BrokerHoldings brokerHoldings = brokerHoldingsRepository.getStockInventoryUsingBrokerId(userCredentialsDb.getBrokerCredentialsId(), request.getStockId());
		UpdateHoldingsResponse response = new UpdateHoldingsResponse();
		if(request.getNumberOfStocks() > 0) {
			brokerHoldingsRepository.updateHoldings(request.getNumberOfStocks(), Utils.getAveragePrice(), Utils.getTime(), userCredentialsDb.getBrokerCredentialsId(), request.getStockId());
			response.setError(false);
			return response;
		} else if(request.getNumberOfStocks() < 0) {
			if(brokerHoldings.getNumberOfStocks() > Math.abs(request.getNumberOfStocks())) {
				brokerHoldingsRepository.updateHoldings(request.getNumberOfStocks(), Utils.getAveragePrice(), Utils.getTime(), userCredentialsDb.getBrokerCredentialsId(), request.getStockId());
				response.setError(false);
				return response;
			} else {
				response.setError(true);
				response.setErrorMessage("You do not own that many stocks");
				return response;
			}
		} else {
			response.setError(true);
			response.setErrorMessage("No stocks purchased");
			return response;
		}
	}
	
	
}
