package br.unb.ppca.fbd.etl.exception;

public class LinhaInvalidaException extends Exception {

	private static final long serialVersionUID = -6922296346934723985L;

	private int numeroAtributos;
	
	public LinhaInvalidaException(int numeroAtributos) {
		this.numeroAtributos = numeroAtributos;
	}

	public int getNumeroAtributos() {
		return numeroAtributos;
	}
}
