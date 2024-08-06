package com.chessgame.Strategy;

import com.chessgame.Board.Board;
import com.chessgame.Pieces.Piece;
import com.chessgame.Pieces.Rook;

public class RookMoveStrategy implements MoveStrategy {

	@Override
	public boolean canMove(int x, int y, Board board, Piece piece) {
		Rook rook = (Rook) piece;
		int xCord = rook.getXcord();
		int yCord = rook.getYcord();
		
		if (board.getPiece(x, y) != null && board.getPiece(x, y).isWhite() == rook.isWhite()) {
			return false;
		}

		if (x == xCord && (y < yCord)) {
			for (int i = yCord - 1; i > y; i--) {
				if (board.getXY(x, i) != 0) {
					return false;
				}
			}
			return true;
		}

		if (x == xCord && (y > yCord)) {
			for (int i = yCord + 1; i < y; i++) {
				if (board.getXY(x, i) != 0) {
					return false;
				}
			}
			return true;
		}

		if (y == yCord && (x > xCord)) {
			for (int i = xCord + 1; i < x; i++) {
				if (board.getXY(i, y) != 0) {
					return false;
				}
			}
			return true;
		}
		if (y == yCord && (x < xCord)) {
			for (int i = xCord - 1; i > x; i--) {
				if (board.getXY(i, y) != 0) {
					return false;
				}
			}
			return true;
		}

		return false;

	}

}
