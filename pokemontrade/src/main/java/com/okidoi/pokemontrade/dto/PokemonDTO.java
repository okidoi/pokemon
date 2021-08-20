package com.okidoi.pokemontrade.dto;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.okidoi.pokemontrade.model.Pokemon;

import lombok.Data;


@Data //Lombok
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PokemonDTO {
	
	
	private Long id;
	private String number;
	private String name;
	private String img;
	
	public static PokemonDTO create(Pokemon p) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(p,  PokemonDTO.class);
	}
	
}


