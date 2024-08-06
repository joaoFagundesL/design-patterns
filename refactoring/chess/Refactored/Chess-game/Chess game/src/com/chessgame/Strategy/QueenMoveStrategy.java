package com.chessgame.Strategy;

import com.chessgame.Board.Board;
import com.chessgame.Pieces.Piece;
import com.chessgame.Pieces.Queen;

public class QueenMoveStrategy implements MoveStrategy {
	
	@Override
	public boolean canMove(int x, int y, Board board, Piece piece) {
		Queen queen = (Queen) piece;
		int xCord = queen.getXcord();
		int yCord = queen.getYcord();
		
		if(board.getPiece(x, y) != null && board.getPiece(x, y).isWhite() == queen.isWhite()) {
			return false;
		}
	
		if(Math.abs(x-xCord) == Math.abs( y-yCord)) {
			return (queen.queenMovesDiagonial(x, y, board));
		}
		if( x == xCord  || y == yCord ) {
			return queen.queenMovesStraight(x, y, board);
		}
		
	return false;
	}
	

}
