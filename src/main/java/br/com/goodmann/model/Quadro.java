package br.com.goodmann.model;

public class Quadro {

	private Cadeira data[][];
	private int totalCredito;

	public Quadro() {
		this.data = new Cadeira[7][6];
	}

	public void put(Cadeira obj, int m, int n) {
		this.data[m][n] = obj;
	}

	public Cadeira get(int m, int n) {
		return this.data[m][n];
	}

	public int getTotalCredito() {
		return totalCredito;
	}

	public void setTotalCredito(int totalCredito) {
		this.totalCredito = totalCredito;
	}

	public Cadeira[][] getData() {
		return data;
	}

	public void setData(Cadeira[][] data) {
		this.data = data;
	}

}
