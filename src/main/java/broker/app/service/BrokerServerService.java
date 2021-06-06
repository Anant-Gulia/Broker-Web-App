package broker.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import broker.app.entity.BrokerCredentials;
import broker.app.entity.BrokerHoldings;
import broker.app.model.ChangeNameRequest;
import broker.app.model.ChangeNameResponse;
import broker.app.model.ChangePasswordRequest;
import broker.app.model.ChangePasswordResponse;
import broker.app.model.CreateAccountRequest;
import broker.app.model.CreateAccountResponse;
import broker.app.model.GetHoldingsRequest;
import broker.app.model.GetHoldingsResponse;
import broker.app.model.GetTransactionsRequest;
import broker.app.model.GetTransactionsResponse;
import broker.app.model.LoginRequest;
import broker.app.model.LoginResponse;
import broker.app.model.LogoutRequest;
import broker.app.model.LogoutResponse;
import broker.app.model.UpdateHoldingsRequest;
import broker.app.model.UpdateHoldingsResponse;
import broker.app.repository.BrokerCredentialsRepository;
import broker.app.repository.BrokerHoldingsRepository;
import broker.app.repository.BrokerTransactionsRepository;

@Service
@Transactional
public class BrokerServerService {

	@Autowired
	private BrokerCredentialsRepository brokerCredentialsRepository;
	
	@Autowired
	private BrokerHoldingsRepository brokerHoldingsRepository;
	
	@Autowired
	private BrokerTransactionsRepository brokerTransactionsRepository;
	
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
			brokerCredentialsRepository.createAccount(request.getUsername(), request.getPassword(), request.getFirstName(), request.getLastName(), Utils.getTime());
			response.setError(false);
			return response;
		}
	}
	
	public GetHoldingsResponse getUserHoldings(GetHoldingsRequest request) {
		BrokerCredentials userCredentialsDb = brokerCredentialsRepository.getCredentialsUsingUsername(request.getUsername());
		GetHoldingsResponse response = new GetHoldingsResponse();
		if(!request.getApikey().equals(userCredentialsDb.getApiKey())) {
			response.setError(true);
			response.setErrorMessage("Incorrect API key");
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
		System.out.println(request.getApiKey());
		System.out.println(userCredentialsDb.getApiKey());
		if(!request.getApiKey().equals(userCredentialsDb.getApiKey())) {
			response.setError(true);
			response.setErrorMessage("Incorrect API key");
			return response;
		}
		if(request.getNumberOfStocks() > 0) {
			if(brokerHoldings == null) {
				brokerHoldingsRepository.newHoldings(request.getNumberOfStocks(), Utils.getAveragePrice(50,request.getNumberOfStocks(),0,0), Utils.getTime(), userCredentialsDb.getBrokerCredentialsId(), request.getStockId());
				brokerTransactionsRepository.addTransaction(userCredentialsDb.getBrokerCredentialsId(), request.getStockId(), request.getNumberOfStocks(), request.getNumberOfStocks() * 50F, Utils.getTime(), "BUY");
				response.setError(false);
				return response;
			} else {
				brokerHoldingsRepository.updateHoldings(request.getNumberOfStocks() + brokerHoldings.getNumberOfStocks(), Utils.getAveragePrice(50, request.getNumberOfStocks(), brokerHoldings.getNumberOfStocks(), brokerHoldings.getAveragePrice()), Utils.getTime(), userCredentialsDb.getBrokerCredentialsId(), request.getStockId());
				brokerTransactionsRepository.addTransaction(userCredentialsDb.getBrokerCredentialsId(), request.getStockId(), request.getNumberOfStocks(), request.getNumberOfStocks() * 50F, Utils.getTime(), "BUY");
				response.setError(false);
				return response;
			}
		} else if(request.getNumberOfStocks() < 0) {
			if(brokerHoldings == null) {
				response.setError(true);
				response.setErrorMessage("No share owned for that stock");
				return response;
			} else if(brokerHoldings.getNumberOfStocks() > Math.abs(request.getNumberOfStocks())) {
				brokerHoldingsRepository.updateHoldings(request.getNumberOfStocks() + brokerHoldings.getNumberOfStocks(), Utils.getAveragePrice(50, request.getNumberOfStocks() + brokerHoldings.getNumberOfStocks(),0,0), Utils.getTime(), userCredentialsDb.getBrokerCredentialsId(), request.getStockId());
				brokerTransactionsRepository.addTransaction(userCredentialsDb.getBrokerCredentialsId(), request.getStockId(), Math.abs(request.getNumberOfStocks()), Math.abs(request.getNumberOfStocks()) * 50F, Utils.getTime(), "SELL");
				response.setError(false);
				return response;
			} else if(brokerHoldings.getNumberOfStocks() == Math.abs(request.getNumberOfStocks())) {
				brokerHoldingsRepository.deleteHoldings(userCredentialsDb.getBrokerCredentialsId(), request.getStockId());
				brokerTransactionsRepository.addTransaction(userCredentialsDb.getBrokerCredentialsId(), request.getStockId(), Math.abs(request.getNumberOfStocks()), Math.abs(request.getNumberOfStocks()) * 50F, Utils.getTime(), "SELL");
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
	
	public GetTransactionsResponse getTransactions(GetTransactionsRequest request) {
		BrokerCredentials userCredentialsDb = brokerCredentialsRepository.getCredentialsUsingUsername(request.getUsername());
		GetTransactionsResponse response = new GetTransactionsResponse();
		if(!request.getApiKey().equals(userCredentialsDb.getApiKey())) {
			response.setError(true);
			response.setErrorMessage("Incorrect API key");
			return response;
		} else {
			response.setUsername(request.getUsername());
			response.setTransactions(brokerTransactionsRepository.getTransactionsUsingBrokerId(userCredentialsDb.getBrokerCredentialsId()));
			response.setError(false);
			return response;
		}
	}
	
	public ChangeNameResponse changeName(ChangeNameRequest request) {
		BrokerCredentials userCredentialsDb = brokerCredentialsRepository.getCredentialsUsingUsername(request.getUsername());
		ChangeNameResponse response = new ChangeNameResponse();
		if(!request.getApiKey().equals(userCredentialsDb.getApiKey())) {
			response.setError(true);
			response.setErrorMessage("Incorrect API key");
			return response;
		} else {
			brokerCredentialsRepository.changeName(request.getFirstName(), request.getLastName(), userCredentialsDb.getBrokerCredentialsId());
			response.setError(false);
			return response;
		}
	}
	
	public ChangePasswordResponse changePassword(ChangePasswordRequest request) {
		BrokerCredentials userCredentialsDb = brokerCredentialsRepository.getCredentialsUsingUsername(request.getUsername());
		ChangePasswordResponse response = new ChangePasswordResponse();
		if(!request.getApiKey().equals(userCredentialsDb.getApiKey())) {
			response.setError(true);
			response.setErrorMessage("Incorrect API key");
			return response;
		} else {
			brokerCredentialsRepository.changePassword(request.getPassword(), userCredentialsDb.getBrokerCredentialsId());
			response.setError(false);
			return response;
		}
	}
	
}
