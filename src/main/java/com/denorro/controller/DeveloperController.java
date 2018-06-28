package com.denorro.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

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

import com.denorro.model.Developer;
import com.denorro.model.ResultingResponse;
import com.denorro.service.DeveloperService;

@RestController
public class DeveloperController {
	
	private final DeveloperService devService;
	
	public DeveloperController(DeveloperService developerService) {
		this.devService = developerService;
	}
	
	@GetMapping("/developers")
	public ResponseEntity<Iterable<Developer>> getAllDevelopers(){
		return new ResponseEntity<>(devService.findAllDevelopers(), HttpStatus.OK);
	}
	
	@PostMapping("/developers")
	public ResponseEntity<?> saveDeveloper(@Valid @RequestBody Developer developer, BindingResult results) {
		if(results.hasFieldErrors()) {
			String errorMessage = null;
			for(FieldError error : results.getFieldErrors()) {
				errorMessage += error.getDefaultMessage() + "\n";
			}
			return new ResponseEntity<>(new ResultingResponse(500, errorMessage), HttpStatus.BAD_REQUEST);
		}
		Developer newDeveloper = devService.saveDeveloper(developer);
		return new ResponseEntity<>(newDeveloper, HttpStatus.CREATED);
	}
	
	@GetMapping("/developers/{usernameOrId}")
	public ResponseEntity<?> findDeveloper(@PathVariable String usernameOrId){
		Optional<Developer> dev = null;
		try {
			Long id = Long.parseLong(usernameOrId);
			dev = devService.findDeveloperById(id);
		}
		catch(NumberFormatException ex) {
			dev = devService.findDeveloperByUsername(usernameOrId);
		}
		if(!dev.isPresent()) {
			ResultingResponse error = new ResultingResponse(404, "Developer Not Found");
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(dev, HttpStatus.FOUND);
	}
	
	@PutMapping("/developers/{id}")
	public ResponseEntity<?> updateDeveloper(@Valid @RequestBody Developer developer, @PathVariable("id") Long id){
		Optional<Developer> tempDev = devService.findDeveloperById(id);
		if(tempDev.isPresent()) {
			if(developer.getCity() != null) {
				tempDev.get().setCity(developer.getCity());
			}
			if(developer.getState() != null) {
				tempDev.get().setState(developer.getState());
			}
			if(developer.getCountry() != null) {
				tempDev.get().setCountry(developer.getCountry());
			}
			if(developer.getFirstName() != null) {
				tempDev.get().setFirstName(developer.getFirstName());
			}
			if(developer.getLastName() != null) {
				tempDev.get().setLastName(developer.getLastName());
			}
			if(!developer.isAvailable()) {
				tempDev.get().setAvailable(developer.isAvailable());
			}
			if(developer.getRate() != null) {
				tempDev.get().setRate(developer.getRate());
			}
			devService.updateDeveloper(tempDev.get());
			return new ResponseEntity<>(tempDev, HttpStatus.OK);
		}
		return new ResponseEntity<>(new ResultingResponse(404, "Developer Not Found"), HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/developers/{id}")
	public ResponseEntity<?> deleteDeveloperById(@PathVariable("id") Long id){
		devService.deleteDeveloper(id);
		return new ResponseEntity<>(new ResultingResponse(200, "Developer Deleted!"), HttpStatus.OK);
	}
	
	@GetMapping("/developers/u/{firstname},{lastname}")
	public ResponseEntity<?> findDeveloperByFirstNameLastName(@PathVariable String firstname, @PathVariable String lastname){
		Optional<Iterable<Developer>> devs = devService.findByFirstNameLastName(firstname.toUpperCase(), lastname.toUpperCase());
		if(!devs.isPresent()) {
			return new ResponseEntity<>(new ResultingResponse(404, "No Developers By That Name!"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(devs, HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/developers/{devId}/tech/{techId}")
	public ResponseEntity<?> addTechnologyToDeveloper(@PathVariable("devId") Long devId, @PathVariable("techId") Long techId){
		Developer dev = devService.addTechnologyToDeveloper(devId, techId);
		if(null == dev) {
			return new ResponseEntity<>(new ResultingResponse(400, "Failed to add Technology to Developer"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new ResultingResponse(200, "Added Technology to Developer Successfully"), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/developers/{devId}/tech/{techId}")
	public ResponseEntity<?> removeTechnologyFromDeveloper(@PathVariable("devId") Long devId, @PathVariable("techId") Long techId){
		Developer dev = devService.addTechnologyToDeveloper(devId, techId);
		if(null == dev) {
			return new ResponseEntity<>(new ResultingResponse(400, "Failed To Remove Technology From Developer!"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new ResultingResponse(200, "Removed Technology From Developer Successfully"), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/developers/tech/{skill}")
	public ResponseEntity<?> findDevelopersBySkill(@PathVariable("skill") String skill){
		Iterable<Developer> list = devService.findDeveloperBySkill(skill);
		if(null == list) {
			return new ResponseEntity<>(new ResultingResponse(404, "No Developers Found"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
}
