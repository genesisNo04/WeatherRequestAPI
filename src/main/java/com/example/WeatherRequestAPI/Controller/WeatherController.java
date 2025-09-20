package com.example.WeatherRequestAPI.Controller;

import com.example.WeatherRequestAPI.Service.WeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/{city}")
    public String getWeather(@PathVariable String city) throws JsonProcessingException {
        return weatherService.getWeather(city);
    }
}
