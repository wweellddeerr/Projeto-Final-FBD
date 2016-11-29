package br.unb.ppca.fbd.etl.action;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		JFileChooser uploader = new JFileChooser();
		uploader.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		int retorno = uploader.showOpenDialog(null);
		
		if(retorno == JFileChooser.APPROVE_OPTION) {
			String mensagemExcecao = null;
			try {
				PovoadorBancoDados.instance().povoar(uploader.getSelectedFile());
				JOptionPane.showMessageDialog(null, "O diretório é válido.");
			}
			catch(DiretorioInvalidoException e) {
				mensagemExcecao = e.getMensagem();
			}
			catch (ArquivoInvalidoException e) {
				mensagemExcecao = e.getMensagem();
			}
			if(mensagemExcecao != null) {
				JOptionPane.showMessageDialog(null, mensagemExcecao, "Erro no processamento", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
}
