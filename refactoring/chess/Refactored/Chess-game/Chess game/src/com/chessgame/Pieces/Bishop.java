package com.chessgame.Pieces;

import com.chessgame.Board.*;
import com.chessgame.Strategy.BishopMoveStrategy;
import com.chessgame.Strategy.MoveStrategy;

public class Bishop extends Piece {
	
	public Bishop(int x, int y, boolean iswhite, Board board, int value) {
		super(x, y, iswhite, board, value, new BishopMoveStrategy());
		this.pieceImage = PieceImages.BISHOP;
	}
	
	public void intializeSide(int value){
		super.intializeSide(value);
		if(isWhite()) {
			image = PieceImages.wb;
		}
		else {
			image = PieceImages.bb;
		}
	}
	
	
	public boolean bishopMoves(int x,int y, Board board) {
		if(x > xCord && y > yCord) {
			int j = yCord+1;
			for(int i=xCord+1; i<x; i++) {
				if(board.getXY(i, j) != 0) {
					return false;
				}
				j++;
			}
			
		}
		else if(x < xCord && y < yCord) {
			int j = yCord-1;
			for(int i=xCord-1; i>x; i--) {
				if(board.getXY(i, j) != 0) {
					return false;
				}
				j--;
			}
			
		}
		else if(x > xCord && y < yCord) {
			int j = yCord - 1;
			for(int i=xCord+1; i<x; i++) {
				if(board.getXY(i, j) != 0) {
					return false;
				}
				j--;
			}
		}
		else if(x < xCord && y > yCord) {
			int j = yCord+1;
			for(int i=xCord-1; i>x; i--) {
				if(board.getXY(i, j) != 0) {
					return false;
				}
				j++;
			}
		}
		return true;
		
	}


}
