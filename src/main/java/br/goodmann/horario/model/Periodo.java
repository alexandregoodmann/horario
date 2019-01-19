package br.goodmann.horario.model;

public class Periodo implements Comparable<Periodo> {

	private String periodo = "";
	private Cadeira segunda = new Cadeira();
	private Cadeira terca = new Cadeira();
	private Cadeira quarta = new Cadeira();
	private Cadeira quinta = new Cadeira();
	private Cadeira sexta = new Cadeira();
	private Cadeira sabado = new Cadeira();

	public void put(int dia, Cadeira cadeira) {
		switch (dia) {
		case 2:
			this.setSegunda(cadeira);
			break;
		case 3:
			this.setTerca(cadeira);
			break;
		case 4:
			this.setQuarta(cadeira);
			break;
		case 5:
			this.setQuinta(cadeira);
			break;
		case 6:
			this.setSexta(cadeira);
			break;
		case 7:
			this.setSabado(cadeira);
			break;

		default:
			break;
		}
	}

	public Cadeira getSegunda() {
		return segunda;
	}

	public void setSegunda(Cadeira segunda) {
		this.segunda = segunda;
	}

	public Cadeira getTerca() {
		return terca;
	}

	public void setTerca(Cadeira terca) {
		this.terca = terca;
	}

	public Cadeira getQuarta() {
		return quarta;
	}

	public void setQuarta(Cadeira quarta) {
		this.quarta = quarta;
	}

	public Cadeira getQuinta() {
		return quinta;
	}

	public void setQuinta(Cadeira quinta) {
		this.quinta = quinta;
	}

	public Cadeira getSexta() {
		return sexta;
	}

	public void setSexta(Cadeira sexta) {
		this.sexta = sexta;
	}

	public Cadeira getSabado() {
		return sabado;
	}

	public void setSabado(Cadeira sabado) {
		this.sabado = sabado;
	}

	public String getPeriodo() {
		return this.periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	@Override
	public int compareTo(Periodo o) {
		return this.periodo.compareTo(o.getPeriodo());
	}
}
