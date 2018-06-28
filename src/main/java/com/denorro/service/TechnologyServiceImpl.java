package com.denorro.service;

import java.util.Optional;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.denorro.model.Technology;
import com.denorro.repository.TechnologyRepository;

@Service
@Transactional
public class TechnologyServiceImpl implements TechnologyService {
	
	private TechnologyRepository repo;
	
	public TechnologyServiceImpl(TechnologyRepository repo) {
		this.repo = repo;
	}

	@Override
	public Technology saveTechnology(Technology technology) {
		return repo.save(technology);
	}

	@Override
	public Optional<Technology> findTechnologyById(Long id) {
		return repo.findById(id);
	}

	@Override
	public void deleteTechnology(Long id) {
		repo.deleteById(id);
	}

	@Override
	public Iterable<Technology> findAllTechnologies() {
		return repo.findAll();
	}

	@Override
	public Optional<Iterable<Technology>> findTechnologyByName(String skill) {
		return repo.findTechnologyByName(skill.toUpperCase());
	}

}
