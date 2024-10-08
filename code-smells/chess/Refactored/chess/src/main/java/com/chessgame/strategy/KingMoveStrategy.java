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

    boolean result = false;

    if (isRegularMove(indexI, indexJ)) {
      result = isMoveValidForRegularMove(positionX, positionY, board, king);
    } else if (isCastlingMove(indexI, indexJ)) {
      result = isMoveValidForCastling(positionX, positionY, board, king);
    }

    return result;
  }

  private boolean isRegularMove(final int indexI, final int indexJ) {
    return indexJ == 1 && indexI == 1 || (indexI + indexJ) == 1;
  }

  private boolean isMoveValidForRegularMove(
      final int positionX, final int positionY, final Board board, final King king) {
    final boolean result;
    if (board.getPiece(positionX, positionY) == null) {
      result = true;
    } else {
      result = board.getPiece(positionX, positionY).isWhite() != king.isWhite();
    }
    return result;
  }

  private boolean isCastlingMove(final int indexI, final int indexJ) {
    return (indexI == 2 && indexJ == 0) || (indexI == 0 && indexJ == 2);
  }

  private boolean isMoveValidForCastling(
      final int positionX, final int positionY, final Board board, final King king) {
    boolean result = false;
    king.fetchRook(positionX, board);
    if (king.getRook() != null && (king.getRook().hasMoved() || king.getHasMoved())) {
      result = false;
    } else if (king.getRook() != null) {
      result = isPathClearForCastling(positionX, positionY, board, king);
    }
    return result;
  }

  private boolean isPathClearForCastling(
      final int positionX, final int positionY, final Board board, final King king) {
    boolean result = false;
    final int startX = Math.min(king.getXcord(), king.getRook().getXcord());
    final int endX = Math.max(king.getXcord(), king.getRook().getXcord());
    final int yCord = king.getYcord();
    boolean clearPath = true;

    for (int k = startX + 1; k < endX; k++) {
      if (board.getPiece(k, yCord) != null) {
        clearPath = false;
        break;
      }
      if (isSquareUnderAttack(k, yCord)) {
        clearPath = false;
        break;
      }
    }

    if (clearPath) {
      result = isCastlingPositionValid(positionX, positionY, king);
    }

    return result;
  }

  private boolean isSquareUnderAttack(final int horizontalPos, final int verticalPos) {
    boolean result = false;
    for (final Movement movement : Game.allEnemysMove) {
      if (movement.getToX() == horizontalPos && movement.getToY() == verticalPos) {
        result = true;
        break;
      }
    }
    return result;
  }

  private boolean isCastlingPositionValid(
      final int positionX, final int positionY, final King king) {
    boolean result = false;
    final int rookX = king.getRook().getXcord();
    if (positionX == rookX - 1 && positionY == king.getYcord()
        || positionX == rookX + 2 && positionY == king.getYcord()) {
      result = true;
    }
    return result;
  }
}
