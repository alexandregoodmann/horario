package br.goodmann.horario.model;

public class Periodo {

	private Cadeira segunda;
	private Cadeira terca;
	private Cadeira quarta;
	private Cadeira quinta;
	private Cadeira sexta;
	private Cadeira sabado;

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

}
