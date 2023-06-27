package com.example.weatherservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherControllerTest {

    @Mock
    private WeatherController weatherController;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(weatherController).build();
    }

    @Test
    public void testGetWeatherForWeek() throws Exception {
        // Arrange
        int kalenderWoche = 10;

        WeatherOfWeek expectedWeather = new WeatherOfWeek();
        expectedWeather.setKalenderWoche(kalenderWoche);
        expectedWeather.setStadt("Weiden");

        Day monday = new Day();
        monday.setName("Montag");
        monday.setDatum("2023-03-06");
        monday.setTemperatur("20 Grad");
        monday.setLuftdruck("10pa");
        expectedWeather.addDay(monday);

        // Act
        when(weatherController.getWeatherForWeek(anyInt())).thenReturn(expectedWeather);

        mockMvc.perform(get("/api/wetter")
                        .param("kalenderWoche", String.valueOf(kalenderWoche)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.kalenderWoche").value(kalenderWoche))
                .andExpect(jsonPath("$.stadt").value("Weiden"))
                .andExpect(jsonPath("$.tage[0].name").value("Montag"))
                .andExpect(jsonPath("$.tage[0].datum").value("2023-03-06"))
                .andExpect(jsonPath("$.tage[0].temperatur").value("20 Grad"))
                .andExpect(jsonPath("$.tage[0].luftdruck").value("10pa"));

        // Assert
        assertNotNull(expectedWeather);
        assertEquals(kalenderWoche, expectedWeather.getKalenderWoche());
        assertEquals("Weiden", expectedWeather.getStadt());
        assertEquals(1, expectedWeather.getTage().size());
        assertEquals(monday, expectedWeather.getTage().get(0));
    }
}

