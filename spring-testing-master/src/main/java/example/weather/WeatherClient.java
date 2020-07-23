package example.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class WeatherClient {

    private static final String LATITUDE = "53.5511";
    private static final String LONGITUDE = "9.9937";
    private final RestTemplate restTemplate;
    private final String weatherServiceUrl;
    private final String weatherServiceApiKey;

    @Autowired
    public WeatherClient(final RestTemplate restTemplate,
                         @Value("${weather.url}") final String weatherServiceUrl,
                         @Value("${weather.api_secret}") final String weatherServiceApiKey) {
        this.restTemplate = restTemplate;
        this.weatherServiceUrl = weatherServiceUrl;
        this.weatherServiceApiKey = weatherServiceApiKey;
    }

    public Optional<WeatherResponse> fetchWeather() {
        String url = String.format("%s/data/2.5/onecall?appid=%s&lat=%s&lon=%s", weatherServiceUrl, weatherServiceApiKey, LATITUDE, LONGITUDE);

        try {
            Optional<WeatherResponse> response = Optional.ofNullable(restTemplate.getForObject(url, WeatherResponse.class));
            response.get().getCurrent();
            return response;
        } catch (RestClientException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
