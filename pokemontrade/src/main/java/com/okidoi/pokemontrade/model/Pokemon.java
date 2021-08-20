package com.okidoi.pokemontrade.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@EqualsAndHashCode
@Data
@Entity
@Table(name="pokemon")
public class Pokemon {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name ="number" , nullable = false)
	private String number;
	
	
	@NotBlank(message = "name is mandatory")
	@Size(max = 60, message = "name size maximum is 80")
	@Column(name ="name" , nullable = false , length = 80)
	private String name;

	@Column(name ="img" , nullable = true , length = 120)
	private String img;
	
	
	
}
