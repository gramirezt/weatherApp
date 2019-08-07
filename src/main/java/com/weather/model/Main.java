package com.weather.model;

import java.io.Serializable;

public class Main implements Serializable{

	private static final long serialVersionUID = 9018838729952412836L;

	private Double temp;

	public Double getTemp() {
		return temp;
	}

	public void setTemp(Double temp) {
		this.temp = temp;
	}
}
