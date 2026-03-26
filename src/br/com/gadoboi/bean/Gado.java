package br.com.gadoboi.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.google.appengine.api.datastore.Key;
@Entity
public class Gado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key id;
	@Column(length=100)
	private String nomeGado;
	@ManyToOne
	private Key cliente;
	
	public Key getId() {
		return id;
	}
	public void setId(Key id) {
		this.id = id;
	}
	public String getNomeGado() {
		return nomeGado;
	}
	public void setNomeGado(String nomeGado) {
		this.nomeGado = nomeGado;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente.getId();
	}
	public Key getCliente() {
		return cliente;
	}
}
