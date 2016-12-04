package br.unb.ppca.fbd.etl.action;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.unb.ppca.fbd.etl.dominio.UF;
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

public abstract class PovoadorGenerico {

	public static final String SEPARADOR = ";";
	public static final int NUMERO_ATRIBUTOS = 44;

	protected File file;

	private EntityManager entityManager;

	public abstract void povoar() throws DiretorioInvalidoException, ArquivoInvalidoException;

	protected ArquivoProcessado processarArquivo(File arquivoNaoProcessado) throws ArquivoInvalidoException {
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
		return arquivoProcessado;
	}

	protected void povoarDados(UF uf, ArquivoProcessado arquivoProcessado) {
		for (LinhaProcessada linhaProcessada : arquivoProcessado.getLinhasProcessadas()) {
			System.out.println(linhaProcessada);

			// cria os municípios de eleição
			Municipio municipioEleicao = criarMunicipioSeNaoExistir(linhaProcessada.getNomeMunicipio(), uf);

			// cria os partidos políticos
			PartidoPolitico partido = criarPartidoSeNaoExistir(linhaProcessada.getNumeroPartido(),
					linhaProcessada.getSiglaPartido(), linhaProcessada.getNomePartido());

			// cria as nacionalidade
			criarNacionalidadeSeNaoExistir(linhaProcessada.getCodigoNacionalidade(),
					linhaProcessada.getDescricaoNacionalidade());

			// cria as ocupações
			criarOcupacaoSeNaoExistir(linhaProcessada.getCodigoOcupacao(), linhaProcessada.getDescricaoOcupacao());

			// cria os graus de instrução
			criarGrauInstrucaoSeNaoExistir(linhaProcessada.getCodigoGrauInstrucao(),
					linhaProcessada.getDescricaoGrauInstrucao());

			// cria os municípios de nascimento
			Municipio municipioNascimento = criarMunicipioSeNaoExistir(linhaProcessada.getNomeMunicipioNascimento(),
					UF.valueOf(linhaProcessada.getSiglaUFNascimento()));

			// cria os candidatos
			Candidato candidato = criarCandidatoSeNaoExistir(linhaProcessada.getCpf(),
					municipioNascimento.getCodigoTSE(), linhaProcessada.getCodigoNacionalidade(),
					linhaProcessada.getCodigoOcupacao(), linhaProcessada.getCodigoGrauInstrucao(),
					linhaProcessada.getNomeCandidato(), linhaProcessada.getDataNascimento(),
					linhaProcessada.getNumeroTitulo(), linhaProcessada.getEmail(), linhaProcessada.getSexo(),
					linhaProcessada.getEstadoCivil());

			// cria as candidaturas
			criarCandidaturaSeNaoExistir(linhaProcessada.getAnoCandidatura(), candidato.getCpf(), partido.getNumero(),
					municipioEleicao.getCodigoTSE(), linhaProcessada.getNumeroUrna(), linhaProcessada.getNomeUrna(),
					linhaProcessada.getDescricaoSituacaoCandidatura(), linhaProcessada.getNomeCargo(),
					linhaProcessada.getSituacaoEleicao(), linhaProcessada.getNumeroTurno());
		}

	}

	protected void criarCandidaturaSeNaoExistir(Integer anoCandidatura, Long cpf, Integer numeroPartido,
			Integer codigoMunicipioCandidatura, Integer numeroUrna, String nomeUrna, String situacaoCandidatura,
			String nomeCargo, String situacaoEleicao, Integer numeroTurno) {

		Candidatura c = getEntityManager().find(Candidatura.class, new CandidaturaId(anoCandidatura, cpf));

		if (c == null) {
			getEntityManager().persist(new Candidatura(anoCandidatura, cpf, numeroPartido, codigoMunicipioCandidatura,
					numeroUrna, nomeUrna, situacaoCandidatura, nomeCargo, situacaoEleicao, 
					determinarSeSegundoTurno(numeroTurno)));
		}
	}

	private boolean determinarSeSegundoTurno(int numeroTurno) {
		return numeroTurno == 2;
	}

	private Candidato criarCandidatoSeNaoExistir(Long cpf, int codigoMunicipioNascimento, int codigoNacionalidade,
			int codigoOcupacao, int codigoGrauInstrucao, String nomeCandidato, String dataNascimento,
			BigInteger numeroTitulo, String email, String sexo, String estadoCivil) {

		Candidato c = getEntityManager().find(Candidato.class, cpf);

		if (c == null) {
			c = new Candidato(cpf, codigoMunicipioNascimento, codigoNacionalidade, codigoOcupacao, codigoGrauInstrucao,
					nomeCandidato, converterData(dataNascimento), numeroTitulo, email, sexo, estadoCivil);
			getEntityManager().persist(c);
		}

		return c;
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

	private PartidoPolitico criarPartidoSeNaoExistir(int numeroPartido, String siglaPartido, String nomePartido) {
		PartidoPolitico p = getEntityManager().find(PartidoPolitico.class, numeroPartido);
		if (p == null) {
			p = new PartidoPolitico(numeroPartido, siglaPartido, nomePartido);
			getEntityManager().persist(p);
		}

		return p;
	}

	@SuppressWarnings("unchecked")
	private Municipio criarMunicipioSeNaoExistir(String nomeMunicipio, UF uf) {
		Municipio m = null;

		List<Municipio> ms = getEntityManager()
				.createQuery("from Municipio m where m.nome = :nomeMunicipio and m.uf = :uf")
				.setParameter("nomeMunicipio", nomeMunicipio).setParameter("uf", uf).getResultList();

		if (ms != null && ms.size() == 1) {
			m = ms.get(0);
		} else {
			Integer codigoMunicipio = (Integer) getEntityManager()
					.createQuery("select max(m.codigoTSE) from Municipio m").getSingleResult();
			if(codigoMunicipio == null) {
				codigoMunicipio = 0;
			}
			m = new Municipio(codigoMunicipio + 1, nomeMunicipio, uf);
			getEntityManager().persist(m);
		}

		return m;
	}

	protected LinhaProcessada processarLinha(String linha) throws LinhaInvalidaException {
		String[] atributos = linha.split(SEPARADOR);
		validarAtributos(atributos);
		return new LinhaProcessada(atributos);
	}

	private void validarAtributos(String[] atributos) throws LinhaInvalidaException {
		if (NUMERO_ATRIBUTOS != atributos.length) {
			throw new LinhaInvalidaException(atributos.length);
		}

	}

	protected EntityManager getEntityManager() {
		if (entityManager == null) {
			entityManager = Persistence.createEntityManagerFactory("etlPU").createEntityManager();
		}
		return entityManager;
	}
}