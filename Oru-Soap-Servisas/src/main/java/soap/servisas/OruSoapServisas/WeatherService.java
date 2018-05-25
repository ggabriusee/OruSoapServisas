/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soap.servisas.OruSoapServisas;

/**
 *
 * @author gabrius
 */
import com.locations.Weather;
import com.locations.FootballTeams;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class WeatherService {
    private static final Map<Integer, Weather> locations= new HashMap<>();
    private int counter = 1;
    
    @PostConstruct
    public void initialize(){
        Weather cd = new Weather(1, 0, "Vilnius", "2018-02-02", new FootballTeams(1));
	Weather cd1 = new Weather(2, 15, "Kaunas", "2018-04-02", new FootballTeams(2));
	Weather cd2 = new Weather(3, -20, "Vilnius", "2018-12-30", new FootballTeams());
        
        locations.put(cd.getId(), cd);
        locations.put(cd1.getId(), cd1);
        locations.put(cd2.getId(), cd2);
    }
    
    public Weather getWeather(int id){
        return locations.get(id);
    }

    public void createWeather(Weather wt, boolean isPresent){
       if(isPresent) {
           do {
               wt.setId(counter);
           }while(locations.containsKey(counter++));
       }
       locations.put(wt.getId(),wt);
    }

    public void updateWeather(Weather wt){
        Weather wee = locations.get(wt.getId());
        if(wt.getCity() != null)
            wee.setCity(wt.getCity());
        if(Integer.valueOf(wt.getTemperature()) != null) wee.setTemperature(wt.getTemperature());
        if(wt.getDate() != null) wee.setDate(wt.getDate());

    }

    public Map<Integer, Weather> getAllWeather(){
        return this.locations;
    }

    public void deleteWeather(int id){
        locations.remove(id);
    }

    public boolean isPresent(int id ){
        return locations.containsKey(id);
    }
}
