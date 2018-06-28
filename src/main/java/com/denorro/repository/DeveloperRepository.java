package com.denorro.repository;

import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.denorro.model.Developer;

@Repository
public interface DeveloperRepository extends CrudRepository<Developer, Long>{
	
	public Optional<Developer> findByUsernameIgnoreCase(String username);
	
	@Query("select d from Developer d where Upper(d.firstName) = :firstName and Upper(d.lastName) = :lastName")
	public Optional<Iterable<Developer>> findByFirstNameLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);
	
	@Query(value="select * from Developer d, Technology t, developer_technology dt where d.id = dt.developer_id and t.id = dt.technology_id and t.id = :techId", nativeQuery = true)
	public Iterable<Developer> findBySkill(@Param("techId") Long techId);
}
