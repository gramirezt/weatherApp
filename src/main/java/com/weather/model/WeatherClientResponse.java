package com.weather.model;

import java.io.Serializable;
import java.util.List;

public class WeatherClientResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	private List<Weather> weather;
	private Main main;
	private Sys sys;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Weather> getWeather() {
		return weather;
	}

	public void setWeather(List<Weather> weather) {
		this.weather = weather;
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public Sys getSys() {
		return sys;
	}

	public void setSys(Sys sys) {
		this.sys = sys;
	}

	@Override
	public String toString() {
		return "WeatherClientResponse [id=" + id + ", weather=" + weather + ", main=" + main + ", sys=" + sys + "]";
	}

	
}
