package com.denorro.service;

import java.util.Collection;
import java.util.Optional;

import com.denorro.model.Developer;

public interface DeveloperService {
	
	Iterable<Developer> findAllDevelopers();
	
	Developer saveDeveloper(Developer developer);
	
	Developer updateDeveloper(Developer developer);
	
	Optional<Developer> findDeveloperById(Long id);
	
	void deleteDeveloper(Long id);
	
	public Optional<Developer> findDeveloperByUsername(String username);
	
	public Optional<Iterable<Developer>> findByFirstNameLastName(String firstname, String lastname);
	
	public Collection<Developer> findDeveloperBySkill(String skill);
	
	public Developer addTechnologyToDeveloper(Long devId, Long techId);
}
