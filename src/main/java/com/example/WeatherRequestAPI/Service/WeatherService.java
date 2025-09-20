package com.example.WeatherRequestAPI.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WeatherService {

    private final WebClient webClient;

    //API key from https://www.visualcrossing.com/account/
    private final String apiKey = "";

    public WeatherService(WebClient webClient) {
        this.webClient = webClient;
    }

    public String getWeather(String city) throws JsonProcessingException {

        String url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/" + city + "/today?key=" + apiKey;

        String jsonString = webClient.get().uri(url).retrieve().bodyToMono(String.class).block();

        ObjectMapper mapper = new ObjectMapper();
        Object json = mapper.readValue(jsonString, Object.class);
        String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);

        return prettyJson;
    }
}
