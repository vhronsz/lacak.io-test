package com.ryan.lacakio_test.Converter;

import com.ryan.lacakio_test.Dto.CityDto;
import com.ryan.lacakio_test.Model.City;
import org.springframework.beans.BeanUtils;

//Convert City Entity to DTO
public class CityConverter {
    public CityDto toDto(City entity) {
        CityDto dto = new CityDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}
