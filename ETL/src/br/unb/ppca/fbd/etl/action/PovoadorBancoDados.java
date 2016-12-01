package br.unb.ppca.fbd.etl.action;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.unb.ppca.fbd.etl.entity.Candidato;
import br.unb.ppca.fbd.etl.entity.Candidatura;
import br.unb.ppca.fbd.etl.entity.CandidaturaId;
import br.unb.ppca.fbd.etl.entity.GrauInstrucao;
import br.unb.ppca.fbd.etl.entity.Municipio;
import br.unb.ppca.fbd.etl.entity.Nacionalidade;
import br.unb.ppca.fbd.etl.entity.Ocupacao;
import br.unb.ppca.fbd.etl.entity.PartidoPolitico;
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
			getEntityManager().close();
		} catch (Exception e) {
			getEntityManager().getTransaction().rollback();
			getEntityManager().close();
			throw e;
		}
	}

	private void incluirTodasUFs() {
		for (UF uf : UF.values()) {
			getEntityManager().createNativeQuery("insert into UF(SIGLA, NOME) values (:sigla, :nome)")
					.setParameter("sigla", uf.name()).setParameter("nome", uf.getNome()).executeUpdate();
		}
	}

	private void povoarDados(UF uf, ArquivoProcessado arquivoProcessado) {
		for (LinhaProcessada linhaProcessada : arquivoProcessado.getLinhasProcessadas()) {
			// cria os municípios de eleição
			criarMunicipioSeNaoExistir(linhaProcessada.getCodigoMunicipio(), linhaProcessada.getNomeMunicipio(), uf);

			// cria os partidos políticos
			criarPartidoSeNaoExistir(linhaProcessada.getNumeroPartido(), linhaProcessada.getSiglaPartido(),
					linhaProcessada.getNomePartido());

			// cria as nacionalidade
			criarNacionalidadeSeNaoExistir(linhaProcessada.getCodigoNacionalidade(),
					linhaProcessada.getDescricaoNacionalidade());

			// cria as ocupações
			criarOcupacaoSeNaoExistir(linhaProcessada.getCodigoOcupacao(), linhaProcessada.getDescricaoOcupacao());

			// cria os graus de instrução
			criarGrauInstrucaoSeNaoExistir(linhaProcessada.getCodigoGrauInstrucao(),
					linhaProcessada.getDescricaoGrauInstrucao());
			
			//cria os municípios de nascimento
			criarMunicipioSeNaoExistir(linhaProcessada.getCodigoMunicipioNascimento(), 
					linhaProcessada.getNomeMunicipioNascimento(), UF.valueOf(linhaProcessada.getSiglaUFNascimento()));

			// cria os candidatos
			criarCandidatoSeNaoExistir(linhaProcessada.getCpf(), linhaProcessada.getCodigoMunicipioNascimento(),
					linhaProcessada.getCodigoNacionalidade(), linhaProcessada.getCodigoOcupacao(),
					linhaProcessada.getCodigoGrauInstrucao(), linhaProcessada.getNomeCandidato(),
					linhaProcessada.getDataNascimento(), linhaProcessada.getNumeroTitulo(), linhaProcessada.getEmail(),
					linhaProcessada.getSexo(), linhaProcessada.getEstadoCivil());

			// cria as candidaturas
			criarCandidaturaSeNaoExistir(linhaProcessada);
		}

	}

	private void criarCandidaturaSeNaoExistir(LinhaProcessada linhaProcessada) {
		
		Candidatura c = getEntityManager().find(Candidatura.class, new CandidaturaId(linhaProcessada.getAnoCandidatura(), linhaProcessada.getCpf()));

		if(c == null) {
			getEntityManager().persist(new Candidatura(linhaProcessada.getAnoCandidatura(), linhaProcessada.getCpf(),
					linhaProcessada.getNumeroPartido(), linhaProcessada.getCodigoMunicipio(),
					linhaProcessada.getNumeroUrna(), linhaProcessada.getNomeUrna(),
					linhaProcessada.getDescricaoSituacaoCandidatura(),
					linhaProcessada.getNomeCargo(),
					linhaProcessada.getSituacaoEleicao(),
					determinarSeSegundoTurno(linhaProcessada.getNumeroTurno()))
				);
		}
	}

	private boolean determinarSeSegundoTurno(int numeroTurno) {
		return numeroTurno == 2;
	}

	private void criarCandidatoSeNaoExistir(BigInteger cpf, int codigoMunicipioNascimento, int codigoNacionalidade,
			int codigoOcupacao, int codigoGrauInstrucao, String nomeCandidato, String dataNascimento,
			BigInteger numeroTitulo, String email, String sexo, String estadoCivil) {

		Candidato c = getEntityManager().find(Candidato.class, cpf);

		if (c == null) {
			getEntityManager().persist(new Candidato(cpf, codigoMunicipioNascimento, codigoNacionalidade,
					codigoOcupacao, codigoGrauInstrucao, nomeCandidato, converterData(dataNascimento), numeroTitulo,
					email, sexo, estadoCivil));
		}

	}

	private Date converterData(String data) {
		try {
			return new SimpleDateFormat("dd/MM/yyyy").parse(data);
		} catch (ParseException e) {
			throw new RuntimeException("erro no parse de data");
		}
	}

	private void criarGrauInstrucaoSeNaoExistir(int codigoGrauInstrucao, String descricaoGrauInstrucao) {
		GrauInstrucao g = getEntityManager().find(GrauInstrucao.class, codigoGrauInstrucao);
		if (g == null) {
			getEntityManager().persist(new GrauInstrucao(codigoGrauInstrucao, descricaoGrauInstrucao));
		}
	}

	private void criarOcupacaoSeNaoExistir(int codigoOcupacao, String descricaoOcupacao) {
		Ocupacao o = getEntityManager().find(Ocupacao.class, codigoOcupacao);
		if (o == null) {
			getEntityManager().persist(new Ocupacao(codigoOcupacao, descricaoOcupacao));
		}
	}

	private void criarNacionalidadeSeNaoExistir(int codigoNacionalidade, String descricaoNacionalidade) {
		Nacionalidade n = getEntityManager().find(Nacionalidade.class, codigoNacionalidade);
		if (n == null) {
			getEntityManager().persist(new Nacionalidade(codigoNacionalidade, descricaoNacionalidade));
		}
	}

	private void criarPartidoSeNaoExistir(int numeroPartido, String siglaPartido, String nomePartido) {
		PartidoPolitico p = getEntityManager().find(PartidoPolitico.class, numeroPartido);
		if (p == null) {
			getEntityManager().persist(new PartidoPolitico(numeroPartido, siglaPartido, nomePartido));
		}
	}

	private void criarMunicipioSeNaoExistir(int codigoMunicipio, String nomeMunicipio, UF uf) {
		Municipio m = getEntityManager().find(Municipio.class, codigoMunicipio);
		if (m == null) {
			getEntityManager().persist(new Municipio(codigoMunicipio, nomeMunicipio, uf));
		}
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
