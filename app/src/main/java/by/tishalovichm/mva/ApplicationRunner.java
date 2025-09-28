package by.tishalovichm.mva;

import by.tishalovichm.mva.sound.SoundPlayer;
import by.tishalovichm.mva.tts.TtsService;
import by.tishalovichm.mva.weather.CurrentWeatherInfo;
import by.tishalovichm.mva.weather.WeatherRetriever;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApplicationRunner {

    private final SoundPlayer soundPlayer;

    private final WeatherRetriever weatherRetriever;

    private final TtsService ttsService;

    @SneakyThrows
    public void run() {
        CurrentWeatherInfo weatherInfo = weatherRetriever.getWeather();

        int temperature = (int) Math.ceil(weatherInfo.temperature() - 0.5);
        log.info("Retrieved temperature = {}", temperature);

        byte[] bytes = ttsService.getWav("The temperature is %d degrees Celsius.".formatted(temperature));

        soundPlayer.play(new ByteArrayInputStream(bytes));
    }
}
