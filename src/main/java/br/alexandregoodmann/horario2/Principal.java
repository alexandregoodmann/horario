package br.alexandregoodmann.horario2;

import java.util.List;

import br.com.goodmann.model.Cadeira;
import br.com.goodmann.model.Quadro;
import br.com.goodmann.util.ArquivoUtil;

public class Principal {

	public static void main(String[] args) throws Exception {

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
		List<Quadro> quadros = montar.quadros(cadeiras);
		quadros.forEach(d -> {
			System.out.println(d);
		});

		System.out.println("Feito");
		System.exit(0);
	}

}
