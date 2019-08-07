package com.weather.model;

import java.io.Serializable;

public class Sys implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long sunrise;
	private Long sunset;
	
	public Long getSunrise() {
		return sunrise;
	}
	public void setSunrise(Long sunrise) {
		this.sunrise = sunrise;
	}
	public Long getSunset() {
		return sunset;
	}
	public void setSunset(Long sunset) {
		this.sunset = sunset;
	}

}
