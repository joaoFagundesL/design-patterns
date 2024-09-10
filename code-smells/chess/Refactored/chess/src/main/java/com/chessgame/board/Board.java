package com.chessgame.board;

import com.chessgame.game.Game;
import com.chessgame.pieces.Pawn;
import com.chessgame.pieces.Piece;
import com.chessgame.pieces.Rook;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Board implements Cloneable {
  public static final int ROWS = 8;
  public static final int COLUMNS = 8;

  public int[][] grid;
  public Piece[][] pieces;
  public Piece lastPieceMoved;
  public Movement lastMove;
  public Piece died;

  public Stack<Movement> lastMoves = new Stack<>();
  public Stack<Piece> deadPieces = new Stack<>();
  public List<Piece> piecesList = new ArrayList<>();

  private static Board instance;

  private Board() {
    grid = new int[ROWS][COLUMNS];
    pieces = new Piece[ROWS][COLUMNS];
  }

  public static Board getInstance() {
    if (instance == null) {
      synchronized (Board.class) {
        if (instance == null) {
          instance = new Board();
        }
      }
    }
    return instance;
  }

  public void setPieceIntoBoard(final int positionX, final int positionY, final Piece piece) {
    if (piece != null) {
      grid[positionX][positionY] = piece.getValueInTheboard();
      pieces[positionX][positionY] = piece;
      piecesList.add(piece);
    } else {
      grid[positionX][positionY] = 0;
      pieces[positionX][positionY] = null;
    }
  }

  public void updatePieces(
      final int fromX, final int fromY, final int toX, final int toY, final Piece piece) {
    lastMove = new Movement(fromX, fromY, toX, toY, piece);
    lastMoves.add(lastMove);
    if (pieces[toX][toY] != null) {
      died = pieces[toX][toY];
      deadPieces.add(died);
      piecesList.remove(died);
      Game.AllPieces.remove(died);
      Game.fillPieces();
    } else {
      deadPieces.add(null);
    }
    grid[fromX][fromY] = 0;
    grid[toX][toY] = piece.getValueInTheboard();
    pieces[fromX][fromY] = null;
    pieces[toX][toY] = piece;
  }

  public void undoMove() {
    if (!lastMoves.isEmpty()) {
      final Movement move = lastMoves.pop();
      final Piece dead = deadPieces.pop();
      grid[move.fromX][move.fromY] = move.getPiece().getValueInTheboard();
      pieces[move.fromX][move.fromY] = move.getPiece();

      move.getPiece().setXcord(move.fromX);
      move.getPiece().setYcord(move.fromY);

      if (move.getPiece() instanceof Pawn) {
        if (move.getPiece().getYcord() == (move.getPiece().isWhite() ? 6 : 1)) {
          ((Pawn) move.getPiece()).setFirstMove(true);
        }
      }

      if (move.getPiece() instanceof Rook) {
        if (((Rook) move.getPiece()).isJustMoved()) {
          ((Rook) move.getPiece()).setHasMoved(false);
          ((Rook) move.getPiece()).setJustMoved(false);
        }
      }

      if (dead != null) {
        Game.AllPieces.add(dead);
        Game.fillPieces();
        grid[move.toX][move.toY] = dead.getValueInTheboard();
        pieces[move.toX][move.toY] = dead;
        dead.setXcord(move.getToX());
        dead.setYcord(move.getToY());

      } else {
        grid[move.toX][move.toY] = 0;
        pieces[move.toX][move.toY] = dead;
      }

      Game.changeSide();
    }
  }

  public Piece getPiece(final int positionX, final int positionY) {
    return pieces[positionX][positionY];
  }

  public int[][] getGrid() {
    return grid;
  }

  public void setGrid(final int[][] grid) {
    this.grid = grid;
  }

  public int getXY(final int positionX, final int positionY) {
    return grid[positionX][positionY];
  }

  public void setXY(final int positionX, final int positionY, final int value) {
    grid[positionX][positionY] = value;
  }

  public Board getNewBoard() {
    final Board newBoard = new Board();
    for (int i = 0; i < ROWS; i++) {
      for (int j = 0; j < COLUMNS; j++) {
        final Piece piece = this.getPiece(i, j);
        if (piece != null) {
          newBoard.setPieceIntoBoard(i, j, piece.getClone());
        }
      }
    }
    return newBoard;
  }

  public void printBoard() {
    for (int i = 0; i < ROWS; i++) {
      for (int j = 0; j < COLUMNS; j++) {
        System.out.print(grid[j][i] + "  ");
      }
      System.out.println();
    }
  }
}
