package strategy;

import game.GameM;

public class HardGameStrategy implements GameStrategy {

  @Override
  public void configureBoard(final GameM game, final int level) {
    final String[] symbols = {
      ":-D", "X", "O", "-(*.*)-", "<>", "<(^-^)>", "=>", ";^P", "ABC", "123"
    };
    game.setBoardSymbols(symbols, level);
  }
}
