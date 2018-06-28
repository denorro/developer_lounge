package com.denorro.repository;

import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.denorro.model.Technology;

public interface TechnologyRepository extends CrudRepository<Technology, Long> {
	
	@Query(value="select * from technology t where Upper(t.skill) like %:skill%", nativeQuery=true)
	Optional<Iterable<Technology>> findTechnologyByName(@Param("skill") String skill);

}
