package factory;

import strategy.EasyGameStrategy;
import strategy.GameStrategy;
import strategy.HardGameStrategy;

public class GameStrategyFactory {
  public static GameStrategy createStrategy(final boolean easyMode) {
    return easyMode ? new EasyGameStrategy() : new HardGameStrategy();
  }
}
