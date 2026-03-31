package br.com.gadoboi.bean;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;


public abstract class RacaoDemonstracao{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key id;
	private Double precoMilho;
	private Double precoFareloSoja;
	private Double precoUreia;
	private Double precoSulfatoAmonio;
	private Double precoMisturaMineral;
	private Double qtdMilho;
	private Double qtdFareloSoja;
	private Double qtdUreia;
	private Double qtdSultafoAmonio;
	private Double qtdMisturaMineral;
	private Double valorTotal;
	
	
	
	
	
	
	
	
	public Double calculaTotal(){
		valorTotal = (getPrecoMilho() * getQtdMilho()) + 
					 (getPrecoFareloSoja() * getQtdFareloSoja()) +
					 (getPrecoMisturaMineral() * getQtdMisturaMineral()) +
					 (getPrecoSulfatoAmonio() * getQtdSultafoAmonio()) +
					 (getPrecoUreia() * getQtdUreia());		
		return valorTotal;
		
	}
	
	
	
	
	
	public Double getPrecoMilho() {
		return precoMilho;
	}
	public void setPrecoMilho(Double precoMilho) {
		this.precoMilho = precoMilho;
	}
	public Double getPrecoFareloSoja() {
		return precoFareloSoja;
	}
	public void setPrecoFareloSoja(Double precoFareloSoja) {
		this.precoFareloSoja = precoFareloSoja;
	}
	public Double getPrecoUreia() {
		return precoUreia;
	}
	public void setPrecoUreia(Double precoUreia) {
		this.precoUreia = precoUreia;
	}
	public Double getPrecoSulfatoAmonio() {
		return precoSulfatoAmonio;
	}
	public void setPrecoSulfatoAmonio(Double precoSulfatoAmonia) {
		this.precoSulfatoAmonio = precoSulfatoAmonia;
	}
	public Double getPrecoMisturaMineral() {
		return precoMisturaMineral;
	}
	public void setPrecoMisturaMineral(Double precoMisturaMineral) {
		this.precoMisturaMineral = precoMisturaMineral;
	}
	public Double getQtdMilho() {
		return qtdMilho;
	}
	public void setQtdMilho(Double qtdMilho) {
		this.qtdMilho = qtdMilho;
	}
	public Double getQtdFareloSoja() {
		return qtdFareloSoja;
	}
	public void setQtdFareloSoja(Double qtdFareloSoja) {
		this.qtdFareloSoja = qtdFareloSoja;
	}
	public Double getQtdUreia() {
		return qtdUreia;
	}
	public void setQtdUreia(Double qtdUreia) {
		this.qtdUreia = qtdUreia;
	}
	public Double getQtdSultafoAmonio() {
		return qtdSultafoAmonio;
	}
	public void setQtdSultafoAmonio(Double qtdSultafoAmonia) {
		this.qtdSultafoAmonio = qtdSultafoAmonia;
	}
	public Double getQtdMisturaMineral() {
		return qtdMisturaMineral;
	}
	public void setQtdMisturaMineral(Double qtdMisturaMineral) {
		this.qtdMisturaMineral = qtdMisturaMineral;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Double getValorTotal() {
		return valorTotal;
	}





	public void setId(Key id) {
		this.id = id;
	}





	public Key getId() {
		return id;
	}
	
	
}
