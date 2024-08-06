package com.chessgame.Pieces;
import com.chessgame.Board.Board;
import com.chessgame.Strategy.KnightMoveStrategy;
import com.chessgame.Strategy.MoveStrategy;

public class Knight extends Piece {
	
	public Knight(int x, int y, boolean iswhite, Board board, int value) {
		super(x, y, iswhite, board, value, new KnightMoveStrategy());
		this.pieceImage = PieceImages.KNIGHT;
	}
	
	public void intializeSide(int value){
		super.intializeSide(value);
		if(isWhite()) {
			image = PieceImages.wn;
		}
		else {
			image = PieceImages.bn;
		}
	}

}
