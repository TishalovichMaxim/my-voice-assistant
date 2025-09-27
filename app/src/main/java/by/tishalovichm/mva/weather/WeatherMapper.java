package by.tishalovichm.mva.weather;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface WeatherMapper {

    @Mapping(target = "temperature", source = "current.tempC")
    CurrentWeatherInfo fromDto(CurrentWeatherApiInfo i);
}
