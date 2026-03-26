package br.com.gadoboi.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

@Entity
public class Rendimento{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key id;
	@Column (precision = 2)
	private double qtdAnimais;
	@Column (precision = 2)
	private double periodoDeTratamento;
	@Column (precision = 2)
	private double tamanhoDaPastagem;
	@Column (precision = 2)
	private double pesoInicial;
	@Column (precision = 2)
	private double ganhoDePesoEsperado;
	@Column (precision = 2)
	private double qtdConcentradoTotal;
	@Column (precision = 2)
	private double qtdConcentradoDiariamente;
	@Column (precision = 2)
	private double qtdConcentradoDiarioCabeca;
	@Column (precision = 2)
	private double pesoMedioEmArroba;
	@Column (precision = 2)
	private double taxaLotacao;
	@Column (precision = 2)
	private double porcentagemPorPeso;
	@Column (precision = 2)
	private double precoArroba;
	@Column (precision = 2)
	private double rendimentoCarcaca;
	@Column (precision = 2)
	private double precoConcentradoDiaAnimal;
	@Column (precision = 2)
	private double ganhoPorCabecaDia;
	@Column (precision = 2)
	private double custoTotal;
	@Column (precision = 2)
	private double ganhoTotal;
	@Column (precision = 2)
	private double resultado;
	@Column (precision = 2)
	private double precoPorQuiloCon;
	
	
	
	
	
	
	
	
	
	public void calcular(){
		taxaLotacao = (getQtdAnimais() / getTamanhoDaPastagem());
		pesoMedioEmArroba = (getPesoInicial() / 15);
		qtdConcentradoDiarioCabeca = (getPesoInicial() * 0.01);
		qtdConcentradoDiariamente = (getQtdConcentradoDiarioCabeca() * getQtdAnimais());
		qtdConcentradoTotal = (getQtdConcentradoDiariamente() * getPeriodoDeTratamento());
		precoConcentradoDiaAnimal = (getPrecoPorQuiloCon() * getQtdConcentradoDiarioCabeca());
		ganhoPorCabecaDia = ((getGanhoDePesoEsperado() * (getRendimentoCarcaca() * 0.01)) * getPrecoArroba() / 15);
		custoTotal = (getPrecoPorQuiloCon() * getQtdConcentradoTotal());
		ganhoTotal = (getGanhoDePesoEsperado() * 
						getQtdAnimais() * 
						getPeriodoDeTratamento() * 
						(getRendimentoCarcaca() * 0.01) * 
						(getPrecoArroba() / 15)
					);
		resultado = (getGanhoTotal() - getCustoTotal());
	}
	
	
	
	
	
	
	
	
	
	public void setPorcentagemPorPeso(double porcentagemPorPeso) {
		this.porcentagemPorPeso = porcentagemPorPeso;
	}
	public double getPorcentagemPorPeso() {
		return porcentagemPorPeso;
	}
	public void setPrecoArroba(double precoArroba) {
		this.precoArroba = precoArroba;
	}
	public double getPrecoArroba() {
		return precoArroba;
	}
	public void setRendimentoCarcaca(double rendimentoCarcaca) {
		this.rendimentoCarcaca = rendimentoCarcaca;
	}
	public double getRendimentoCarcaca() {
		return rendimentoCarcaca;
	}
	public double getPrecoConcentradoDiaAnimal() {
		return precoConcentradoDiaAnimal;
	}
	public double getGanhoPorCabecaDia() {
		return ganhoPorCabecaDia;
	}
	public double getCustoTotal() {
		return custoTotal;
	}
	public double getGanhoTotal() {
		return ganhoTotal;
	}
	public double getResultado() {
		return resultado;
	}
	public void setQtdAnimais(double qtdAnimais) {
		this.qtdAnimais = qtdAnimais;
	}
	public double getQtdAnimais() {
		return qtdAnimais;
	}
	public void setPeriodoDeTratamento(double periodoDeTratamento) {
		this.periodoDeTratamento = periodoDeTratamento;
	}
	public double getPeriodoDeTratamento() {
		return periodoDeTratamento;
	}
	public void setTamanhoDaPastagem(double tamanhoDaPastagem) {
		this.tamanhoDaPastagem = tamanhoDaPastagem;
	}
	public double getTamanhoDaPastagem() {
		return tamanhoDaPastagem;
	}
	public void setGanhoDePesoEsperado(double ganhoDePesoEsperado) {
		this.ganhoDePesoEsperado = ganhoDePesoEsperado;
	}
	public double getGanhoDePesoEsperado() {
		return ganhoDePesoEsperado;
	}
	public void setPesoInicial(double pesoInicial) {
		this.pesoInicial = pesoInicial;
	}
	public double getPesoInicial() {
		return pesoInicial;
	}
	public double getQtdConcentradoTotal() {
		return qtdConcentradoTotal;
	}
	public double getQtdConcentradoDiariamente() {
		return qtdConcentradoDiariamente;
	}
	public double getQtdConcentradoDiarioCabeca() {
		return qtdConcentradoDiarioCabeca;
	}
	public double getPesoMedioEmArroba() {
		return pesoMedioEmArroba;
	}
	public double getTaxaLotacao() {
		return taxaLotacao;
	}
	public void setPrecoPorQuiloCon(double precoPorQuiloCon) {
		this.precoPorQuiloCon = precoPorQuiloCon;
	}
	public double getPrecoPorQuiloCon() {
		return precoPorQuiloCon;
	}
	public void setId(Key id) {
		this.id = id;
	}
	public Key getId() {
		return id;
	}
}
