package br.goodmann.horario.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Cadeira implements Comparable<Cadeira> {

	private String codigo = "";
	private String descricao = "";
	private int credito = 0;

	@JsonIgnore
	private List<String> periodos;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getCredito() {
		return credito;
	}

	public void setCredito(int credito) {
		this.credito = credito;
	}

	public List<String> getPeriodos() {
		return periodos;
	}

	public void setPeriodos(List<String> periodos) {
		this.periodos = periodos;
	}

	@Override
	public int compareTo(Cadeira o) {
		return this.descricao.compareTo(o.getDescricao());
	}

}
