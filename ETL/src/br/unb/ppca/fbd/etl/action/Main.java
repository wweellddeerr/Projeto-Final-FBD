package br.unb.ppca.fbd.etl.action;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import br.unb.ppca.fbd.etl.exception.ArquivoInvalidoException;
import br.unb.ppca.fbd.etl.exception.DiretorioInvalidoException;

public class Main {

	public static void main(String[] args) {
		
		int tipo = JOptionPane.showConfirmDialog(null, "Deseja selecionar um diretório completo?");
		JFileChooser uploader = new JFileChooser();
		
		if(tipo == JOptionPane.YES_OPTION) {
			uploader.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		}
		
		int retorno = uploader.showOpenDialog(null);
		
		if(retorno == JFileChooser.APPROVE_OPTION) {
			String mensagemExcecao = null;
			try {
				PovoadorFactory.instance().criarPovoador(uploader.getSelectedFile()).povoar();
				JOptionPane.showMessageDialog(null, "Processamento concluído com sucesso.");
			}
			catch(DiretorioInvalidoException e) {
				mensagemExcecao = e.getMensagem();
			}
			catch (ArquivoInvalidoException e) {
				mensagemExcecao = e.getMensagem();
			}
			catch (Exception e) {
				e.printStackTrace();
				mensagemExcecao = e.getMessage();
			}
			if(mensagemExcecao != null) {
				JOptionPane.showMessageDialog(null, mensagemExcecao, "Erro no processamento", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
}
