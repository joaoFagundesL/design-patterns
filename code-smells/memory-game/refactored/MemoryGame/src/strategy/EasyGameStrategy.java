package strategy;

import game.GameM;

public class EasyGameStrategy implements GameStrategy {

  @Override
  public void configureBoard(GameM game, int level) {
    String[] symbols = {"square", "circle", "rectangle", "heart", "diamond", "clover", "spade", "triangle", "polygon", "tetrahedral"};
    game.setBoardSymbols(symbols, level);
  }
}
