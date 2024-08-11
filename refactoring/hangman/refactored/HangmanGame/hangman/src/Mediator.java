import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;

public interface Mediator {
	int checkGuess (String palavraDoJogo, char letra, List<JLabel> campos, JButton l);
	void processGuess(int encontrou, int[] chances, JLabel labelChances, String palavraDoJogo);
	
	String getPalavraDoJogo();
    int getEncontrou();
    int[] getChances();
    JLabel getLabelChances();
    List<JLabel> getCampos();
}
