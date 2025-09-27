package by.tishalovichm.mva.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CurrentWeatherApiInfo(
    Temp current
) {
    public record Temp(
        @JsonProperty("temp_c") float tempC
    ) {
    }
}
