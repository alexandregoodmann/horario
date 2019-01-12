package br.goodmann.horario.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;

public class ArquivoUtil {

	public List<String> lerArquivo(String arquivo) {

		List<String> linhas = new LinkedList<String>();
		BufferedReader br = null;
		try {

			FileInputStream stream = new FileInputStream(arquivo);

			InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
			br = new BufferedReader(reader);
			String linha;

			linha = br.readLine();
			while (linha != null) {

				linhas.add(linha);
				linha = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return linhas;

	}

	public void escrever(String texto, String arquivoHTML) throws IOException {
		OutputStreamWriter bufferOut = new OutputStreamWriter(new FileOutputStream(arquivoHTML), "UTF-8");
		bufferOut.write(texto);
		bufferOut.close();
	}

}
