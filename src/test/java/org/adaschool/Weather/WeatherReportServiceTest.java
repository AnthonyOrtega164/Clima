package org.adaschool.Weather;

import org.adaschool.Weather.data.WeatherApiResponse;
import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class WeatherReportServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherReportService weatherReportService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getWeatherReport_ReturnsCorrectWeatherReport() {
        double latitude = 40.111;
        double longitude = -50.0606;
        String url = "https://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude + "&appid=" + WeatherReportService.API_KEY;

        WeatherApiResponse.Main main = new WeatherApiResponse.Main();
        main.setTemperature(289);
        main.setHumidity(79);

        WeatherApiResponse response = new WeatherApiResponse();
        response.setMain(main);

        when(restTemplate.getForObject(url, WeatherApiResponse.class)).thenReturn(response);

        WeatherReport report = weatherReportService.getWeatherReport(latitude, longitude);

        assertEquals(289.92, report.getTemperature());
        assertEquals(77, report.getHumidity());
    }
}