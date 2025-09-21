package by.tishalovichm.mva;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WeatherInfo(
    Temp current
) {
    public record Temp(
        @JsonProperty("temp_c") float tempC
    ) {
    }
}
