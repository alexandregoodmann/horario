package br.goodmann.horario.model;

public class Filtro {

	private String[] ignorarPeriodos;
	private String[] ignorarCadeiras;

	public String[] getIgnorarPeriodos() {
		return ignorarPeriodos;
	}

	public void setIgnorarPeriodos(String[] ignorarPeriodos) {
		this.ignorarPeriodos = ignorarPeriodos;
	}

	public String[] getIgnorarCadeiras() {
		return ignorarCadeiras;
	}

	public void setIgnorarCadeiras(String[] ignorarCadeiras) {
		this.ignorarCadeiras = ignorarCadeiras;
	}

}
