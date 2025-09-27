package by.tishalovichm.mva.tts;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RequiredArgsConstructor
@Service
public class TtsService {

    private final ObjectMapper objectMapper;

    private record TtsInfo(
        String input,
        String model
    ) {
        TtsInfo(String input) {
            this(input, "kitten-tts");
        }
    }

    @SneakyThrows
    public byte[] getWav(String text) {
        try (HttpClient client = HttpClient.newBuilder().build()) {
            URI uri = URI.create("http://localhost:8080/tts");

            String requestBody = objectMapper.writeValueAsString(new TtsInfo(text));

            HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .uri(uri)
                .build();

            HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
            return response.body();
        }
    }

}
