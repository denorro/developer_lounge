package com.denorro.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.denorro.model.Developer;
import com.denorro.model.Technology;
import com.denorro.repository.DeveloperRepository;
import com.denorro.repository.TechnologyRepository;

@Service
@Transactional
public class DeveloperServiceImpl implements DeveloperService {
	
	private DeveloperRepository devRepo;
	private TechnologyRepository techRepo;
	
	public DeveloperServiceImpl(DeveloperRepository devRepo, TechnologyRepository techRepo) {
		this.devRepo = devRepo;
		this.techRepo = techRepo;
	}

	@Override
	public Iterable<Developer> findAllDevelopers() {
		return devRepo.findAll();
	}

	@Override
	public Developer saveDeveloper(Developer developer) {
		return devRepo.save(developer);
	}

	@Override
	public Optional<Developer> findDeveloperById(Long id) {
		return devRepo.findById(id);
	}

	@Override
	public void deleteDeveloper(Long id) {
		devRepo.deleteById(id);
	}

	@Override
	public Optional<Developer> findDeveloperByUsername(String username) {
		return devRepo.findByUsernameIgnoreCase(username);
	}

	@Override
	public Optional<Iterable<Developer>> findByFirstNameLastName(String firstname, String lastname) {
		return devRepo.findByFirstNameLastName(firstname, lastname);
	}

	@Override
	public Collection<Developer> findDeveloperBySkill(String skill) {
		Optional<Iterable<Technology>> tech = techRepo.findTechnologyByName(skill.toUpperCase());
		Collection<Developer> devList = new HashSet<>();
		if(tech.isPresent()) {
			for(Technology t : tech.get()) {
				Collection<Developer> devs = (Collection<Developer>) devRepo.findBySkill(t.getId());
				devList.addAll(devs);
			}
			return devList;
		}
		return null;
	}

	@Override
	public Developer updateDeveloper(Developer developer) {
		return devRepo.save(developer);
	}

	@Override
	public Developer addTechnologyToDeveloper(Long devId, Long techId) {
		Optional<Technology> t = techRepo.findById(techId);
		if(!t.isPresent()) {
			return null;
		}
		Optional<Developer> d = devRepo.findById(devId);
		d.get().addTechnologyToDeveloper(t.get());
		return devRepo.save(d.get());
	}

}
