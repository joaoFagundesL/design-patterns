package com.chessgame.strategy;

import com.chessgame.board.Board;
import com.chessgame.pieces.Piece;

public interface MoveStrategy {
  boolean canMove(int horizontalAxis, int verticalAxis, Board board, Piece piece);

  default boolean checkDiagonalMove(
      final int horizontalPos,
      final int verticalPos,
      final int horizontalDir,
      final int verticalDir,
      final Board board,
      final int horizontalCoord,
      final int verticalCoord) {

    boolean isClear = true;
    final int totalHorizonal = horizontalCoord + horizontalDir;
    final int totalVertical = verticalCoord + verticalDir;
    for (int i = totalHorizonal, currentVertical = totalVertical;
        i != horizontalPos;
        i += horizontalDir, currentVertical += verticalDir) {
      if (board.getXY(i, currentVertical) != 0) {
        isClear = false;
        break;
      }
    }
    return isClear;
  }

  default boolean checkMove(
      final int startPos,
      final int endPos,
      final int fixedCoord,
      final int variableCoord,
      final Board board,
      final boolean isVertical) {

    boolean isClear = true;
    final int minPos = Math.min(startPos, endPos) + 1;
    final int maxPos = Math.max(startPos, endPos);

    for (int i = minPos; i < maxPos; i++) {
      if ((isVertical ? board.getXY(fixedCoord, i) : board.getXY(i, fixedCoord)) != 0) {
        isClear = false;
        break;
      }
    }

    return isClear;
  }

  default boolean checkVerticalMove(
      final int verticalPos,
      final Board board,
      final int horizontalCoord,
      final int verticalCoord) {
    return checkMove(verticalPos, verticalCoord, horizontalCoord, verticalCoord, board, true);
  }

  default boolean checkHorizontalMove(
      final int horizontalPos,
      final Board board,
      final int horizontalCoord,
      final int verticalCoord) {
    return checkMove(horizontalPos, horizontalCoord, verticalCoord, verticalCoord, board, false);
  }
}
