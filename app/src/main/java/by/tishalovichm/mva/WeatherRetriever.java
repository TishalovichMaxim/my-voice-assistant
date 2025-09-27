package by.tishalovichm.mva;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Properties;

@Service
public class WeatherRetriever {

    private final ObjectMapper objectMapper;

    private String apiKey;

    @SneakyThrows
    public WeatherRetriever(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;

        Properties prop = new Properties();

        try (InputStream input = WeatherRetriever.class.getClassLoader()
            .getResourceAsStream("creds.properties")) {

            prop.load(input);
        }

        apiKey = prop.getProperty("api-key");
    }

    @SneakyThrows
    public WeatherInfo getWeather() {
        try (HttpClient client = HttpClient.newBuilder().build()) {
            URI uri = URI.create(
                "https://api.weatherapi.com/v1/current.json?key=%s&q=Minsk".formatted(apiKey));

            HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .timeout(Duration.ofSeconds(20))
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String res = response.body();

            return objectMapper.readValue(res, WeatherInfo.class);
        }
    }

}
