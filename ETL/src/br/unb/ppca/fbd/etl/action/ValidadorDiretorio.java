package br.unb.ppca.fbd.etl.action;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ValidadorDiretorio {
	
	private ValidadorDiretorio() {}
	
	private static ValidadorDiretorio instance;
	
	public static ValidadorDiretorio instance() {
		if(instance == null) {
			instance = new ValidadorDiretorio();
		}
		
		return instance;
	}

	public Map<UF, File> validar(File diretorio) throws DiretorioInvalidoException {
		
		Map<UF, File> arquivosPorUF = new HashMap<UF, File>();
		File[] arquivos = diretorio.listFiles();
		
		for (File arquivo : arquivos) {
			
			UF uf = null;
			
			if(arquivo.getName().endsWith(".txt")) {
				
				try {
					uf = UF.valueOf(arquivo.getName().split("_")[3].substring(0, 2));
				}
				catch(Exception e) {}
			}
			else {
				continue;
			}
			
			if(uf == null) {
				throw new DiretorioInvalidoException("O nome do arquivo " + arquivo.getName() + " não é válido.");
			}
			else {
				arquivosPorUF.put(uf, arquivo);
			}
		}
		
		validarExisteArquivoParaTodasUfs(arquivosPorUF);
		return arquivosPorUF;
	}

	private void validarExisteArquivoParaTodasUfs(Map<UF, File> arquivosPorUF) throws DiretorioInvalidoException {
		
		DiretorioInvalidoException e = new DiretorioInvalidoException("A pasta selecionada não contém os arquivos necessários para povoar o banco de dados.");
		
		if(arquivosPorUF.size() == 26) {
			
			for (UF uf : UF.values()) {
				
				if(arquivosPorUF.get(uf) == null) {
					throw e;
				}
				
			}
			
		}
		
		else {
			throw e;
		}
	}

}
