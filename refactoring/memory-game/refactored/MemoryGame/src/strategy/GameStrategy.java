package strategy;

import game.GameM;

public interface GameStrategy {
  void configureBoard(GameM game, int level);
}
