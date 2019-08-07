package com.weather.builder;

import java.util.Date;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.weather.exception.WeatherServiceException;
import com.weather.model.Weather;
import com.weather.model.WeatherAppResponse;
import com.weather.model.WeatherClientResponse;
import com.weather.util.Utils;

/**
 * Class to transform client response to weather object
 * 
 * @author gloria.ramirez
 */
public class WeatherResponseBuilder {

	private static final Logger LOGGER = LogManager.getLogger(WeatherResponseBuilder.class);

	private WeatherResponseBuilder() {
		throw new IllegalStateException("WeatherResponseBuilder exception");
	}

	/**
	 * Build response weather object
	 * 
	 * @param openWeather
	 * @param body
	 * @param openWeather
	 * @param body
	 * @return WeatherAppResponse object
	 */
	public static WeatherAppResponse buildWeatherResponse(String city, WeatherClientResponse clientResponse,
			String timeFormat, String decimalFormat) throws WeatherServiceException {
 
		WeatherAppResponse response = new WeatherAppResponse();
		try {
			Optional<WeatherClientResponse> optionalClientResponse = Optional.ofNullable(clientResponse);
			if (optionalClientResponse.isPresent()) {
				String sunset = Utils.parseTime(clientResponse.getSys().getSunset(), timeFormat);
				String sunrise = Utils.parseTime(clientResponse.getSys().getSunrise(), timeFormat);
				Double celsius = Utils.kelvinToCelsius(clientResponse.getMain().getTemp(), decimalFormat);
				Double fahrenheit = Utils.kelvinToFahrenheit(clientResponse.getMain().getTemp(), decimalFormat);

				Optional<Weather> weather = clientResponse.getWeather().stream().findFirst();
				if (weather.isPresent()) {
					response.setDescription(Optional.ofNullable(weather.get().getDescription()).orElseGet(() -> ""));
				}
				response.setCityName(city);
				response.setCurrentDate(new Date());
				response.setTempC(Optional.ofNullable(celsius).orElseGet(() -> 0D));
				response.setTempF(Optional.ofNullable(fahrenheit).orElseGet(() -> 0D));
				response.setSunset(Optional.ofNullable(sunset).orElseGet(() -> ""));
				response.setSunrise(Optional.ofNullable(sunrise).orElseGet(() -> ""));
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new WeatherServiceException(e.getMessage());
		}
		return response;
	}

}
