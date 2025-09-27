package by.tishalovichm.mva;

import by.tishalovichm.mva.sound.SoundPlayer;
import by.tishalovichm.mva.tts.TtsService;
import by.tishalovichm.mva.weather.WeatherInfo;
import by.tishalovichm.mva.weather.WeatherRetriever;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;

@Component
@RequiredArgsConstructor
public class ApplicationRunner {

    private final SoundPlayer soundPlayer;

    @SneakyThrows
    public void run() {
        var objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        var ttsService = new TtsService(objectMapper);

        WeatherRetriever weatherRetriever = new WeatherRetriever(objectMapper);
        WeatherInfo weatherInfo = weatherRetriever.getWeather();

        int temperature = (int) Math.ceil(weatherInfo.current().tempC() - 0.5);
        System.out.printf("Temperature = %d\n", temperature);

        byte[] bytes = ttsService.getWav("The temperature is %d degrees Celsius.".formatted(temperature));

        soundPlayer.play(new ByteArrayInputStream(bytes));
    }
}
