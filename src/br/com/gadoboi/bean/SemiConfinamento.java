package br.com.gadoboi.bean;


public abstract class SemiConfinamento extends RacaoDemonstracao{
	private double precoIonoforo;
	private double precoCalCalcitico;
	private double qtdIonoforo;
	private double qtdCalCalcitico;
	private double valorTotal;
	
	@Override
	public Double calculaTotal() {
		valorTotal = (getPrecoIonoforo() * getQtdIonoforo()) +
					 (getPrecoCalCalcitico() * getQtdCalCalcitico());
		return super.calculaTotal() + valorTotal;
	}
	
	public double getPrecoIonoforo() {
		return precoIonoforo;
	}
	public void setPrecoIonoforo(double precoIonoforo) {
		this.precoIonoforo = precoIonoforo;
	}
	public double getPrecoCalCalcitico() {
		return precoCalCalcitico;
	}
	public void setPrecoCalCalcitico(double precoCalCalcitico) {
		this.precoCalCalcitico = precoCalCalcitico;
	}
	public double getQtdIonoforo() {
		return qtdIonoforo;
	}
	public void setQtdIonoforo(double qtdIonoforo) {
		this.qtdIonoforo = qtdIonoforo;
	}
	public double getQtdCalCalcitico() {
		return qtdCalCalcitico;
	}
	public void setQtdCalCalcitico(double qtdCalCalcitico) {
		this.qtdCalCalcitico = qtdCalCalcitico;
	}
}
