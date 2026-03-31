package br.com.gadoboi.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.google.appengine.api.datastore.Key;
@Entity
public class RacaoInsumo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key id;	
	@Column(length=100)
	private double qtdInsumo;
	@ManyToOne
	private Key insumo;
	@ManyToOne
	private Key racao;
	
	public Key getId() {
		return id;
	}
	public void setId(Key id) {
		this.id = id;
	}
	public Key getInsumo() {
		return insumo;
	}
	public void setInsumo(Insumo insumo) {
		this.insumo = insumo.getId();
	}
	public void setRacao(Racao racao) {
		this.racao = racao.getId();
	}
	public Key getRacao() {
		return racao;
	}
	public void setQtdInsumo(double qtdInsumo) {
		this.qtdInsumo = qtdInsumo;
	}
	public double getQtdInsumo() {
		return qtdInsumo;
	}
}
