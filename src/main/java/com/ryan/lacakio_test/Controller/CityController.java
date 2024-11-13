package com.ryan.lacakio_test.Controller;

import com.ryan.lacakio_test.Dto.ResponseDto;
import com.ryan.lacakio_test.Model.City;
import com.ryan.lacakio_test.Service.CityService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class CityController {
    @Autowired
    CityService cityService;

    @GetMapping("/suggestions")
    public ResponseDto<List<City>> test(
            @RequestParam("q") String query,
            @RequestParam("latitude") @Nullable Double latitude,
            @RequestParam("longitude") @Nullable Double longitude
    ){
        ResponseDto<List<City>> response = new ResponseDto<>();
        try {
            List<City> data = cityService.suggestion();
            response.setData(data);
            response.setStatus(true);
            response.setMessage("Showing Suggestion");
        } catch (Exception e) {
            response.setStatus(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }
}
