package br.unb.ppca.fbd.etl.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.unb.ppca.fbd.etl.entity.Municipio;
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
		
		Map<UF, File> arquivosNaoProcessados = ValidadorDiretorio.instance().validar(diretorio);
		Map<UF, ArquivoProcessado> arquivosProcessados = processarArquivos(arquivosNaoProcessados);

		getEntityManager().getTransaction().begin();
		try {
			incluirTodasUFs();
			
			for (UF uf : UF.values()) {
				povoarDados(uf, arquivosProcessados.get(uf));
			}
	
			getEntityManager().getTransaction().commit();
		}
		catch(Exception e) {
			getEntityManager().getTransaction().rollback();
			throw e;
		}
		finally {
			getEntityManager().close();
		}
	}

	

	private void incluirTodasUFs() {
		for (UF uf : UF.values()) {
			getEntityManager()
				.createNativeQuery("insert into UF(SIGLA, NOME) values (:sigla, :nome)")
				.setParameter("sigla", uf.name())
				.setParameter("nome", uf.getNome())
				.executeUpdate();
		}
	}

	private void povoarDados(UF uf, ArquivoProcessado arquivoProcessado) {
		for (LinhaProcessada linhaProcessada : arquivoProcessado.getLinhasProcessadas()) {
			Municipio municipioEleicao = criarMunicipioSeNaoExistir(linhaProcessada.getCodigoMunicipio(), linhaProcessada.getNomeMunicipio(), uf);
		}

	}

	private Municipio criarMunicipioSeNaoExistir(int codigoMunicipio, String nomeMunicipio, UF uf) {
		Municipio m = recuperarMunicipioPorCodigo(codigoMunicipio);
		if(m == null) {
			m = criarMunicipio(codigoMunicipio, nomeMunicipio, uf);
		}
		return m;
	}

	private Municipio criarMunicipio(int codigoMunicipio, String nomeMunicipio, UF uf) {
		Municipio m = new Municipio(codigoMunicipio, nomeMunicipio, uf);
		getEntityManager().persist(m);
		return m;
	}

	@SuppressWarnings("unchecked")
	private Municipio recuperarMunicipioPorCodigo(int codigoMunicipio) {
		List<Municipio> municipiosPorCodigo = getEntityManager().createQuery("from Municipio m where m.codigoTSE = :codigo").setParameter("codigo", codigoMunicipio).getResultList();
		if(municipiosPorCodigo == null || municipiosPorCodigo.isEmpty()) {
			return null;
		}
		return municipiosPorCodigo.get(0);
	}

	private Map<UF, ArquivoProcessado> processarArquivos(Map<UF, File> arquivosNaoProcessados)
			throws ArquivoInvalidoException {
		Map<UF, ArquivoProcessado> arquivosProcessados = new HashMap<UF, ArquivoProcessado>();

		for (UF uf : UF.values()) {
			File arquivoNaoProcessado = arquivosNaoProcessados.get(uf);
			ArquivoProcessado arquivoProcessado = new ArquivoProcessado(arquivoNaoProcessado.getName());
			try {
				int numeroLinha = 1;
				for (String linhaNaoProcessada : ArquivoUtil.lerTodasLinhas(arquivoNaoProcessado.getAbsolutePath())) {
					try {
						if (!"".equals(linhaNaoProcessada.trim())) {
							arquivoProcessado.addLinha(processarLinha(linhaNaoProcessada));
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
	
	private EntityManager getEntityManager() {
		if (entityManager == null) {
			entityManager = Persistence.createEntityManagerFactory("etlPU").createEntityManager();
		}
		return entityManager;
	}

}
