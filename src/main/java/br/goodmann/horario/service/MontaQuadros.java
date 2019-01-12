package br.goodmann.horario.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.goodmann.horario.model.Cadeira;
import br.goodmann.horario.model.Quadro;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class MontaQuadros {

	private static final String PERIODOS[] = { "AB", "CD", "FG", "HI", "JK", "LM", "NP" };

	private Map<String, Cadeira> mapa;

	public List<Quadro> quadros(final List<Cadeira> cadeiras) {

		List<Quadro> retorno = new ArrayList<Quadro>();
		List<Cadeira> list = this.criaQuadros(cadeiras);

		for (int i = 0; i < list.size(); i++) {

			Quadro quadro = new Quadro();
			List<Cadeira> listaCadeiras = (List<Cadeira>) list.get(i);

			for (Cadeira cadeira : listaCadeiras) {
				cadeira.getPeriodos().forEach(p -> {
					quadro.put(cadeira, this.parseLinha(p), this.parseColuna(p));
				});
			}
			retorno.add(quadro);
		}
		return retorno;
	}

	private int parseLinha(String periodo) {
		List<String> periodos = Arrays.asList(PERIODOS);
		int ret = periodos.indexOf(periodo.substring(1));
		return ret;
	}

	private int parseColuna(String periodo) {
		int ret = Integer.parseInt(periodo.substring(0, 1)) - 2;
		return ret;
	}

	private List criaQuadros(final List<Cadeira> cadeiras) {

		List<Cadeira> origem = new ArrayList<Cadeira>(cadeiras);
		List destino = new ArrayList();

		while (origem.size() > 0) {
			mapa = new HashMap<String, Cadeira>();
			for (Cadeira c : origem) {
				if (!this.mapa.containsKey(c.getCodigo()) && !this.conflito(c)) {
					mapa.put(c.getCodigo(), c);
				}
			}
			origem.removeAll(mapa.values());
			List<Cadeira> item = new ArrayList<Cadeira>(mapa.values());
			destino.add(item);
		}

		return destino;
	}

	private boolean conflito(Cadeira c) {
		for (Cadeira cc : mapa.values()) {
			for (String hor : c.getPeriodos()) {
				if (cc.getPeriodos().contains(hor)) {
					return true;
				}
			}
		}
		return false;
	}

}
