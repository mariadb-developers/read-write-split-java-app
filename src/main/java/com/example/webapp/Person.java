package com.example.webapp;

import javax.annotation.Nonnull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Entity
@Data
@NoArgsConstructor
public class Person {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
 
	@Nonnull
	private String name;

	private String creditCardNumber;

	@Column(insertable = false, updatable = false)
	private Integer writeServerId;
	
	@Column(insertable = false, updatable = false)
	private Integer readServerId;

}
