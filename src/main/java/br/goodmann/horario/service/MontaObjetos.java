package br.goodmann.horario.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.goodmann.horario.model.Cadeira;

@Service
public class MontaObjetos {

	private List<String> formataHorario(String vet2) throws Exception {

		List<String> list = new ArrayList<String>();

		int ini = vet2.indexOf("Horários:") + 9;
		int fim = vet2.length();
		String horario = vet2.substring(ini, fim).replaceAll("\\W", "");

		switch (horario.length()) {

		case 3:
			list.add(horario);
			break;
		case 5: {
			String a1 = horario.substring(0, 3);
			String a2 = horario.substring(0, 1) + horario.substring(3, 5);
			list.add(a1);
			list.add(a2);
			break;
		}
		case 6: {
			String a1 = horario.substring(0, 3);
			String a2 = horario.substring(3, 6);
			list.add(a1);
			list.add(a2);
			break;
		}

		default:
			break;
		}

		if (list.size() == 0) {
			throw new Exception("Cadeira sem horários: " + vet2);
		}

		return list;
	}

	public List<Cadeira> disciplinas(List<String> linhas, String[] vetPeriodos, String[] vetCadeiras) throws Exception {
		List<Cadeira> list = this.turmasDasDisciplinas(linhas, vetPeriodos, vetCadeiras);
		Map<String, Cadeira> mapa = new HashMap<String, Cadeira>();
		for (Cadeira cadeira : list) {
			mapa.put(cadeira.getCodigo(), cadeira);
		}
		List<Cadeira> retorno = new ArrayList<Cadeira>(mapa.values());
		Collections.sort(retorno);
		return retorno;
	}

	public List<Cadeira> turmasDasDisciplinas(List<String> linhas, String[] vetPeriodos, String[] vetCadeiras)
			throws Exception {

		List<Cadeira> cadeiras = new ArrayList<Cadeira>();

		String descricao = null;
		String codigo = null;
		int credito = 0;

		for (String linha : linhas) {

			if (linha.startsWith("Nivel")) {

				codigo = linha.substring(linha.length() - 8, linha.length());
				credito = Integer.valueOf(codigo.split("-")[1]);

			} else if (linha.contains("Nome: ")) {

				descricao = linha.substring(6, linha.length());

			} else if (linha.contains("Vagas:")) {

				int ini = linha.indexOf("Vagas:") + 6;
				int vagas = Integer.valueOf(linha.substring(ini, ini + 1));
				if (vagas > 0) {

					Cadeira cadeira = new Cadeira();
					cadeira.setDescricao(descricao);
					if ("".equals(codigo) || codigo == null) {
						throw new Exception("codigo vazio");
					}
					cadeira.setCodigo(codigo);
					cadeira.setCredito(credito);
					cadeira.setPeriodos(this.formataHorario(linha));

					if (!this.ignorar(cadeira, vetPeriodos, vetCadeiras)) {
						cadeiras.add(cadeira);
					}
				}
			}
		}
		return cadeiras;
	}

	private boolean ignorar(Cadeira cadeira, String[] vetPeriodos, String[] vetCadeiras) {

		// ignora os Períodos listados (2JK, 3LM, 5NP)
		for (String per : vetPeriodos) {
			for (String s : cadeira.getPeriodos()) {
				if (per.compareToIgnoreCase(s.substring(1)) == 0) {
					return true;
				}
			}
		}

		// ignora as cadeias listadas
		for (String car : vetCadeiras) {
			if (car.equalsIgnoreCase(cadeira.getCodigo())) {
				return true;
			}
		}

		return false;
	}

	/*
	 * public static void main(String[] args) throws IOException {
	 * 
	 * ArquivoUtil arquivo = new ArquivoUtil(); List<String> linhas =
	 * arquivo.lerArquivo();
	 * 
	 * MontaObjetos monta = new MontaObjetos(); List<Cadeira> cadeiras =
	 * monta.cadeiras(linhas);
	 * 
	 * for (Cadeira cad : cadeiras) { System.out.println(cad); } }
	 */
}
