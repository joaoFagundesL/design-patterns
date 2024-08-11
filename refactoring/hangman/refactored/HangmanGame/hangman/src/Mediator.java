import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;

public interface Mediator {
	int teste (String palavraDoJogo, char letra, List<JLabel> campos, JButton l);
	void verificar(int encontrou, int[] chances, JLabel labelChances, String palavraDoJogo);
	
	String getPalavraDoJogo();
    int getEncontrou();
    int[] getChances();
    JLabel getLabelChances();
    List<JLabel> getCampos();
}
