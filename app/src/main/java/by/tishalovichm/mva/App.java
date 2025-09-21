package by.tishalovichm.mva;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class App {

    public static void playSound(InputStream inputStream) throws Exception {
        Clip clip = AudioSystem.getClip();
        clip.open(AudioSystem.getAudioInputStream(inputStream));
        clip.addLineListener((v) -> {
            if (v.getType() == LineEvent.Type.STOP) {
                synchronized (App.class) {
                    App.class.notify();
                }
            }
        });

        clip.start();
        synchronized (App.class) {
            App.class.wait();
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        var objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        var ttsService = new TtsService(objectMapper);

        WeatherRetriever weatherRetriever = new WeatherRetriever(objectMapper);
        WeatherInfo weatherInfo = weatherRetriever.getWeather();

        int temperature = (int) Math.ceil(weatherInfo.current().tempC() - 0.5);
        System.out.printf("Temperature = %d\n", temperature);

        byte[] bytes = ttsService.getWav("The temperature is %d degrees Celsius.".formatted(temperature));

        playSound(new ByteArrayInputStream(bytes));
    }
}
