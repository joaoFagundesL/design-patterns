package com.chessgame.pieces;

import com.chessgame.board.Board;
import com.chessgame.board.Movement;
import com.chessgame.game.Game;
import com.chessgame.strategy.KingMoveStrategy;

public class King extends Piece {
  private boolean hasMoved;
  private Rook rook;

  public King(
      final int positionX,
      final int positionY,
      final boolean iswhite,
      final Board board,
      final int value) {
    super(positionX, positionY, iswhite, board, value, new KingMoveStrategy());
    hasMoved = false;
    this.pieceImage = PieceImages.KING;
  }

  public void intializeSide(final int value) {
    super.intializeSide(value);
    if (isWhite()) {
      image = PieceImages.wk;
    } else {
      image = PieceImages.bk;
    }
  }

  public boolean makeMove(final int positionX, final int positionY, final Board board) {
    final Movement move = new Movement(xCord, yCord, positionX, positionY, this);

    if (!isAlive()) {
      return false;
    }

    if (isValidMove(move)) {
      executeMove(positionX, positionY, board);
      if (shouldCastle(positionX)) {
        performCastling(board);
      }
      hasMoved = true;
      return true;
    }

    return false;
  }

  private boolean isAlive() {
    return alive();
  }

  private boolean isValidMove(final Movement move) {
    return moves.stream().anyMatch(m -> m.compareTo(move) == 0);
  }

  private void executeMove(final int positionX, final int positionY, final Board board) {
    fetchRook(positionX, board);
    board.updatePieces(xCord, yCord, positionX, positionY, this);
    xCord = positionX;
    yCord = positionY;
  }

  private boolean shouldCastle(final int positionX) {
    return rook != null
        && !hasMoved
        && !rook.hasMoved()
        && (positionX == rook.getXcord() - 1 || positionX == rook.getXcord() + 2);
  }

  private void performCastling(final Board board) {
    rook.castleDone(xCord, board);
  }

  public Rook getRook() {
    return rook;
  }

  public boolean getHasMoved() {
    return hasMoved;
  }

  public void fetchRook(final int positionX, final Board board) {
    final int rookX = (isWhite() && positionX >= xCord) ? 7 : 0;
    final int rookY = isWhite() ? 7 : 0;

    if (board.getPiece(rookX, rookY) instanceof Rook) {
      rook = (Rook) board.getPiece(rookX, rookY);
    } else {
      rook = null;
    }
  }

  public boolean isInCheck() {
    for (final Movement m : Game.allEnemysMove) {
      if (m.getToX() == xCord && m.getToY() == yCord) {
        return true;
      }
    }
    return false;
  }
}
