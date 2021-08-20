package com.okidoi.pokemontrade.controller;


import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.okidoi.pokemontrade.dto.PokemonDTO;
import com.okidoi.pokemontrade.model.Pokemon;
import com.okidoi.pokemontrade.service.PokemonService;

//Evitar tratamento de exceção nesta classe. Quanto mais limpa for a classe melhor.
//O tratamento de Exceção fica na ExceptionConfig e a exceção do tipo IllegalArgumentException será convertida em 
//BadRequest

@RestController
@RequestMapping("/api/v1/pokemon")
public class PokemonController {
	
	@Autowired
	private PokemonService  service;

 	
 	
 	//Monta a URL até o caminho /id
    private URI getUri(Long id) {
    
    	return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }	
	
	@PostMapping
 	public ResponseEntity post(@RequestBody Pokemon pokemon) {
		
			PokemonDTO dto = service.insert(pokemon);
			URI location = getUri(dto.getId());
			return ResponseEntity.created(location).build();	//In case of success returns Status 201 (Created)  
	}
	
	
	
	@PutMapping("/{id}")
	public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Pokemon pokemon) {
		
		pokemon.setId(id);
		PokemonDTO dto = service.update(pokemon, id);
		
		return dto != null?
				ResponseEntity.ok(dto) :
				ResponseEntity.notFound().build();
		
	}
		
	
	@GetMapping("/{id}")
	public ResponseEntity get(@PathVariable("id") Long id){

		PokemonDTO dto = service.getPokemonById(id);
		return ResponseEntity.ok(dto);
		
	}
	
	@GetMapping("/search")
	public ResponseEntity search(@RequestParam("query") String query) {
	    List<PokemonDTO> carros = service.search(query);
	    return carros.isEmpty() ?
	            ResponseEntity.noContent().build() :
	            ResponseEntity.ok(carros);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		
		service.delete(id);		
		return ResponseEntity.ok().build();
				
		
	}	
	
	@GetMapping()
	public ResponseEntity<List<PokemonDTO>> get(@RequestParam(value ="page", defaultValue = "0") Integer page, 
 											    @RequestParam(value ="size", defaultValue = "10") Integer size ) {
		
		return ResponseEntity.ok(service.getPokemons(PageRequest.of(page, size))); //PageRequest cria um Pageable
		
		//return new ResponseEntity<>(service.getPokemons(), HttpStatus.OK); //mesmo que a linha de cima, porém mais verboso
	}
	/*
	
	@GetMapping("/{id}")
	public ResponseEntity get(@PathVariable("id") Long id){

		CarroDTO carro = service.getCarroById(id);
		return ResponseEntity.ok(carro);
		
	}
	
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity getCarrosByTipo(@PathVariable("tipo") String tipo,
										  @RequestParam(value ="page", defaultValue = "0") Integer page, 
										  @RequestParam(value ="size", defaultValue = "10") Integer size ){
		
		List<CarroDTO> carros = service.getCarrosByTipo(tipo,PageRequest.of(page, size));
		
		return carros.isEmpty()?
				ResponseEntity.noContent().build() :
				ResponseEntity.ok(carros);
				
	}
	
	
	@GetMapping("/search")
	public ResponseEntity search(@RequestParam("query") String query) {
	    List<CarroDTO> carros = service.search(query);
	    return carros.isEmpty() ?
	            ResponseEntity.noContent().build() :
	            ResponseEntity.ok(carros);
	}
	
 	
 	//Monta a URL até o caminho /id
    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }	
	
		

	
*/
		
}
