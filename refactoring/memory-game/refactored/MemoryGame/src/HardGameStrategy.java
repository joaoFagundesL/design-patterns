
public class HardGameStrategy implements GameStrategy{

	@Override
	public void configureBoard(GameM game, int level) {
		String[] symbols = {":-D", "X", "O", "-(*.*)-", "<>", "<(^-^)>", "=>", ";^P", "ABC", "123"};
        game.setBoardSymbols(symbols, level);
	}

}
