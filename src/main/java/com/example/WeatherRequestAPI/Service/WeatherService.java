package com.example.WeatherRequestAPI.Service;

import com.example.WeatherRequestAPI.DTO.WeatherResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WeatherService {

    private final WebClient webClient;

    @Autowired
    private RateLimiterService rateLimiterService;

    ObjectMapper mapper;

    //API key from https://www.visualcrossing.com/account/
    private final String apiKey = System.getenv("WEATHER_API_KEY");

    public WeatherService(WebClient webClient, ObjectMapper mapper) {
        this.webClient = webClient;
        this.mapper = mapper;
    }

    public WeatherResponseDTO getWeather(String city) throws Exception {
        if (!rateLimiterService.isAllowed(city, 5, 60)) {
            throw new RuntimeException("Rate limit exceeded for city: " + city);
        }

        return fetchWeather(city);
    }

    @Cacheable(value = "weather", key = "#city")
    public WeatherResponseDTO fetchWeather(String city) throws Exception {

        System.out.println("Fetching weather from API for: " + city);
        String url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/" + city + "/today?key=" + apiKey;

        String jsonString = webClient.get().uri(url).retrieve().bodyToMono(String.class).block();

        return mapper.readValue(jsonString, WeatherResponseDTO.class);
    }
}
