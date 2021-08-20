package com.okidoi.pokemontrade.dao;

import java.util.List;

import com.okidoi.pokemontrade.model.Pokemon;
import com.okidoi.pokemontrade.util.PaginacaoUtil;


public interface PokemonDao {

    void save(Pokemon pokemon );

    void update(Pokemon pokemon);

    void delete(Long id);

    Pokemon findById(Long id);

    List<Pokemon> findAll();
    
    PaginacaoUtil<Pokemon> buscaPaginada(int paginaSolicitada, String direcao);
}
