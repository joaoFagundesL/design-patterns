package com.chessgame.game;

import com.chessgame.board.Board;
import com.chessgame.board.Movement;
import com.chessgame.pieces.King;
import com.chessgame.pieces.Pawn;
import com.chessgame.pieces.Piece;
import com.chessgame.pieces.PieceImages;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game {
  public static Board board = Board.getInstance();

  static King wk;
  static King bk;
  static ArrayList<Piece> whitePieces = new ArrayList<Piece>();
  static ArrayList<Piece> blackPieces = new ArrayList<Piece>();

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

  public void draw(
      final Graphics graphics, final int positionX, final int positionY, final JPanel panel) {
    drawBoard(graphics);
    drawPiece(graphics, panel);
    drawPossibleMoves(graphics, panel);
    drag(active, positionX, positionY, graphics, panel);
    drawKingInCheck(player, graphics, panel);
  }

  public static void generatePlayersTurnMoves(final Board board) {
    allPlayersMove = new ArrayList<Movement>();
    for (final Piece p : AllPieces) {
      if (p.isWhite() == player) {
        p.fillAllPseudoLegalMoves(board);
        allPlayersMove.addAll(p.getMoves());
      }
    }
  }

  public static void generateEnemysMoves(final Board board) {
    allEnemysMove = new ArrayList<Movement>();
    for (final Piece p : AllPieces) {
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
      final Random random = new Random();
      active = blackPieces.get(random.nextInt(blackPieces.size()));
      while (active.getMoves().isEmpty()) {
        active = blackPieces.get(random.nextInt(blackPieces.size()));
      }
      final Movement m = active.getMoves().get(random.nextInt(active.getMoves().size()));
      move(m.getToX(), m.getToY());
    }
  }

  public void selectPiece(final int positionX, final int positionY) {
    if (active == null
        && board.getPiece(positionX, positionY) != null
        && board.getPiece(positionX, positionY).isWhite() == player) {
      active = board.getPiece(positionX, positionY);
    }
  }

  public static void checkMate() {
    final List<Piece> currentPieces = player ? whitePieces : blackPieces;
    final King currentKing = player ? wk : bk;
    final String winningColor = player ? "white" : "black";

    final boolean hasValidMove = currentPieces.stream().anyMatch(p -> !p.getMoves().isEmpty());

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
      pieces = whitePieces;
    } else {
      pieces = blackPieces;
    }
    for (final Piece piece : pieces) {
      checkLegalMoves(piece);
    }
  }

  public static void checkLegalMoves(final Piece piece) {
    final List<Movement> movesToRemove = new ArrayList<>();
    Board clonedBoard;
    Piece clonedActive;
    final Piece king = piece.isWhite() ? wk : bk;
    final List<Piece> enemyPieces = piece.isWhite() ? blackPieces : whitePieces;

    for (final Movement move : piece.getMoves()) {
      clonedBoard = board.getNewBoard();
      clonedActive = piece.getClone();

      clonedActive.makeMove(move.getToX(), move.getToY(), clonedBoard);

      if (isMoveThreateningKing(clonedActive, move, clonedBoard, enemyPieces, king)) {
        movesToRemove.add(move);
      }
    }

    piece.getMoves().removeAll(movesToRemove);
  }

  private static boolean isMoveThreateningKing(
      final Piece activePiece,
      final Movement move,
      final Board board,
      final List<Piece> enemyPieces,
      final Piece king) {
    for (final Piece enemyPiece : enemyPieces) {
      final Piece clonedEnemyPiece = enemyPiece.getClone();
      clonedEnemyPiece.fillAllPseudoLegalMoves(board);

      for (final Movement enemyMove : clonedEnemyPiece.getMoves()) {
        if (isMoveAttackingKing(activePiece, enemyMove, king)
            || isKingMovingIntoCheck(activePiece, enemyMove)) {
          return true;
        }
      }
    }
    return false;
  }

  private static boolean isMoveAttackingKing(
      final Piece activePiece, final Movement enemyMove, final Piece king) {
    return !(activePiece instanceof King)
        && enemyMove.getToX() == king.getXcord()
        && enemyMove.getToY() == king.getYcord();
  }

  private static boolean isKingMovingIntoCheck(final Piece activePiece, final Movement enemyMove) {
    return activePiece instanceof King
        && enemyMove.getToX() == activePiece.getXcord()
        && enemyMove.getToY() == activePiece.getYcord();
  }

  public void drag(
      final Piece piece,
      final int positionX,
      final int positionY,
      final Graphics graphics,
      final JPanel panel) {
    if (piece != null && drag == true) {
      piece.draw2(graphics, player, positionX, positionY, panel);
    }
  }

  public void move(final int positionX, final int positionY) {
    if (active != null) {
      if (active.makeMove(positionX, positionY, board)) {
        tryToPromote(active);
        changeSide();
        active = null;
      }
      drag = false;
    }
  }

  public void drawKingInCheck(final boolean isWhite, final Graphics graphics, final JPanel panel) {
    graphics.setColor(Color.RED);
    if (isWhite && wk.isInCheck()) {
      graphics.drawRect(
          wk.getXcord() * Piece.size, wk.getYcord() * Piece.size, Piece.size, Piece.size);
    } else if (bk.isInCheck()) {
      graphics.drawRect(
          bk.getXcord() * Piece.size, bk.getYcord() * Piece.size, Piece.size, Piece.size);
    }
    panel.revalidate();
    panel.repaint();
  }

  public void drawBoard(final Graphics graphics) {
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if ((i + j) % 2 == 1) {
          graphics.setColor(new Color(118, 150, 86));
        } else {
          graphics.setColor(new Color(238, 238, 210));
        }
        graphics.fillRect(i * Piece.size, j * Piece.size, Piece.size, Piece.size);
      }
    }
  }

  public void tryToPromote(final Piece piece) {
    if (piece instanceof Pawn) {
      if (((Pawn) piece).madeToTheEnd()) {
        choosePiece(piece, showMessageForPromotion());
      }
    }
  }

  public int showMessageForPromotion() {
    final Object[] options = {"Queen", "Rook", "Knight", "Bishop"};

    drag = false;
    return JOptionPane.showOptionDialog(
        null,
        "Choose Piece To Promote to",
        null,
        JOptionPane.YES_NO_CANCEL_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        options,
        options[0]);
  }

  public static void choosePiece(final Piece piece, final int choice) {
    final List<Piece> playerPieces = piece.isWhite() ? whitePieces : blackPieces;
    final PieceFactory factory = new ConcretePieceFactory();

    AllPieces.remove(piece);
    playerPieces.remove(piece);

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

    final Piece newPiece =
        factory.createPiece(newPieceType, piece.getXcord(), piece.getYcord(), piece.isWhite());

    AllPieces.add(newPiece);
    playerPieces.add(newPiece);

    fillPieces();
  }

  public void drawPossibleMoves(final Graphics graphics, final JPanel panel) {
    final Graphics2D graphics2D = (Graphics2D) graphics;
    graphics2D.setStroke(new BasicStroke(3));
    if (active != null) {
      active.showMoves(graphics2D, panel);
    }
  }

  public void drawPiece(final Graphics graphics, final JPanel panel) {
    for (final Piece piece : AllPieces) {
      piece.draw(graphics, false, panel);
    }
  }

  public void loadFenPosition(final String fenString) {
    final String[] parts = fenString.split(" ");
    final String position = parts[0];
    int row = 0, col = 0;
    for (final char c : position.toCharArray()) {
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
    whitePieces = new ArrayList<Piece>();
    blackPieces = new ArrayList<Piece>();
    for (final Piece p : AllPieces) {
      if (p.isWhite()) {
        whitePieces.add(p);
      } else {
        blackPieces.add(p);
      }
    }
  }

  public void addToBoard(
      final int positionX, final int positionY, final char c, final boolean isWhite) {
    final PieceFactory factory = new ConcretePieceFactory();
    final Piece piece = factory.createPiece(c, positionX, positionY, isWhite);
    AllPieces.add(piece);
  }
}
