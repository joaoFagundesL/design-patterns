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
        
        if (board.getPiece(x, y) != null && board.getPiece(x, y).isWhite() == queen.isWhite()) {
            return false;
        }
    
        if (Math.abs(x - xCord) == Math.abs(y - yCord)) {
            return queenMovesDiagonial(x, y, board, xCord, yCord);
        } else if (x == xCord || y == yCord) {
            return queenMovesStraight(x, y, board, xCord, yCord);
        }
        
        return false;
    }
    
    public boolean queenMovesStraight(int x, int y, Board board, int xCord, int yCord) {
        if (x == xCord) {
            return checkVerticalMove(y, board, xCord, yCord);
        } else if (y == yCord) {
            return checkHorizontalMove(x, board, xCord, yCord);
        }
        return false;
    }

    public boolean queenMovesDiagonial(int x, int y, Board board, int xCord, int yCord) {
        if (x > xCord && y > yCord) {
            return checkDiagonalMove(x, y, 1, 1, board, xCord, yCord);
        } 
        if (x < xCord && y < yCord) {
            return checkDiagonalMove(x, y, -1, -1, board, xCord, yCord);
        }
        if (x > xCord && y < yCord) {
            return checkDiagonalMove(x, y, 1, -1, board, xCord, yCord);
        }
        if (x < xCord && y > yCord) {
            return checkDiagonalMove(x, y, -1, 1, board, xCord, yCord);
        }
        return false;
    }
}
