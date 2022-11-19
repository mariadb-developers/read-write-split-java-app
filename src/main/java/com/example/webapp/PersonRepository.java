package com.example.webapp;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface PersonRepository extends JpaRepository<Person, Long> {

	@Override
	@Query(nativeQuery = true, value = """
			SELECT id, name, credit_card_number, write_server_id, @@server_id AS read_server_id
			FROM person
			WHERE id=:id
			""")
	@Transactional(propagation = Propagation.SUPPORTS)
	Optional<Person> findById(@Param("id") Long id);

	@Override
	@Query(nativeQuery = true, value = """
			SELECT id, name, credit_card_number, write_server_id, @@server_id AS read_server_id
			FROM person
			""")
	@Transactional(propagation = Propagation.SUPPORTS)
	List<Person> findAll();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	long count();

	@Query(nativeQuery = true, value = """
			INSERT INTO person(id, name, credit_card_number, write_server_id)
			VALUES(:id, :name, :creditCardNumber, @@server_id)
			ON DUPLICATE KEY UPDATE
				name = :name, credit_card_number = :creditCardNumber, write_server_id = @@server_id
			""")
	Person save(@Param("id") Long id, @Param("name") String name, @Param("creditCardNumber") String creditCardNumber);

}
