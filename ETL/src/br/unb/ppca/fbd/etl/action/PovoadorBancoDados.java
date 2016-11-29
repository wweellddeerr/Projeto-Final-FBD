package br.unb.ppca.fbd.etl.action;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class PovoadorBancoDados {
	
	private PovoadorBancoDados(){}
	
	private static PovoadorBancoDados instance;
	
	public static PovoadorBancoDados instance() {
		if(instance == null) {
			instance = new PovoadorBancoDados();
		}
		return instance;
	}

	public void povoar(File diretorio) throws DiretorioInvalidoException {
		Map<UF, File> arquivosNaoProcessados = ValidadorDiretorio.instance().validar(diretorio);
		Map<UF, ArquivoProcessado> arquivosProcessados = processarArquivos(arquivosNaoProcessados);
		
		for (UF uf : UF.values()) {
			povoarDados(uf,  arquivosProcessados.get(uf));
		}
		
	}
	
	private void povoarDados(UF uf, ArquivoProcessado arquivoProcessado) {
		// TODO Auto-generated method stub
		
	}

	private Map<UF, ArquivoProcessado> processarArquivos(Map<UF, File> arquivosNaoProcessados) {
		Map<UF, ArquivoProcessado> arquivosProcessados = new HashMap<UF, ArquivoProcessado>();
		
		return arquivosProcessados;
	}

}
