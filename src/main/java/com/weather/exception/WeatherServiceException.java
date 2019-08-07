package com.weather.exception;


public class WeatherServiceException extends Exception {

	private static final long serialVersionUID = 6424400907298711127L;

	public WeatherServiceException() {
	}

	public WeatherServiceException(String message) {
		super(message);
	}

	public WeatherServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}
