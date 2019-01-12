package br.alexandregoodmann.horario2.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.alexandregoodmann.horario2.MontaObjetos;
import br.alexandregoodmann.horario2.MontaQuadros;
import br.com.goodmann.model.Cadeira;
import br.com.goodmann.model.Quadro;
import br.com.goodmann.util.ArquivoUtil;

@RestController
public class horario {

	@GetMapping("/quadros")
	public List<Quadro> test() throws Exception {

		// IGNORAR
		String[] ignorarPeriodos = {};

		String[] ignorarCadeiras = {};

		// Ler Arquivo e pega as linhas
		String path = "/home/alexandre/eclipse-workspace/horario/resource/2019-1.txt";
		ArquivoUtil arquivo = new ArquivoUtil();
		List<String> linhas = arquivo.lerArquivo(path);

		// Transforma linhas do arquivo em objetos
		MontaObjetos ler = new MontaObjetos();
		List<Cadeira> cadeiras = ler.cadeiras(linhas, ignorarPeriodos, ignorarCadeiras);

		MontaQuadros montar = new MontaQuadros();
		return montar.quadros(cadeiras);

	}
}
