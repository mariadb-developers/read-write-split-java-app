package com.example.webapp;

import java.util.Collection;

import javax.transaction.NotSupportedException;

import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

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
		return repository.save(person);
	}

	@SneakyThrows
	public Person update(Person person) {
		throw new NotSupportedException("Not supported in this demo");
	}

	@Override
	public void delete(Person person) {
		repository.delete(person);
	}

}
