package service;
import bean.WeatherResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.ResponseBody;
import model.Weather;
import org.hibernate.mapping.List;

import java.io.IOException;

public class WeatherService {
    public static final String HTTPS_API_OPENWEATHERMAP_ORG_DATA_2_5_WEATHER = "https://api.openweathermap.org/data/2.5/weather";
    private static String FIXED_APPIKEY = "7edda0bea071419a1e1da9c91ac2d4c6";

    public WeatherResponse getLiveWeatherValues(Double lat, Double lon) throws IOException {
        OkHttpClient client = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();

        Request request = new Request.Builder()
                .url(HTTPS_API_OPENWEATHERMAP_ORG_DATA_2_5_WEATHER + "?lat=" + lat + "&lon=" + lon + "&APPID=" + FIXED_APPIKEY)
                .method("GET", null)
                .build();
        ResponseBody responseBody = client.newCall(request).execute().body();
        return objectMapper.readValue(responseBody.string(), WeatherResponse.class);
    }

}