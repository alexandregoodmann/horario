package br.goodmann.horario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.goodmann.horario.model.Cadeira;
import br.goodmann.horario.model.Quadro;
import br.goodmann.horario.service.ArquivoUtil;
import br.goodmann.horario.service.MontaObjetos;
import br.goodmann.horario.service.MontaQuadros;

@CrossOrigin
@RestController
public class HorarioController {

	@Autowired
	private MontaObjetos montaObjetos;

	@Autowired
	private MontaQuadros montaQuadros;

	private List<Cadeira> cadeiras;

	@GetMapping("/cadeiras")
	public List<Cadeira> cadeiras() throws Exception {

		// IGNORAR
		String[] ignorarPeriodos = {};

		String[] ignorarCadeiras = {};

		// Ler Arquivo e pega as linhas
		// String path =
		// "/home/alexandre/eclipse-workspace/horario/src/main/resources/2018-2.txt";
		String path = "D:\\dev\\horario\\src\\main\\resources\\2018-2.txt";

		ArquivoUtil arquivo = new ArquivoUtil();
		List<String> linhas = arquivo.lerArquivo(path);

		this.cadeiras = this.montaObjetos.turmasDasDisciplinas(linhas, ignorarPeriodos, ignorarCadeiras);

		return this.cadeiras;
	}

	@PostMapping("/quadros")
	public List<Quadro> quadros(@RequestBody Filtro filtro) throws Exception {

		String path = "D:\\dev\\horario\\src\\main\\resources\\2018-2.txt";

		ArquivoUtil arquivo = new ArquivoUtil();
		List<String> linhas = arquivo.lerArquivo(path);

		this.cadeiras = this.montaObjetos.turmasDasDisciplinas(linhas, filtro.getIgnorarPeriodos(),
				filtro.getIgnorarCadeiras());
		
		return this.montaQuadros.quadros(this.cadeiras);
	}
}
