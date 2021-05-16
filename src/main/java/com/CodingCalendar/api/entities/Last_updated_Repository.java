package com.CodingCalendar.api.entities;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface Last_updated_Repository extends CrudRepository<Last_updated, Serializable> {

}
