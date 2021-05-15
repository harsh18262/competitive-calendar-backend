package com.CodingCalendar.api.entities;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.sun.xml.bind.v2.model.core.ID;

@Service
public interface ContestRepository extends CrudRepository<Contest, Serializable> {

	
	

}
