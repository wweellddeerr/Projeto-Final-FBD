package br.unb.ppca.fbd.etl.action;

import java.util.Calendar;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import br.unb.ppca.fbd.etl.exception.ArquivoInvalidoException;
import br.unb.ppca.fbd.etl.exception.DiretorioInvalidoException;

public class Main {

	public static void main(String[] args) {
		JFileChooser uploader = new JFileChooser();
		uploader.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		int retorno = uploader.showOpenDialog(null);
		
		if(retorno == JFileChooser.APPROVE_OPTION) {
			String mensagemExcecao = null;
			System.out.println("Início do processamento: " + 
					Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + 
					Calendar.getInstance().get(Calendar.MINUTE) + ":" +
					Calendar.getInstance().get(Calendar.SECOND));
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
			catch (Exception e) {
				e.printStackTrace();
				mensagemExcecao = "erro";
			}
			if(mensagemExcecao != null) {
				JOptionPane.showMessageDialog(null, mensagemExcecao, "Erro no processamento", JOptionPane.ERROR_MESSAGE);
			}
			
			System.out.println("Término do processamento: " + 
					Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + 
					Calendar.getInstance().get(Calendar.MINUTE) + ":" +
					Calendar.getInstance().get(Calendar.SECOND));
		}
		
	}
}
