package br.com.gadoboi.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.google.appengine.api.datastore.Key;
@Entity
public class Metodo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key id;	
	@Column(length=100)
	private String nomeMetodo;
	@ManyToOne
	private Key racao;
	
	
	public Key getId() {
		return id;
	}
	public void setId(Key id) {
		this.id = id;
	}
	public String getNomeMetodo() {
		return nomeMetodo;
	}
	public void setNomeMetodo(String nomeMetodo) {
		this.nomeMetodo = nomeMetodo;
	}
	public Key getRacao() {
		return racao;
	}
	public void setRacao(Racao racao) {
		this.racao = racao.getId();
	}

}
