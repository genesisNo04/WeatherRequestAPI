package com.example.WeatherRequestAPI.Controller;

import com.example.WeatherRequestAPI.DTO.WeatherResponseDTO;
import com.example.WeatherRequestAPI.Service.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<WeatherResponseDTO> getWeather(@PathVariable String city) throws Exception {
        WeatherResponseDTO weatherResponseDTO = weatherService.getWeather(city);
        return ResponseEntity.status(HttpStatus.FOUND).body(weatherResponseDTO);
    }
}
