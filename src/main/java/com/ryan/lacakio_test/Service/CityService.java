package com.ryan.lacakio_test.Service;

import com.ryan.lacakio_test.Converter.CityConverter;
import com.ryan.lacakio_test.Dto.CityDto;
import com.ryan.lacakio_test.Model.City;
import com.ryan.lacakio_test.Repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService{
    @Autowired
    CityRepository cityRepository;
    CityConverter cityConverter;

    public List<CityDto> suggestion(
            String query,
            Double latitude,
            Double longitude
    ) throws Exception {
        try{
            throw new Exception("asdasd");
        }catch (Exception e){
            throw e;
        }
//        List<City> data;
//
//        data = cityRepository.findDataWithScore(query, latitude, longitude);
//
//        List<CityDto> dtoData = new ArrayList<>();
//
//        for(City city : data){
//            CityDto dto = cityConverter.toDto(city);
//            dtoData.add(dto);
//        }
//        return dtoData;
    }

}
