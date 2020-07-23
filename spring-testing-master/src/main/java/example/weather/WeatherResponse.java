package example.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "current"
})

public class WeatherResponse {

    @JsonProperty("current")
    private Current current;

    public WeatherResponse() {
    }

    public WeatherResponse(Current current) {
        this.current = current;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherResponse response = (WeatherResponse) o;
        return Objects.equals(current, response.current);
    }

    @Override
    public int hashCode() {
        return Objects.hash(current);
    }

    public static String getSummary(WeatherResponse weatherResponse) {
        return weatherResponse.getCurrent().weather.get(0).main;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "weather"
    })
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Current {

        @JsonProperty("weather")
        public List<Weather> weather;

        public Current(){

        }
        public Current(List<Weather> weather) {
            this.weather = weather;
        }

        public List<Weather> getWeather() {
            return weather;
        }

        public void setWeather(List<Weather> weather) {
            this.weather = weather;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Current current = (Current) o;
            return Objects.equals(weather, current.weather);
        }

        @Override
        public int hashCode() {
            return Objects.hash(weather);
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Weather {

        @JsonProperty("main")
        public String main;

        public Weather(){

        }

        public Weather(String main) {
            this.main = main;
        }

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Weather weather = (Weather) o;
            return Objects.equals(main, weather.main);
        }

        @Override
        public int hashCode() {
            return Objects.hash(main);
        }
    }

}
