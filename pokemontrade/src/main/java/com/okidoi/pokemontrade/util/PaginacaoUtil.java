package com.okidoi.pokemontrade.util;

import java.util.List;

public class PaginacaoUtil <T> {
	
	private int tamanho;  //numero de linhas que teremos na páginas
	private int pagina;   //numero da pagina atual selecionada pelo cliente
	private long totalDePaginas;
	private String direcao;   //direção do ordenamento
	private List<T> registros;
	
	
	public PaginacaoUtil(int tamanho, int pagina, long totalDePaginas, String direcao, List<T> registros) {
		super();
		this.tamanho = tamanho;
		this.pagina = pagina;
		this.totalDePaginas = totalDePaginas;
		this.direcao = direcao;
		this.registros = registros;
	}
	public String getDirecao() {
		return direcao;
	}
	public void setDirecao(String direcao) {
		this.direcao = direcao;
	}
	public int getTamanho() {
		return tamanho;
	}
	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
	public int getPagina() {
		return pagina;
	}
	public void setPagina(int pagina) {
		this.pagina = pagina;
	}
	public long getTotalDePaginas() {
		return totalDePaginas;
	}
	public void setTotalDePaginas(long totalDePaginas) {
		this.totalDePaginas = totalDePaginas;
	}
	public List<T> getRegistros() {
		return registros;
	}
	public void setRegistros(List<T> registros) {
		this.registros = registros;
	}
	
	
	
	
	

}
