package com.CodingCalendar.api.entities;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;


@Service
public interface ContestRepository extends CrudRepository<Contest, Serializable> {

    @Query(value = "SELECT * FROM competitivecalendarnew t WHERE t.platform like :platform AND t.phase like :phase",
             nativeQuery=true )
    public List<Contest> findByPlatform_and_Phase(@Param("platform") String platform,@Param("phase") String phase);
	

}
