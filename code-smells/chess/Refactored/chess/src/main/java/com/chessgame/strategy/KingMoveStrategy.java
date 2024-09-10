package com.chessgame.strategy;

import com.chessgame.board.Board;
import com.chessgame.board.Movement;
import com.chessgame.game.Game;
import com.chessgame.pieces.King;
import com.chessgame.pieces.Piece;

public class KingMoveStrategy implements MoveStrategy {

  @Override
  public boolean canMove(
      final int positionX, final int positionY, final Board board, final Piece piece) {
    final King king = (King) piece;
    final int xCord = king.getXcord();
    final int yCord = king.getYcord();

    final int indexI = Math.abs(xCord - positionX);
    final int indexJ = Math.abs(yCord - positionY);

    if (indexJ == 1 && indexI == 1 || (indexI + indexJ) == 1) {

      if (board.getPiece(positionX, positionY) == null) {
        return true;
      } else {
        return board.getPiece(positionX, positionY).isWhite() != king.isWhite();
      }
    }

    king.fetchRook(positionX, board);
    if (king.getRook() != null && (king.getRook().hasMoved() || king.getHasMoved())) {
      return false;
    } else if (king.getRook() != null) {

      for (int k = xCord + 1; k < king.getRook().getXcord(); k++) {
        if (board.getPiece(k, yCord) != null) {
          return false;
        }
        for (final Movement movement : Game.allEnemysMove) {
          if ((movement.getToX() == k || movement.getToX() == xCord)
              && movement.getToY() == yCord) {
            return false;
          }
        }
      }
      if (positionX == king.getRook().getXcord() - 1 && positionY == yCord) {
        return true;
      }

      for (int k = xCord - 1; k > king.getRook().getXcord(); k--) {
        if (board.getPiece(k, yCord) != null) {
          return false;
        }
        for (final Movement m : Game.allEnemysMove) {
          if ((m.getToX() == k || m.getToX() == xCord) && m.getToY() == yCord) {
            return false;
          }
        }
      }
      if (positionX == king.getRook().getXcord() + 2 && positionX == yCord) {
        return true;
      }
    }

    return false;
  }
}
