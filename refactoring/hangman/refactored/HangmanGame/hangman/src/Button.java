import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;

public class Button extends JButton implements Component{
	private Mediator mediator;
	private String letter;
	
	public Button(String letter, int x, int y, int width, int height) {
        super(letter);
        this.letter = letter;
        setFont(new Font("Dialog", Font.BOLD, 12));
        setToolTipText("");
        setBounds(x, y, width, height);
        
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireActionPerformed(e);
            }
        });
    }
	
	@Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }
	
	@Override
	public String getName() {
		return letter;
	}
	
	protected void fireActionPerformed(ActionEvent actionEvent) {
		String palavraDoJogo = mediator.getPalavraDoJogo();
		int[] chances = mediator.getChances();
		JLabel labelChances = mediator.getLabelChances();
		int encontrou = mediator.getEncontrou();
		List<JLabel> campos = mediator.getCampos();
		
		encontrou = mediator.teste(palavraDoJogo, letter.charAt(0), campos, this);
		mediator.verificar(encontrou, chances, labelChances, palavraDoJogo);
		
	}

}
