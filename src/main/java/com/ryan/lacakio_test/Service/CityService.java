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
        data = cityRepository.findDataByQuery(query);

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
        double distanceDiffPenalty = getDistanceDifferencePenalty(latitude, longitude, dto);
        baseScore = baseScore - (totalQueryLengthDiffPenalty + distanceDiffPenalty);

        return (float) baseScore / 100;
    }

    //Get Score penalty base on string length difference from the inputted query and string length of city name, country code, fips code and county code
    double getQueryLengthPenaltyScore(int lengthOfQuery, int attributeLength){
        if(lengthOfQuery == attributeLength){
            return 0;
        }
        double baseScorePenalty = 0.5;
        int lengthDifference = Math.abs(lengthOfQuery - attributeLength);
        return baseScorePenalty * lengthDifference;
    }

    //Get score penalty base on difference between latitude and longitude value
    double getDistanceDifferencePenalty(Double latitude, Double longitude, CityDto dto){
        if(Objects.isNull(latitude) || Objects.isNull(longitude)){
            return 0;
        }
        double baseScorePenalty = 1.5;

        double latitudeDifference = Math.abs(latitude - dto.getLatitude());
        double longitudeDifference = Math.abs(longitude - dto.getLongitude());
        double totalDifference = latitudeDifference + longitudeDifference;

        return baseScorePenalty * totalDifference;
    }
}
