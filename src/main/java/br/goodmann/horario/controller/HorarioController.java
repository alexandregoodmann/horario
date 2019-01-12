package br.goodmann.horario.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.goodmann.horario.model.Cadeira;
import br.goodmann.horario.model.Quadro;
import br.goodmann.horario.service.ArquivoUtil;
import br.goodmann.horario.service.MontaObjetos;
import br.goodmann.horario.service.MontaQuadros;

@CrossOrigin
@RestController
public class HorarioController {

	@GetMapping("/quadros")
	public List<Quadro> test() throws Exception {

		// IGNORAR
		String[] ignorarPeriodos = {};

		String[] ignorarCadeiras = {};

		// Ler Arquivo e pega as linhas
		String path = "/home/alexandre/eclipse-workspace/horario/src/main/resources/2018-2.txt";
		ArquivoUtil arquivo = new ArquivoUtil();
		List<String> linhas = arquivo.lerArquivo(path);

		// Transforma linhas do arquivo em objetos
		MontaObjetos ler = new MontaObjetos();
		List<Cadeira> cadeiras = ler.cadeiras(linhas, ignorarPeriodos, ignorarCadeiras);

		MontaQuadros montar = new MontaQuadros();
		return montar.quadros(cadeiras);

	}
}
