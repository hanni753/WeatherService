package com.example.weatherservice;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WeatherController {

    @GetMapping("/wetter")
    public WeatherOfWeek getWeatherForWeek(@RequestParam("kalenderWoche") int kalenderWoche){
        WeatherOfWeek weatherOfWeek = new WeatherOfWeek();
        weatherOfWeek.setKalenderWoche(kalenderWoche);
        weatherOfWeek.setStadt("Weiden"); //PLZ?

        //Wetter API Aufruf mit den Parametern kalenderWoche und stadt/plz
        //else: kalenderWoche=10
        for (int i = 0; i < 7; i++){
            Day tag = new Day();

            switch (i){
                case 0: tag.setName("Montag");
                    tag.setDatum("2023-03-06");
                    break;
                case 1: tag.setName("Dienstag");
                    tag.setDatum("2023-03-07");
                    break;
                case 2: tag.setName("Mittwoch");
                    tag.setDatum("2023-03-08");
                    break;
                case 3: tag.setName("Donnerstag");
                    tag.setDatum("2023-03-09");
                    break;
                case 4: tag.setName("Freitag");
                    tag.setDatum("2023-03-10");
                    break;
                case 5: tag.setName("Samstag");
                    tag.setDatum("2023-03-11");
                    break;
                case 6: tag.setName("Sonntag");
                    tag.setDatum("2023-03-12");
                    break;
                default: break;
            }

            tag.setTemperatur("20 Grad");
            tag.setLuftdruck("10pa");
            weatherOfWeek.addDay(tag);
        }

        return weatherOfWeek;
    }

}