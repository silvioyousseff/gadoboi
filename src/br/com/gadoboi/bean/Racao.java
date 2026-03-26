package br.com.gadoboi.bean;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.appengine.api.datastore.Key;


@Entity
public class Racao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key id;
	@Column(length=100)
	private String nomeRacao;
	@Column(length=100)
	private String taxaRendimento;
	@Temporal(TemporalType.DATE)
	private Date data;
	@Column(length=100)
	private String horario;	
	@Column(length=100)
	private String qtdGado;
	@Column(length=1)
	private String status;
	@Column(length=100)
	private String pesoInicial;
	@Column(length=100)
	private String qtdTratamentoDia;
	@ManyToOne
	private Key gado;
	@ManyToOne
	private Key regiao;
	@ManyToOne
	private Key manejo;
	@ManyToOne
	private Key cliente;
	@ManyToOne
	private Key metodo;
	
	public Key getId() {
		return id;
	}
	public void setId(Key id) {
		this.id = id;
	}
	public String getNomeRacao() {
		return nomeRacao;
	}
	public void setNomeRacao(String nomeRacao) {
		this.nomeRacao = nomeRacao;
	}
	public String getTaxaRendimento() {
		return taxaRendimento;
	}
	public void setTaxaRendimento(String taxaRendimento) {
		this.taxaRendimento = taxaRendimento;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Key getGado() {
		return gado;
	}
	public void setGado(Gado gado) {
		this.gado = gado.getId();
	}
	public Key getRegiao() {
		return regiao;
	}
	public void setRegiao(Regiao regiao) {
		this.regiao = regiao.getId();
	}
	public Key getManejo() {
		return manejo;
	}
	public void setManejo(Manejo manejo) {
		this.manejo = manejo.getId();
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public String getHorario() {
		return horario;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente.getId();
	}
	public Key getCliente() {
		return cliente;
	}
	public void setQtdGado(String qtdGado) {
		this.qtdGado = qtdGado;
	}
	public String getQtdGado() {
		return qtdGado;
	}
	public void setPesoInicial(String pesoInicial) {
		this.pesoInicial = pesoInicial;
	}
	public String getPesoInicial() {
		return pesoInicial;
	}
	public void setQtdTratamentoDia(String qtdTratamentoDia) {
		this.qtdTratamentoDia = qtdTratamentoDia;
	}
	public String getQtdTratamentoDia() {
		return qtdTratamentoDia;
	}
	public void setMetodo(Metodo metodo) {
		this.metodo = metodo.getId();
	}
	public Key getMetodo() {
		return metodo;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	

}
