package com.chessgame.Pieces;
import com.chessgame.Board.Board;
import com.chessgame.Board.Move;
import com.chessgame.Game.Game;
import com.chessgame.Strategy.KingMoveStrategy;
import com.chessgame.Strategy.MoveStrategy;
import com.chessgame.Strategy.RookMoveStrategy;

public class King extends Piece {
	private boolean hasMoved;
	private Rook rook = null;
	
	public King(int x, int y, boolean iswhite, Board board, int value) {
		super(x, y, iswhite, board, value, new KingMoveStrategy());
		hasMoved = false;
		this.pieceImage = PieceImages.KING;
	}
	
	public void intializeSide(int value){
		super.intializeSide(value);
		if(isWhite()) {
			image = PieceImages.wk;
		}
		else {
			image = PieceImages.bk;
		}
	}
	
	public boolean makeMove(int x, int y, Board board) {
		Move move = new Move(xCord, yCord, x, y, this);
		if(!alive()) {
			return false;
		}
		for(Move m: moves) {
			if(m.compareTo(move) == 0) {
				fetchRook(x, board);
				board.updatePieces(xCord, yCord, x, y,this);
				xCord = x;
				yCord = y;
				if(rook != null && !this.hasMoved && !rook.HasMoved()) {
					if(x == rook.getXcord() - 1 || x == rook.getXcord() + 2) {
						rook.castleDone(xCord, board);
					}					
				}
				hasMoved = true;
				return true;
			}
		}
		return false;
	}
	
	public Rook getRook() {
		return rook;
	}
	
	public boolean getHasMoved() {
		return hasMoved;
	}

	
	public void fetchRook(int x, Board board) {
		if(isWhite()) {
			if(x >= xCord) {
				if(board.getPiece(7, 7) != null && board.getPiece(7, 7) instanceof Rook){
					rook = (Rook) board.getPiece(7, 7);
				}
			}
			else{
				if(board.getPiece(0, 7) != null && board.getPiece(0, 7) instanceof Rook) {
					rook = (Rook) board.getPiece(0, 7);
				}
			}
		}
		else {
			if(x >= xCord) {
				if(board.getPiece(7, 0) != null && board.getPiece(7, 0) instanceof Rook) {
					rook = (Rook) board.getPiece(7, 0);					
				}
			}
			else{
				if(board.getPiece(0,0) != null && board.getPiece(0,0) instanceof Rook) {
					rook = (Rook) board.getPiece(0, 0);						
				}
			}
		}
	}
	
	public boolean isInCheck() {
		for(Move m: Game.allEnemysMove) {
			if(m.getToX() == xCord && m.getToY() == yCord) {
				return true;
			}
		}
		return false;
	}
}
