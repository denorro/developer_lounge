package com.denorro;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.denorro.model.Developer;
import com.denorro.model.Technology;
import com.denorro.service.DeveloperService;
import com.denorro.service.TechnologyService;

@SpringBootApplication
public class DeveloperLoungeApplication implements CommandLineRunner{
	
	private DeveloperService devService;
	private TechnologyService techService;
	
	public DeveloperLoungeApplication(DeveloperService devService, TechnologyService techService) {
		this.devService = devService;
		this.techService = techService;
	}

	public static void main(String[] args) {
		SpringApplication.run(DeveloperLoungeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Developer denorro = new Developer("Denorro", "Stallworth", "denorro", "United States", true);
		Developer ace = new Developer("Ace", "Stallworth", "spaceace", "United States", false);
		Developer blake = new Developer("Blake", "Stallworth", "spaceblake", "United States", false);
		Developer kinley = new Developer("Kinley", "Stallworth", "kinley", "United States", true);
		
		Technology react = new Technology("React.js", "https://reactjs.org/", "https://github.com/facebook/react/");
		Technology reactnative = new Technology("React Native", "https://facebook.github.io/react-native/", "https://github.com/facebook/react-native");
		Technology angular = new Technology("Angular", "https://angular.io/", "https://github.com/angular/angular");
		Technology vue = new Technology("Vue.js", "https://vuejs.org/", "https://github.com/vuejs/vue");
		
		techService.saveTechnology(react);
		techService.saveTechnology(reactnative);
		techService.saveTechnology(angular);
		techService.saveTechnology(vue);
		
		denorro.addTechnologyToDeveloper(react);
		denorro.addTechnologyToDeveloper(reactnative);
		denorro.addTechnologyToDeveloper(angular);
		devService.saveDeveloper(denorro);
		
		ace.addTechnologyToDeveloper(react);
		ace.addTechnologyToDeveloper(reactnative);
		devService.saveDeveloper(ace);
		
		blake.addTechnologyToDeveloper(react);
		blake.addTechnologyToDeveloper(angular);
		devService.saveDeveloper(blake);
		
		kinley.addTechnologyToDeveloper(react);
		kinley.addTechnologyToDeveloper(vue);
		kinley.addTechnologyToDeveloper(angular);
		devService.saveDeveloper(kinley);
	}
}
