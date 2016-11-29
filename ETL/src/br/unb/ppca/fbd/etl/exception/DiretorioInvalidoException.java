package br.unb.ppca.fbd.etl.exception;

public class DiretorioInvalidoException extends Exception {

	private static final long serialVersionUID = 3925398756762242283L;
	
	private String mensagem;
	
	public DiretorioInvalidoException(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}
}
