package by.tishalovichm.mva;

public record WeatherInfo(
    Temp current
) {
    public record Temp(float temp_c) {
    }
}
