package br.com.gadoboi.bean;

public abstract class Proteinato extends RacaoDemonstracao{
	
	private double precoSalBranco;	
	private double qtdSalBranco;
	private double valorTotal;
	@Override
	public Double calculaTotal() {
		valorTotal = (getPrecoSalBranco() * getQtdSalBranco());
		return super.calculaTotal() + valorTotal;
	}
	
	public double getPrecoSalBranco() {
		return precoSalBranco;
	}
	public void setPrecoSalBranco(double precoSalBranco) {
		this.precoSalBranco = precoSalBranco;
	}
	public double getQtdSalBranco() {
		return qtdSalBranco;
	}
	public void setQtdSalBranco(double qtdSalBranco) {
		this.qtdSalBranco = qtdSalBranco;
	}
}
