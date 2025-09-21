package com.example.WeatherRequestAPI.Service;

import com.example.WeatherRequestAPI.DTO.WeatherResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WeatherService {

    private final WebClient webClient;

    ObjectMapper mapper;

    //API key from https://www.visualcrossing.com/account/
    private final String apiKey = System.getenv("WEATHER_API_KEY");

    public WeatherService(WebClient webClient, ObjectMapper mapper) {
        this.webClient = webClient;
        this.mapper = mapper;
    }

    public WeatherResponseDTO getWeather(String city) throws Exception {

        String url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/" + city + "/today?key=" + apiKey;

        String jsonString = webClient.get().uri(url).retrieve().bodyToMono(String.class).block();

        return mapper.readValue(jsonString, WeatherResponseDTO.class);
    }
}
