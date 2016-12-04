package br.unb.ppca.fbd.etl.action;

import java.io.File;

import br.unb.ppca.fbd.etl.dominio.UF;
import br.unb.ppca.fbd.etl.exception.ArquivoInvalidoException;
import br.unb.ppca.fbd.etl.exception.DiretorioInvalidoException;
import br.unb.ppca.fbd.etl.vo.ArquivoProcessado;

public class PovoadorPorArquivo extends PovoadorGenerico {
	PovoadorPorArquivo(File arquivo) {
		this.file = arquivo;
	}

	@Override
	public void povoar() throws DiretorioInvalidoException, ArquivoInvalidoException {
		ArquivoProcessado arquivoProcessado = processarArquivo(file);
		
		getEntityManager().getTransaction().begin();
		try {
			povoarDados(obterUF(arquivoProcessado.getNomeArquivo()), arquivoProcessado);
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

	private UF obterUF(String nomeArquivo) {
		return UF.valueOf(nomeArquivo.split("_")[3].substring(0, 2));
	}
}
