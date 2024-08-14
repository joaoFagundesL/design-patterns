import java.awt.event.ActionEvent;

public class ButtonPressCommand implements Command {
    private GameM game;
    private int buttonIndex;

    public ButtonPressCommand(GameM game, int buttonIndex) {
        this.game = game;
        this.buttonIndex = buttonIndex;
    }

    @Override
    public void execute() {
        if (game.getShown()) {
            game.hideField(game.getLevel());
        } else {
            game.switchSpot(buttonIndex);
            if (game.getTemp() >= (game.getLevel() * 2)) {
                game.setTemp(buttonIndex);
            } else {
                if (!game.getBoard()[game.getTemp()].equals(game.getBoard()[buttonIndex])
                        || (game.getTemp() == buttonIndex)) {
                    game.setTemp2(buttonIndex);
                    game.setPurgatory(true);
                } else {
                    game.getBoard()[buttonIndex] = "done";
                    game.getBoard()[game.getTemp()] = "done";
                    game.checkWin();
                    game.setTemp(game.getLevel() * 2);
                }
            }
        }
    }

}
