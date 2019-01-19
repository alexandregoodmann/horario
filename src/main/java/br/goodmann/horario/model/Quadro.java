package br.goodmann.horario.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Quadro implements Comparable<Quadro> {

	@JsonIgnore
	private Map<String, Periodo> mapa = new HashMap<String, Periodo>();

	private List<Periodo> periodos;

	private int totalCredito;

	public int getTotalCredito() {
		return totalCredito;
	}

	public void setTotalCredito(int totalCredito) {
		this.totalCredito = totalCredito;
	}

	public Map<String, Periodo> getMapa() {
		return mapa;
	}

	public void setMapa(Map<String, Periodo> mapa) {
		this.mapa = mapa;
	}

	public List<Periodo> getPeriodos() {
		return periodos;
	}

	public void setPeriodos(List<Periodo> periodos) {
		this.periodos = periodos;
	}

	@Override
	public int compareTo(Quadro o) {
		if (this.totalCredito > o.getTotalCredito()) {
			return -1;
		} else if (this.totalCredito < o.getTotalCredito()) {
			return 1;
		}
		return 0;
	}

}
