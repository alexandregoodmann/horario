package br.goodmann.horario.service;

import java.util.ArrayList;
import java.util.List;

import br.goodmann.horario.model.Cadeira;

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

	public List<Cadeira> cadeiras(List<String> linhas, String[] vetPeriodos, String[] vetCadeiras) throws Exception {

		List<Cadeira> cadeiras = new ArrayList<Cadeira>();

		String descricao = null;
		String codigo = null;
		int credito = 0;

		for (String linha : linhas) {

			if (linha.startsWith("Nivel")) {

				codigo = linha.substring(linha.length() - 8, linha.length());
				String[] vet = codigo.split("-");
				codigo = vet[0].trim();
				credito = Integer.valueOf(vet[1]);

			} else if (linha.contains("Nome: ")) {

				descricao = linha.substring(6, linha.length());

			} else if (linha.contains("Vagas:")) {

				int ini = linha.indexOf("Vagas:") + 6;
				int vagas = Integer.valueOf(linha.substring(ini, ini + 1));
				if (vagas > 0) {

					Cadeira cadeira = new Cadeira();
					cadeira.setDescricao(descricao);
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
			if (cadeira.getPeriodos().contains(per)) {
				return true;
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
