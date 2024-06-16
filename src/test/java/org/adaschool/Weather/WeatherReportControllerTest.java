package org.adaschool.Weather;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.adaschool.Weather.controller.WeatherReportController;
import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WeatherReportControllerTest {

    @Mock
    private WeatherReportService weatherReportService;

    @InjectMocks
    private WeatherReportController weatherReportController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getWeatherReportTest() {
        double latitude = 37.8888;
        double longitude = -12.4242;
        WeatherReport mockWeatherReport = new WeatherReport(); // Create a mock WeatherReport object with expected values
        when(weatherReportService.getWeatherReport(latitude, longitude)).thenReturn(mockWeatherReport);

        WeatherReport result = weatherReportController.getWeatherReport(latitude, longitude);

        assertNotNull(result);
        assertEquals(mockWeatherReport, result);
        verify(weatherReportService).getWeatherReport(latitude, longitude);
    }
}