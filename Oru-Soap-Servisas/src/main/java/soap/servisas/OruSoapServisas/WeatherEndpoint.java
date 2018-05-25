/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soap.servisas.OruSoapServisas;

import com.locations.CreateWeatherRequest;
import com.locations.CreateWeatherResponse;
import com.locations.DeleteWeatherRequest;
import com.locations.DeleteWeatherResponse;
import com.locations.FootballTeams;
import com.locations.GetAllWeatherRequest;
import com.locations.GetAllWeatherResponse;
import com.locations.GetWeatherRequest;
import com.locations.GetWeatherResponse;
import com.locations.UpdateWeatherRequest;
import com.locations.UpdateWeatherResponse;
import com.locations.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


//import com.soap.servisas.OruSoapServisas.Exceptions.ResourceConflict409;
//import com.soap.servisas.OruSoapServisas.Exceptions.ResourceNotFoundException404;
//import com.soap.servisas.OruSoapServisas.Exceptions.ResourceRegisterFirstException;
//import javafx.util.Pair;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.soap.SoapHeaderException;
import sun.font.CreatedFontTracker;

import javax.xml.soap.SOAPException;
import java.util.Map;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author gabrius
 */
@Endpoint
public class WeatherEndpoint {
    
    @Autowired
    private WeatherService wtService;
    //private PostService postService = new PostService();
    private final String URL_NP = "com/locations";
   //private RestTemplate restTemplate = new RestTemplate();
   
   
    private final String KITAS_URL= "http://kitas:5000/football_teams";
    
    @PayloadRoot(namespace = "com/locations", localPart = "getWeatherRequest")
    @ResponsePayload
    public GetWeatherResponse getWeatherRequest(@RequestPayload GetWeatherRequest request){
        GetWeatherResponse response = new GetWeatherResponse();
        response.setWeather(wtService.getWeather(request.getId()));
        return response;
    }
    
    @PayloadRoot(namespace = URL_NP, localPart = "getAllDoctorRequest")
    @ResponsePayload
    public GetAllWeatherResponse getAllWeatherResponse(@RequestPayload GetAllWeatherRequest request){
       GetAllWeatherResponse response = new GetAllWeatherResponse();
       for(Map.Entry<Integer, Weather> entry : wtService.getAllWeather().entrySet()){
            response.getWeather().add(entry.getValue());
       }
       return response;
    }

    @PayloadRoot(namespace = URL_NP, localPart = "deleteWeatherRequest")
    @ResponsePayload
    public DeleteWeatherResponse deleteWeatherResponse(@RequestPayload DeleteWeatherRequest request){
        DeleteWeatherResponse response = new DeleteWeatherResponse();
        Weather wee = wtService.getWeather(request.getId());
        if(wee == null){}
            //throw new ResourceNotFoundException404("Doctor","id",request.getId());
        else
            wtService.deleteWeather(request.getId());
        return response;
    }


    @PayloadRoot(namespace = URL_NP, localPart = "createWeatherRequest")
    @ResponsePayload
    public CreateWeatherResponse createDoctorResponse(@RequestPayload CreateWeatherRequest request){
        CreateWeatherResponse response = new CreateWeatherResponse();
        Weather newWeather = new Weather();
        boolean isPresent = wtService.isPresent(request.getId());
        if(!isPresent)
            newWeather.setId(request.getId());
        newWeather.setTemperature(request.getTemperature());
        newWeather.setCity(request.getCity());
        newWeather.setDate(request.getDate());
        /*External service part*/
        /*
        FootballTeams ft = new FootballTeams("99354", "Andres Iniesta", "Italy", 1, "FC Barcelona", "Camp Nou" );
        HttpEntity<FootballTeams> newRequest = new HttpEntity<>(ft);
        try {
            ResponseEntity<FootballTeams> userResponse = restTemplate.exchange(KITAS_URL, HttpMethod.POST, newRequest, FootballTeams.class);
            newWeather.getFootballTeams().setID(userResponse.getBody().getID());
            newWeather.setFootballTeams(userResponse.getBody());
            wtService.createWeather(newWeather,isPresent);
            return response;
        }catch(RestClientException exc){
            if(exc.getMessage().equals("409 null")){
                throw new ResourceConflict409("FootballTeam","create problem",ft.getID());
            }else{
                wtService.createWeather(newWeather,isPresent);
                throw new SoapHeaderException("Internal Error");
            }
        }
*/
        return response;
    }

    @PayloadRoot(namespace = URL_NP, localPart = "updateDoctorRequest")
    @ResponsePayload
    public UpdateWeatherResponse updateWeatherResponse(@RequestPayload UpdateWeatherRequest request){
        UpdateWeatherResponse response = new UpdateWeatherResponse();
        Weather wee = wtService.getWeather(request.getId());
        if(wee == null){}
            //throw new ResourceNotFoundException404("Doctor","id",request.getId());
        else {
            wtService.updateWeather(new Weather(request.getId(),
                    request.getTemperature(),
                    request.getCity(),
                    request.getDate(),
                    new FootballTeams()));
        }
        return response;
    }
}
