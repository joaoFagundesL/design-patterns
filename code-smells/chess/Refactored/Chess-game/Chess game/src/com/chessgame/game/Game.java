package com.chessgame.game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.chessgame.board.Board;
import com.chessgame.board.Movement;
import com.chessgame.pieces.King;
import com.chessgame.pieces.Pawn;
import com.chessgame.pieces.Piece;
import com.chessgame.pieces.PieceImages;

public class Game {
  public static Board board = Board.getInstance();

  static King wk;
  static King bk;
  static ArrayList<Piece> wPieces = new ArrayList<Piece>();
  static ArrayList<Piece> bPieces = new ArrayList<Piece>();

  static boolean player = true;
  public Piece active = null;
  public static boolean drag = false;
  public static ArrayList<Piece> AllPieces = new ArrayList<Piece>();

  ArrayList<Movement> allPossiblesMoves = new ArrayList<Movement>();

  static List<Movement> allPlayersMove = new ArrayList<Movement>();
  public static List<Movement> allEnemysMove = new ArrayList<Movement>();
  private static boolean gameOver = false;


  public Game() {
    new PieceImages();
    loadFenPosition("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
    start();
  }

  public void start() {
    fillPieces();
    generatePlayersTurnMoves(board);
    generateEnemysMoves(board);
    checkPlayersLegalMoves();
  }

  public void draw(Graphics g, int x, int y, JPanel panel) {
    drawBoard(g);
    drawPiece(g, panel);
    drawPossibleMoves(g, panel);
    drag(active, x, y, g, panel);
    drawKingInCheck(player, g, panel);
  }

  public static void generatePlayersTurnMoves(Board board) {
    allPlayersMove = new ArrayList<Movement>();
    for (Piece p : AllPieces) {
      if (p.isWhite() == player) {
        p.fillAllPseudoLegalMoves(board);
        allPlayersMove.addAll(p.getMoves());
      }
    }
  }

  public static void generateEnemysMoves(Board board) {
    allEnemysMove = new ArrayList<Movement>();
    for (Piece p : AllPieces) {
      if (p.isWhite() != player) {
        p.fillAllPseudoLegalMoves(board);
        allEnemysMove.addAll(p.getMoves());
      }
    }
  }

  public static void changeSide() {
    player = !player;
    generateEnemysMoves(board);
    generatePlayersTurnMoves(board);
    checkPlayersLegalMoves();
    checkMate();
  }

  public void randomPlay() {
    if (gameOver) {
      return;
    }
    if (!player) {
      Random r = new Random();
      active = bPieces.get(r.nextInt(bPieces.size()));
      while (active.getMoves().isEmpty()) {
        active = bPieces.get(r.nextInt(bPieces.size()));
      }
      Movement m = active.getMoves().get(r.nextInt(active.getMoves().size()));
      move(m.getToX(), m.getToY());
    }
  }

  public void selectPiece(int x, int y) {
    if (active == null && board.getPiece(x, y) != null && board.getPiece(x, y).isWhite() == player) {
      active = board.getPiece(x, y);
    }
  }

  public static void checkMate() {
    List<Piece> currentPieces = player ? wPieces : bPieces;
    King currentKing = player ? wk : bk;
    String winningColor = player ? "white" : "black";

    boolean hasValidMove = currentPieces.stream()
    .anyMatch(p -> !p.getMoves().isEmpty());

    if (!hasValidMove) {
      if (currentKing.isInCheck()) {
        JOptionPane.showMessageDialog(null, "Checkmate! " + winningColor + " wins");
      } else {
        JOptionPane.showMessageDialog(null, "Stalemate");
      }
      gameOver = true;
    }
  }


  public static void checkPlayersLegalMoves() {
    List<Piece> pieces = null;
    if (player) {
      pieces = wPieces;
    } else {
      pieces = bPieces;
    }
    for (Piece p : pieces) {
      checkLegalMoves(p);
    }
  }

  public static void checkLegalMoves(Piece piece) {
    List<Movement> movesToRemove = new ArrayList<>();
    Board clonedBoard;
    Piece clonedActive;
    Piece king = piece.isWhite() ? wk : bk;
    List<Piece> enemyPieces = piece.isWhite() ? bPieces : wPieces;

    for (Movement move : piece.getMoves()) {
      clonedBoard = board.getNewBoard();
      clonedActive = piece.getClone();

      clonedActive.makeMove(move.getToX(), move.getToY(), clonedBoard);

      if (isMoveThreateningKing(clonedActive, move, clonedBoard, enemyPieces, king)) {
        movesToRemove.add(move);
      }
    }

    piece.getMoves().removeAll(movesToRemove);
  }

  private static boolean isMoveThreateningKing(Piece activePiece, Movement move, Board board, List<Piece> enemyPieces, Piece king) {
    for (Piece enemyP : enemyPieces) {
      Piece clonedEnemyPiece = enemyP.getClone();
      clonedEnemyPiece.fillAllPseudoLegalMoves(board);

      for (Movement enemyMove : clonedEnemyPiece.getMoves()) {
        if (isMoveAttackingKing(activePiece, enemyMove, king) || isKingMovingIntoCheck(activePiece, enemyMove)) {
          return true;
        }
      }
    }
    return false;
  }

  private static boolean isMoveAttackingKing(Piece activePiece, Movement enemyMove, Piece king) {
    return !(activePiece instanceof King) &&
    enemyMove.getToX() == king.getXcord() &&
    enemyMove.getToY() == king.getYcord();
  }

  private static boolean isKingMovingIntoCheck(Piece activePiece, Movement enemyMove) {
    return activePiece instanceof King &&
    enemyMove.getToX() == activePiece.getXcord() &&
    enemyMove.getToY() == activePiece.getYcord();
  }


  public void drag(Piece piece, int x, int y, Graphics g, JPanel panel) {
    if (piece != null && drag == true) {
      piece.draw2(g, player, x, y, panel);
    }
  }

  public void move(int x, int y) {
    if (active != null) {
      if (active.makeMove(x, y, board)) {
        tryToPromote(active);
        changeSide();
        active = null;
      }
      drag = false;
    }
  }

  public void drawKingInCheck(boolean isWhite, Graphics g, JPanel panel) {
    g.setColor(Color.RED);
    if (isWhite && wk.isInCheck()) {
      g.drawRect(wk.getXcord() * Piece.size, wk.getYcord() * Piece.size, Piece.size, Piece.size);
    } else if (bk.isInCheck()) {
      g.drawRect(bk.getXcord() * Piece.size, bk.getYcord() * Piece.size, Piece.size, Piece.size);
    }
    panel.revalidate();
    panel.repaint();
  }

  public void drawBoard(Graphics g) {
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if ((i + j) % 2 == 1) {
          g.setColor(new Color(118, 150, 86));
        } else {
          g.setColor(new Color(238, 238, 210));
        }
        g.fillRect(i * Piece.size, j * Piece.size, Piece.size, Piece.size);
      }
    }
  }

  public void tryToPromote(Piece p) {
    if (p instanceof Pawn) {
      if (((Pawn) p).madeToTheEnd()) {
        choosePiece(p, showMessageForPromotion());
      }
    }
  }

  public int showMessageForPromotion() {
    Object[] options = { "Queen", "Rook", "Knight", "Bishop" };

    drag = false;
    return JOptionPane.showOptionDialog(null, "Choose Piece To Promote to", null, JOptionPane.YES_NO_CANCEL_OPTION,
      JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
  }

  public static void choosePiece(Piece p, int choice) {
    List<Piece> playerPieces = p.isWhite() ? wPieces : bPieces;
    PieceFactory factory = new ConcretePieceFactory();

    AllPieces.remove(p);
    playerPieces.remove(p);

    char newPieceType;
    switch (choice) {
      case 0:
      newPieceType = 'Q';
      break;
      case 1:
      newPieceType = 'R';
      break;
      case 2:
      newPieceType = 'N';
      break;
      case 3:
      newPieceType = 'B';
      break;
      default:
      newPieceType = 'Q';
      break;
    }

    Piece newPiece = factory.createPiece(newPieceType, p.getXcord(), p.getYcord(), p.isWhite());

    AllPieces.add(newPiece);
    playerPieces.add(newPiece);

    fillPieces();
  }

  public void drawPossibleMoves(Graphics g, JPanel panel) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setStroke(new BasicStroke(3));
    if (active != null) {
      active.showMoves(g2, panel);
    }

  }

  public void drawPiece(Graphics g, JPanel panel) {
    for (Piece p : AllPieces) {
      p.draw(g, false, panel);
    }

  }

  public void loadFenPosition(String fenString) {
    String[] parts = fenString.split(" ");
    String position = parts[0];
    int row = 0, col = 0;
    for (char c : position.toCharArray()) {
      if (c == '/') {
        row++;
        col = 0;
      }
      if (Character.isLetter(c)) {
        if (Character.isLowerCase(c)) {
          addToBoard(col, row, c, false);
        } else {
          addToBoard(col, row, c, true);
        }
        col++;
      }
      if (Character.isDigit(c)) {
        col += Integer.parseInt(String.valueOf(c));
      }
    }

    if (parts[1].equals("w")) {
      player = true;
    } else {
      player = false;
    }

  }

  public static void fillPieces() {
    wPieces = new ArrayList<Piece>();
    bPieces = new ArrayList<Piece>();
    for (Piece p : AllPieces) {
      if (p.isWhite()) {
        wPieces.add(p);
      } else {
        bPieces.add(p);
      }
    }
  }

  public void addToBoard(int x, int y, char c, boolean isWhite) {
    PieceFactory factory = new ConcretePieceFactory();
    Piece piece = factory.createPiece(c, x, y, isWhite);
    AllPieces.add(piece);
  }

}
