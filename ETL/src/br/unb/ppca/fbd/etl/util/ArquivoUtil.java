package br.unb.ppca.fbd.etl.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ArquivoUtil {

	private ArquivoUtil() {}

	public static List<String> lerTodasLinhas(String url, String nome) throws IOException {
		return lerTodasLinhas(url + nome);
	}
	
	public static List<String> lerTodasLinhas(String absolutePath) throws IOException {
		return Files.readAllLines(Paths.get(absolutePath), Charset.forName("ISO-8859-1"));
	}

	public static List<String> lerTodasLinhas(String url, String nome, Charset charset) throws IOException {
		return Files.readAllLines(Paths.get(url, nome), charset);
	}

	public static void criarArquivo(String url, String nome, String conteudo) throws IOException {
		Path p = Paths.get(url, nome);
		if (!Files.exists(p)) {
			Files.createFile(p);
		}
		Files.write(p, conteudo.getBytes());
	}

}
