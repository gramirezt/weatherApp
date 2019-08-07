/**
 * 
 */
package com.weather.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.weather.exception.WeatherServiceException;
import com.weather.model.WeatherAppResponse;
import com.weather.service.WeatherService;

/**
 * Controller for Weather App
 * @author Gloria ramirez
 *
 */
@Controller
public class WeatherController {
	private static final Logger LOGGER = LogManager.getLogger(WeatherController.class);
	
	@Autowired
	private WeatherService weatherService;
	
	@GetMapping(value = "/")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("main");
	         
	    return modelAndView;
	}
	
	@GetMapping(value = "/weather")
	public ModelAndView getWeatherByCity(@RequestParam String city) {
		ModelAndView modelAndView = new ModelAndView(); 
		ResponseEntity<WeatherAppResponse> response; 
		try {
			response = this.weatherService.getWeatherByCity(city);
			modelAndView.setViewName("details");
	        modelAndView.addObject("response", response); 
	        modelAndView.addObject("weather", response.getBody());  
		} catch (WeatherServiceException e) { 
			LOGGER.error("An exception has ocurred:"+e.getMessage());
			modelAndView.setViewName("error");
	        modelAndView.addObject("message", e.getMessage());
		}
         
        return modelAndView;
	}

}
