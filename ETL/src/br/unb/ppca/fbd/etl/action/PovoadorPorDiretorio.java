package br.unb.ppca.fbd.etl.action;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import br.unb.ppca.fbd.etl.dominio.UF;
import br.unb.ppca.fbd.etl.exception.ArquivoInvalidoException;
import br.unb.ppca.fbd.etl.exception.DiretorioInvalidoException;
import br.unb.ppca.fbd.etl.vo.ArquivoProcessado;

public class PovoadorPorDiretorio extends PovoadorGenerico {
	
	PovoadorPorDiretorio(File diretorio) {
		this.file = diretorio;
	}

	@Override
	public void povoar() throws DiretorioInvalidoException, ArquivoInvalidoException {
		Map<UF, File> arquivosNaoProcessados = ValidadorDiretorio.instance().validar(file);
		Map<UF, ArquivoProcessado> arquivosProcessados = processarArquivos(arquivosNaoProcessados);

		getEntityManager().getTransaction().begin();
		try {
			for (UF uf : UF.values()) {
				povoarDados(uf, arquivosProcessados.get(uf));
			}

			getEntityManager().flush();
			getEntityManager().getTransaction().commit();
			getEntityManager().clear();
			getEntityManager().close();
		} catch (Exception e) {
			getEntityManager().getTransaction().rollback();
			getEntityManager().clear();
			getEntityManager().close();
			throw e;
		}
	}
	
	private Map<UF, ArquivoProcessado> processarArquivos(Map<UF, File> arquivosNaoProcessados)
			throws ArquivoInvalidoException {
		Map<UF, ArquivoProcessado> arquivosProcessados = new HashMap<UF, ArquivoProcessado>();
		for (UF uf : UF.values()) {
			if(uf.isTemEleicaoMunicipal()) {
				arquivosProcessados.put(uf, processarArquivo(arquivosNaoProcessados.get(uf)));
			}
		}
		return arquivosProcessados;
	}

	
}
