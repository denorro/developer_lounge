package com.denorro.service;

import java.util.Optional;
import java.util.Set;
import com.denorro.model.Technology;

public interface TechnologyService {
	
	Iterable<Technology> findAllTechnologies();
	
	Technology saveTechnology(Technology technology);
	
	Optional<Technology> findTechnologyById(Long id);
	
	void deleteTechnology(Long id);
	
	Optional<Iterable<Technology>> findTechnologyByName(String skill);
}
