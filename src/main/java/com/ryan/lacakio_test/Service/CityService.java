package com.ryan.lacakio_test.Service;

import com.ryan.lacakio_test.Converter.CityConverter;
import com.ryan.lacakio_test.Dto.CityDto;
import com.ryan.lacakio_test.Model.City;
import com.ryan.lacakio_test.Repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CityService{
    @Autowired
    CityRepository cityRepository;
    CityConverter cityConverter;

    public List<CityDto> suggestion(
            String query,
            Double latitude,
            Double longitude
    ) {
        List<City> data;
        data = cityRepository.findAll();

        List<CityDto> dtoData = new ArrayList<>();

        for(City city : data){
            CityDto dto = cityConverter.toDto(city);

            //For getting score data
            dto.setScore(getSuggestionScore(query, latitude, longitude, dto));
            dtoData.add(dto);
        }
        return dtoData;
    }

    //Get score by comparing query with suggestion, diff in latitude and longitude
    double getSuggestionScore(String query, Double latitude, Double longitude, CityDto dto){
        //each data will get base score of 100, that will be subtracted for each scoring criteria
        int lengthOfQuery = query.length();

        double baseScore = 100;

        double namePenalty = getQueryLengthPenaltyScore(lengthOfQuery, dto.getName().length());
        double countryCodePenalty = getQueryLengthPenaltyScore(lengthOfQuery, dto.getName().length());
        double fipsCodePenalty = getQueryLengthPenaltyScore(lengthOfQuery, dto.getName().length());
        double countyCodePenalty = getQueryLengthPenaltyScore(lengthOfQuery, dto.getName().length());

        double totalQueryLengthDiffPenalty = namePenalty + countryCodePenalty + fipsCodePenalty + countyCodePenalty;



        baseScore = baseScore - totalQueryLengthDiffPenalty;
        return (float) baseScore / 100;
    }

    //Get Score penalty base on string length difference from the inputted query and string length of city name, country code, fips code and county code
    double getQueryLengthPenaltyScore(int lengthOfQuery, int attributeLength){
        if(lengthOfQuery == attributeLength){
            return 0;
        }
        double baseLengthDifferencePenalty = 0.5;
        int lengthDifference = Math.abs(lengthOfQuery - attributeLength);
        return baseLengthDifferencePenalty * lengthDifference;
    }

    double getDistanceDifference(Double latitude, Double longitude, CityDto dto){
        if(Objects.equals(latitude, longitude)){
            return 0;
        }
        double baseLengthDifferencePenalty = 0.5;
        int lengthDifference = 0;
        return baseLengthDifferencePenalty * lengthDifference;
    }

}
