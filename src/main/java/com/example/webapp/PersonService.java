package com.example.webapp;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonService implements CrudListener<Person> {

	private final PersonRepository repository;

	@Override
	public Collection<Person> findAll() {
		return repository.findAll();
	}

	@Override
	public Person add(Person person) {
		return repository.save(person.getId(), person.getName(), person.getCreditCardNumber());
	}

	@Override
	public Person update(Person person) {
		return repository.save(person.getId(), person.getName(), person.getCreditCardNumber());
	}

	@Override
	public void delete(Person person) {
		repository.delete(person);
	}

}
