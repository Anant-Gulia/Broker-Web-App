package broker.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
import broker.app.service.BrokerServerService;

@RestController
public class BrokerServerController {

	@Autowired
	private BrokerServerService brokerServerService;
	
	@CrossOrigin
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public LoginResponse login(@RequestBody LoginRequest request) {
		return brokerServerService.loginUser(request);
	}
	
	@CrossOrigin
	@RequestMapping(path = "/logout", method = RequestMethod.POST)
	public LogoutResponse logout(@RequestBody LogoutRequest request) {
		return brokerServerService.logoutUser(request);
	}
	
	@CrossOrigin
	@RequestMapping(path = "/CreateAccount", method = RequestMethod.POST)
	public CreateAccountResponse create(@RequestBody CreateAccountRequest request) {
		return brokerServerService.createAccount(request);
	}
	
	@CrossOrigin
	@RequestMapping(path = "/GetHoldings", method = RequestMethod.POST)
	public GetHoldingsResponse getHoldings(@RequestBody GetHoldingsRequest request) {
		return brokerServerService.getUserHoldings(request);
	}
	
	@CrossOrigin
	@RequestMapping(path = "/UpdateHoldings", method = RequestMethod.POST)
	public UpdateHoldingsResponse updateHoldings(@RequestBody UpdateHoldingsRequest request) {
		return brokerServerService.updateUserHoldings(request);
	}
	
	@CrossOrigin
	@RequestMapping(path = "/Transactions", method = RequestMethod.POST)
	public GetTransactionsResponse getTransactions(@RequestBody GetTransactionsRequest request) {
		return brokerServerService.getTransactions(request);
	}
}
