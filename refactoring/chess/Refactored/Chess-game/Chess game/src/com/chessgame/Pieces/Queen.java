package com.chessgame.Pieces;
import com.chessgame.Board.Board;
import com.chessgame.Strategy.MoveStrategy;
import com.chessgame.Strategy.QueenMoveStrategy;

public class Queen extends Piece {

	public Queen(int x, int y, boolean iswhite, Board board, int value) {
		super(x, y, iswhite, board, value, new QueenMoveStrategy());
		this.pieceImage = PieceImages.QUEEN;
	}

	public void intializeSide(int value){
		super.intializeSide(value);
		if(isWhite()) {
			image = PieceImages.wq;
		}
		else {
			image = PieceImages.bq;
		}
	}

	
}
