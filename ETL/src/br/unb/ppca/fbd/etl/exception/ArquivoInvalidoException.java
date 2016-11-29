package br.unb.ppca.fbd.etl.exception;

public class ArquivoInvalidoException extends Exception {

	private static final long serialVersionUID = -987341759854374027L;
	
	private String mensagem;
	
	public ArquivoInvalidoException(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}
}