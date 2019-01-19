package br.goodmann.horario.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.goodmann.horario.model.Cadeira;
import br.goodmann.horario.model.Periodo;
import br.goodmann.horario.model.Quadro;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Service
public class MontaQuadros {

	private Map<String, Cadeira> mapa;

	public List<Quadro> quadros(final List<Cadeira> cadeiras) {

		List<Quadro> retorno = new ArrayList<Quadro>();
		List<Cadeira> list = this.criaQuadros(cadeiras);

		for (int i = 0; i < list.size(); i++) {

			Quadro quadro = new Quadro();
			List<Cadeira> listaCadeiras = (List<Cadeira>) list.get(i);

			int totalCredito = 0;

			for (Cadeira cadeira : listaCadeiras) {
				totalCredito = totalCredito + cadeira.getCredito();
				cadeira.getPeriodos().forEach(periodo -> {
					String per = periodo.substring(1);
					if (quadro.getMapa().containsKey(per)) {
						quadro.getMapa().get(per).put(this.parseDia(periodo), cadeira);
					} else {
						Periodo obj = new Periodo();
						obj.put(this.parseDia(periodo), cadeira);
						quadro.getMapa().put(per, obj);
					}
				});
			}

			List<Periodo> periodos = new ArrayList<Periodo>();
			for (String key : quadro.getMapa().keySet()) {
				Periodo periodo = quadro.getMapa().get(key);
				periodo.setPeriodo(key);
				periodos.add(periodo);
			}
			Collections.sort(periodos);
			
			quadro.setPeriodos(periodos);
			quadro.setTotalCredito(totalCredito);
			retorno.add(quadro);
		}

		Collections.sort(retorno);
		return retorno;
	}

	private int parseDia(String periodo) {
		int ret = Integer.parseInt(periodo.substring(0, 1));
		return ret;
	}

	/**
	 * Cria uma Collection composta por listas de Cadeiras (uma lista de listas)
	 * baseado nas cadeiras disponíveis para matrícula. As listas são formadas por
	 * Cadeiras não conflitantes, ou seja, não há duas no mesmo horário.
	 * 
	 * @param cadeiras
	 * @return
	 */
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
