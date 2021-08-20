package com.okidoi.pokemontrade.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.okidoi.pokemontrade.model.Pokemon;
import com.okidoi.pokemontrade.util.PaginacaoUtil;



@Repository
public class PokemonDaoImpl extends AbstractDao<Pokemon, Long> implements PokemonDao {

	public PaginacaoUtil<Pokemon> buscaPaginada(int paginaSolicitada, String direcao){
		
		int tamanho = 5;
		int inicio = (paginaSolicitada -1) * tamanho; // 0*5 = 0 , 1*5 = 5, 2*5 = 10 
		List<Pokemon> listaPokemons = getEntityManager()
				.createQuery("select c from pokemon c order by c.nome " + direcao, Pokemon.class)
				.setFirstResult(inicio)
				.setMaxResults(tamanho)
				.getResultList();
		
		long totalRegistros = count();
		long totalDePaginas = (totalRegistros + (tamanho - 1)) / tamanho;
		
		return new PaginacaoUtil<>(tamanho, paginaSolicitada, totalDePaginas, direcao, listaPokemons);
	}
	
	public long count() {
		return getEntityManager()
				.createQuery("select count(*) from pokemon", Long.class)
				.getSingleResult();
	}
}
