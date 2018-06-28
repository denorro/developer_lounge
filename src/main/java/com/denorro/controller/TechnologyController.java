package com.denorro.controller;

import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.denorro.model.ResultingResponse;
import com.denorro.model.Technology;
import com.denorro.service.TechnologyService;

@RestController
public class TechnologyController {
	
	private final TechnologyService techService;

	public TechnologyController(TechnologyService techService) {
		this.techService = techService;
	}
	
	@GetMapping("/technologies")
	public ResponseEntity<Iterable<Technology>> findAllTechnologies(){
		return new ResponseEntity<>(techService.findAllTechnologies(), HttpStatus.OK);
	}
	
	@PostMapping("/technologies")
	public ResponseEntity<?> saveTechnology(@Valid @RequestBody Technology technology, BindingResult results) {
		if(results.hasFieldErrors()) {
			String errorMessage = null;
			for(FieldError error : results.getFieldErrors()) {
				errorMessage += error.getDefaultMessage() + "\n";
			}
			return new ResponseEntity<>(new ResultingResponse(500, errorMessage), HttpStatus.BAD_REQUEST);
		}
		Technology newTech= techService.saveTechnology(technology);
		return new ResponseEntity<>(newTech, HttpStatus.CREATED);
	}
	
	@GetMapping("/technologies/{techNameOrId}")
	public ResponseEntity<?> findTechnology(@PathVariable String techNameOrId){
		Optional<Technology> tech = null;
		Optional<Iterable<Technology>> techList = null;
		try {
			Long id = Long.parseLong(techNameOrId);
			tech = techService.findTechnologyById(id);
			if(tech.isPresent()) {
				return new ResponseEntity<>(tech, HttpStatus.FOUND);
			}
		}
		catch(NumberFormatException ex) {
			techList = techService.findTechnologyByName(techNameOrId);
			if(techList.isPresent()) {
				return new ResponseEntity<>(techList, HttpStatus.FOUND);
			}
		}
		return new ResponseEntity<>(new ResultingResponse(404, "Technology Not Found"), HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/technologies/{id}")
	public ResponseEntity<?> updateTechnology(@Valid @RequestBody Technology technology, @PathVariable("id") Long id){
		Optional<Technology> tempTech = techService.findTechnologyById(id);
		if(tempTech.isPresent()) {
			if(technology.getSkill() != null) {
				tempTech.get().setSkill(technology.getSkill());
			}
			if(technology.getLink() != null) {
				tempTech.get().setLink(technology.getLink());
			}
			if(technology.getVersionControlRepo() != null) {
				tempTech.get().setVersionControlRepo(technology.getVersionControlRepo());
			}
			Technology t = techService.saveTechnology(tempTech.get());
			if(t == null) {
				return new ResponseEntity<>(new ResultingResponse(400, "Couldn't Update Technology"), HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(tempTech, HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResultingResponse(404, "Technology Not Found"), HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/technologies/{id}")
	public ResponseEntity<?> deleteTechnologyById(@PathVariable("id") Long id){
		techService.deleteTechnology(id);
		return new ResponseEntity<>(new ResultingResponse(200, "Technology Deleted!"), HttpStatus.OK);
	}
}
