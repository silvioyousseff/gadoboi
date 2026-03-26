package br.com.gadoboi.bean;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.google.appengine.api.datastore.Key;

@Entity
public class Estado{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key id;
	@Column(length=100)
	private String nome;

	
	
	
	
	
	
	@OneToMany(mappedBy="estado")
	private Collection<Cidade> cidades;
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
	public void setCidades(Collection<Cidade> cidades) {
		this.cidades = cidades;
	}
	public Collection<Cidade> getCidades() {
		return cidades;
	}
	public void setId(Key id) {
		this.id = id;
	}
	public Key getId() {
		return id;
	}
}
