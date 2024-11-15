package com.ryan.lacakio_test.Repository;

import com.ryan.lacakio_test.Model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    @Query("select c from City c where name like '%:query%' or countryCode like '%:query%' or fipsCode like '%:query%' or countyCode like '%:query%'")
    List<City> findDataByQuery(
            @Param("query") String query
    );
}
