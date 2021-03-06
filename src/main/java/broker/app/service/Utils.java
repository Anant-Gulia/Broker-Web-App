package broker.app.service;

import java.time.LocalDateTime;
import java.util.Random;

public class Utils {

	public static String createApiKey(int length) {
		StringBuilder randomKey = new StringBuilder("");
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			randomKey.append((char) (random.nextInt(26) + 'a'));
		}
		return randomKey.toString();
	}
	
	public static LocalDateTime getTime() {
		return LocalDateTime.now();
	}
	
	public static float getAveragePrice(float currentPrice, float boughtNumberOfStocks, float ownedNumberOfStocks, float previousAverage) {
		return ((previousAverage * ownedNumberOfStocks) + ( boughtNumberOfStocks * currentPrice) ) / (ownedNumberOfStocks + boughtNumberOfStocks);
	}
}
