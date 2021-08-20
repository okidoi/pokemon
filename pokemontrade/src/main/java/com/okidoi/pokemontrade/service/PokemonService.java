package com.okidoi.pokemontrade.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.okidoi.pokemontrade.dao.ListsDao;
import com.okidoi.pokemontrade.dto.PokemonDTO;
import com.okidoi.pokemontrade.exception.ObjectNotFoundException;
import com.okidoi.pokemontrade.model.Pokemon;


@Service
public class PokemonService {
	
	@Autowired
	private ListsDao dao;
	
	public List<PokemonDTO> getPokemons(Pageable pageable){
	
		return dao.findAll(pageable).stream().map(PokemonDTO::create).collect(Collectors.toList());
		
		//Manualmente (sem Lambda)
		
		//	List<CarroDTO> list = new ArrayList<>();
		//	for (Carro c : carros) {
		//		list.add(new CarroDTO(c));
		//	}  
		
		//Converte para uma list de CarroDTO
		
		//O findAll retorna uma lista de carros.
		//Chamamos a o método stream pra mapear essa lista. 
		//Estamos percorrendo carro por carro (em CarroDTO:new) e criando um CarroDTO
		//Depois geramos uma nova lista de CarroDTO
		
	}


	
	public PokemonDTO insert(Pokemon pokemon) {
		
		Assert.isNull(pokemon.getId(), "Fail to save register");
		PokemonDTO create = PokemonDTO.create(dao.save(pokemon));
		return create;		
	}	
	
	
	public List<PokemonDTO> search(String query) {
		
		return dao.findByNameContaining(query).stream().map(PokemonDTO::create).collect(Collectors.toList());
	
	}	
	
	public void delete(Long id) {
		
		dao.deleteById(id);
	
	}	
	
    public PokemonDTO update(Pokemon pokemon, Long id) {
        
    	//validate id
    	Assert.notNull(id,"Fail to update register");

        // search pokemon on database
        Optional<Pokemon> optional = dao.findById(id);
        
        if(optional.isPresent()) {
        
        	Pokemon db = optional.get();
            
        	// copy properties
            db.setName(pokemon.getName());
            db.setImg(pokemon.getImg());
            db.setNumber(pokemon.getNumber());

            // update pokemon
            dao.save(db);

            return PokemonDTO.create(db);
        } else {
            return null;
        }
    
    }	
	
    public PokemonDTO getPokemonById(Long id) {
		
		return dao.findById(id).map(PokemonDTO::create).orElseThrow(()-> new ObjectNotFoundException("pokemon not found")); //Usando Lambda

		//Opção sem Lambda
		
		//Optional<Pokemon> pokemon = rep.findById(id);
		//return pokemon.map(c -> Optional.of(new PokemonDTO(c))).orElse(null);
		
	}

	/*	
	

	public List<CarroDTO> getCarrosByTipo(String tipo, Pageable pageable) {
		
		return rep.findByTipo(tipo,pageable).stream().map(CarroDTO::create).collect(Collectors.toList());
	}


	

	


	public List<CarroDTO> search(String query) {
	
		return rep.findByNomeContaining(query).stream().map(CarroDTO::create).collect(Collectors.toList());
	
	}*/

}
