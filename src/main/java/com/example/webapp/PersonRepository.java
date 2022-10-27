package com.example.webapp;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
 
public interface PersonRepository extends JpaRepository<Person, Long> {
 
	@Transactional(propagation = Propagation.SUPPORTS)
	Optional<Person> findById(Long id);
 
	@Transactional(propagation = Propagation.SUPPORTS)
	Page<Person> findAll(Pageable pageable);
 
	@Transactional(propagation = Propagation.SUPPORTS)
	long count();

}
