package com.ryan.lacakio_test.Controller;

import com.ryan.lacakio_test.Dto.CityDto;
import com.ryan.lacakio_test.Dto.ResponseDto;
import com.ryan.lacakio_test.Model.City;
import com.ryan.lacakio_test.Service.CityService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class CityController {
    @Autowired
    CityService cityService;

    @GetMapping("/suggestions")
    public ResponseEntity<ResponseDto<List<CityDto>>> test(
            @RequestParam("q") String query,
            @RequestParam("latitude") @Nullable Double latitude,
            @RequestParam("longitude") @Nullable Double longitude
    ){
        ResponseDto<List<CityDto>> response = new ResponseDto<>();
        try {
            List<CityDto> data = cityService.suggestion(query, latitude, longitude);
            response.setData(data);
            response.setStatus(true);
            response.setMessage("Showing Suggestion");
        } catch (Exception e) {
            response.setStatus(false);
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.ok(response);
    }
}
