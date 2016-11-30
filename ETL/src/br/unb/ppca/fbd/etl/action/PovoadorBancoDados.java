package br.unb.ppca.fbd.etl.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.unb.ppca.fbd.etl.exception.ArquivoInvalidoException;
import br.unb.ppca.fbd.etl.exception.DiretorioInvalidoException;
import br.unb.ppca.fbd.etl.exception.LinhaInvalidaException;
import br.unb.ppca.fbd.etl.util.ArquivoUtil;
import br.unb.ppca.fbd.etl.vo.ArquivoProcessado;
import br.unb.ppca.fbd.etl.vo.LinhaProcessada;
import br.unb.ppca.fbd.etl.vo.UF;

public class PovoadorBancoDados {

	private static final String SEPARADOR = ";";
	private static final int NUMERO_ATRIBUTOS = 44;

	private EntityManager entityManager;

	private PovoadorBancoDados() {
	}

	private static PovoadorBancoDados instance;

	public static PovoadorBancoDados instance() {
		if (instance == null) {
			instance = new PovoadorBancoDados();
		}
		return instance;
	}

	public void povoar(File diretorio) throws DiretorioInvalidoException, ArquivoInvalidoException {
		if (entityManager == null) {
			entityManager = Persistence.createEntityManagerFactory("etlPU").createEntityManager();
		}
		entityManager.createNativeQuery("select * from MUNICIPIO").getResultList();
		Map<UF, File> arquivosNaoProcessados = ValidadorDiretorio.instance().validar(diretorio);
		Map<UF, ArquivoProcessado> arquivosProcessados = processarArquivos(arquivosNaoProcessados);

		for (UF uf : UF.values()) {
			povoarDados(uf, arquivosProcessados.get(uf));
		}

	}

	private void povoarDados(UF uf, ArquivoProcessado arquivoProcessado) {
		// TODO Auto-generated method stub

	}

	private Map<UF, ArquivoProcessado> processarArquivos(Map<UF, File> arquivosNaoProcessados)
			throws ArquivoInvalidoException {
		Map<UF, ArquivoProcessado> arquivosProcessados = new HashMap<UF, ArquivoProcessado>();

		for (UF uf : UF.values()) {
			File arquivoNaoProcessado = arquivosNaoProcessados.get(uf);
			ArquivoProcessado arquivoProcessado = new ArquivoProcessado();
			try {
				List<String> linhasNaoProcessadas = ArquivoUtil.lerTodasLinhas(arquivoNaoProcessado.getAbsolutePath());
				
				int numeroLinha = 1;

				for (String linha : linhasNaoProcessadas) {
					try {
						if (!"".equals(linha.trim())) {
							LinhaProcessada linhaProcessada = processarLinha(linha);
							System.out.println(linhaProcessada);
							incluirDadosLinha(arquivoProcessado, linhaProcessada);
						}
					} catch (LinhaInvalidaException e) {
						throw new ArquivoInvalidoException("A linha " + numeroLinha + " do arquivo "
								+ arquivoNaoProcessado.getName() + " é inválida. Possui " + e.getNumeroAtributos()
								+ " atributos e deveria possuir " + NUMERO_ATRIBUTOS + ".");
					}
					numeroLinha++;
				}
			} catch (IOException e) {
				e.printStackTrace();
				throw new ArquivoInvalidoException("O arquivo " + arquivoNaoProcessado.getName() + " não é válido.");
			}
			
			arquivosProcessados.put(uf, arquivoProcessado);
		}

		return arquivosProcessados;
	}

	private void incluirDadosLinha(ArquivoProcessado arquivoProcessado, LinhaProcessada linhaProcessada) {
		// TODO Auto-generated method stub
	}

	private LinhaProcessada processarLinha(String linha) throws LinhaInvalidaException {
		String[] atributos = linha.split(SEPARADOR);
		validarAtributos(atributos);
		return new LinhaProcessada(atributos);
	}

	private void validarAtributos(String[] atributos) throws LinhaInvalidaException {
		if (NUMERO_ATRIBUTOS != atributos.length) {
			throw new LinhaInvalidaException(atributos.length);
		}

	}

}
