package tictactoe

import java.awt._

import alphabeta.engine.{Action, AlphaBeta, Player}
import org.junit.Assert.assertTrue
import org.junit.Test

/**
  * Created by Abdelrahman Sayed on 01/09/16.
  */
class TestCases {
  @Test def testCheckWinnersAtRows() {
    val X: Player = Player(0, null, Color.yellow, isHuman = true, isPlayerTurn = true, null)
    val O: Player = Player(0, null, Color.yellow, isHuman = false, isPlayerTurn = false, null)
    val G: TicTacToe = TicTacToe(X, O)
    val Positions: Array[Array[TicTacToePosition]] = Array.ofDim[TicTacToePosition](3, 3)
    for (i <- 0 to 2)
      for (z <- 0 to 2)
        Positions(i)(z) = TicTacToePosition(X, O)
    Positions(0)(0).applyActionForPlayer(O)
    Positions(0)(1).applyActionForPlayer(O)
    Positions(0)(2).applyActionForPlayer(O)
    G.setBoard(Positions)
    assertTrue("Check Row1", G.checkWinner(O))
    Positions(0)(0).reverseAction()
    Positions(0)(1).reverseAction()
    Positions(0)(2).reverseAction()
    Positions(1)(0).applyActionForPlayer(X)
    Positions(1)(1).applyActionForPlayer(X)
    Positions(1)(2).applyActionForPlayer(X)
    assertTrue("Check Row2", G.checkWinner(X))
    Positions(1)(0).reverseAction()
    Positions(1)(1).reverseAction()
    Positions(1)(2).reverseAction()
    Positions(2)(0).applyActionForPlayer(X)
    Positions(2)(1).applyActionForPlayer(X)
    Positions(2)(2).applyActionForPlayer(X)
    assertTrue("Check Row3", G.checkWinner(X))
  }

  @Test def testCheckWinnersAtColumns() {
    val X: Player = Player(0, null, Color.yellow, isHuman = true, isPlayerTurn = true, null)
    val O: Player = Player(0, null, Color.yellow, isHuman = false, isPlayerTurn = false, null)
    val G: TicTacToe = TicTacToe(X, O)
    val Positions: Array[Array[TicTacToePosition]] = Array.ofDim[TicTacToePosition](3, 3)
    for (i <- 0 to 2)
      for (z <- 0 to 2)
        Positions(i)(z) = TicTacToePosition(X, O)
    Positions(0)(0).applyActionForPlayer(O)
    Positions(1)(0).applyActionForPlayer(O)
    Positions(2)(0).applyActionForPlayer(O)
    G.setBoard(Positions)
    assertTrue("Check Column1", G.checkWinner(O))
    Positions(0)(0).reverseAction()
    Positions(1)(0).reverseAction()
    Positions(2)(0).reverseAction()
    Positions(0)(1).applyActionForPlayer(X)
    Positions(1)(1).applyActionForPlayer(X)
    Positions(2)(1).applyActionForPlayer(X)
    assertTrue("Check Column2", G.checkWinner(X))
    Positions(0)(1).reverseAction()
    Positions(1)(1).reverseAction()
    Positions(2)(1).reverseAction()
    Positions(0)(2).applyActionForPlayer(X)
    Positions(1)(2).applyActionForPlayer(X)
    Positions(2)(2).applyActionForPlayer(X)
    assertTrue("Check Column3", G.checkWinner(X))
  }

  @Test def testCheckWinnersAtDiagonals() {
    val X: Player = Player(0, null, Color.yellow, isHuman = true, isPlayerTurn = true, null)
    val O: Player = Player(0, null, Color.yellow, isHuman = false, isPlayerTurn = false, null)
    val G: TicTacToe = TicTacToe(X, O)
    val Positions: Array[Array[TicTacToePosition]] = Array.ofDim[TicTacToePosition](3, 3)
    for (i <- 0 to 2)
      for (z <- 0 to 2)
        Positions(i)(z) = TicTacToePosition(X, O)
    Positions(0)(0).applyActionForPlayer(O)
    Positions(1)(1).applyActionForPlayer(O)
    Positions(2)(2).applyActionForPlayer(O)
    G.setBoard(Positions)
    assertTrue("Check LeftDia", G.checkWinner(O))
    Positions(0)(0).reverseAction()
    Positions(1)(1).reverseAction()
    Positions(2)(2).reverseAction()
    Positions(2)(0).applyActionForPlayer(X)
    Positions(1)(1).applyActionForPlayer(X)
    Positions(0)(2).applyActionForPlayer(X)
    assertTrue("Check RightDia", G.checkWinner(X))
  }

  @Test def checkBestPositionLeftDiagonal() {
    val X: Player = Player(0, null, Color.yellow, isHuman = true, isPlayerTurn = true, null)
    val O: Player = Player(0, null, Color.yellow, isHuman = false, isPlayerTurn = false, null)
    val G: TicTacToe = TicTacToe(X, O)
    val Positions: Array[Array[TicTacToePosition]] = Array.ofDim[TicTacToePosition](3, 3)
    for (i <- 0 to 2)
      for (z <- 0 to 2) {
        Positions(i)(z) = TicTacToePosition(X, O)
        Positions(i)(z).column = z
        Positions(i)(z).row = i
      }
    Positions(0)(0).applyActionForPlayer(X)
    Positions(1)(1).applyActionForPlayer(X)
    G.setBoard(Positions)
    val AB: AlphaBeta = AlphaBeta(X, O)
    assertTrue("Pos should be 2 2", AB.getBestMove(G, 1, O) == Positions(2)(2))
  }

  @Test def checkBestPositionRightDiagonal() {
    val X: Player = Player(0, null, Color.yellow, isHuman = true, isPlayerTurn = true, null)
    val O: Player = Player(0, null, Color.yellow, isHuman = false, isPlayerTurn = false, null)
    val G: TicTacToe = TicTacToe(X, O)
    val Positions: Array[Array[TicTacToePosition]] = Array.ofDim[TicTacToePosition](3, 3)
    for (i <- 0 to 2)
      for (z <- 0 to 2) {
        Positions(i)(z) = TicTacToePosition(X, O)
        Positions(i)(z).column = z
        Positions(i)(z).row = i
      }
    Positions(0)(2).applyActionForPlayer(X)
    Positions(1)(1).applyActionForPlayer(X)
    G.setBoard(Positions)
    val AB: AlphaBeta = AlphaBeta(X, O)
    assertTrue("Pos should be 2 0", AB.getBestMove(G, 1, O) == Positions(2)(0))
  }

  @Test def checkBestPositionFirstRow() {
    val X: Player = Player(0, null, Color.yellow, isHuman = true, isPlayerTurn = true, null)
    val O: Player = Player(0, null, Color.yellow, isHuman = false, isPlayerTurn = false, null)
    val G: TicTacToe = TicTacToe(X, O)
    val Positions: Array[Array[TicTacToePosition]] = Array.ofDim[TicTacToePosition](3, 3)
    for (i <- 0 to 2)
      for (z <- 0 to 2) {
        Positions(i)(z) = TicTacToePosition(X, O)
        Positions(i)(z).column = z
        Positions(i)(z).row = i
      }
    Positions(0)(0).applyActionForPlayer(X)
    Positions(0)(1).applyActionForPlayer(X)
    G.setBoard(Positions)
    val AB: AlphaBeta = AlphaBeta(X, O)
    assertTrue("Pos Row 1should be 0 2", AB.getBestMove(G, 1, O) == Positions(0)(2))
  }

  @Test def checkBestPositionSecondRow() {
    val X: Player = Player(0, null, Color.yellow, isHuman = true, isPlayerTurn = true, null)
    val O: Player = Player(0, null, Color.yellow, isHuman = false, isPlayerTurn = false, null)
    val G: TicTacToe = TicTacToe(X, O)
    val Positions: Array[Array[TicTacToePosition]] = Array.ofDim[TicTacToePosition](3, 3)
    for (i <- 0 to 2)
      for (z <- 0 to 2) {
        Positions(i)(z) = TicTacToePosition(X, O)
        Positions(i)(z).column = z
        Positions(i)(z).row = i
      }
    Positions(1)(0).applyActionForPlayer(X)
    Positions(1)(1).applyActionForPlayer(X)
    G.setBoard(Positions)
    val AB: AlphaBeta = AlphaBeta(X, O)
    assertTrue("Pos Row 2 should be 1 2", AB.getBestMove(G, 1, O) == Positions(1)(2))
  }

  @Test def checkBestPositionThirdRow() {
    val X: Player = Player(0, null, Color.yellow, isHuman = true, isPlayerTurn = true, null)
    val O: Player = Player(0, null, Color.yellow, isHuman = false, isPlayerTurn = false, null)
    val G: TicTacToe = TicTacToe(X, O)
    val Positions: Array[Array[TicTacToePosition]] = Array.ofDim[TicTacToePosition](3, 3)
    for (i <- 0 to 2)
      for (z <- 0 to 2) {
        Positions(i)(z) = TicTacToePosition(X, O)
        Positions(i)(z).column = z
        Positions(i)(z).row = i
      }
    Positions(2)(0).applyActionForPlayer(X)
    Positions(2)(1).applyActionForPlayer(X)
    G.setBoard(Positions)
    val AB: AlphaBeta = AlphaBeta(X, O)
    val Tp: Action = AB.getBestMove(G, 1, O)
    assertTrue("Pos Row 3 should be 2 2", Tp == Positions(2)(2))
  }

  @Test def checkBestPositionColumn1() {
    val X: Player = Player(0, null, Color.yellow, isHuman = true, isPlayerTurn = true, null)
    val O: Player = Player(0, null, Color.yellow, isHuman = false, isPlayerTurn = false, null)
    val G: TicTacToe = TicTacToe(X, O)
    val Positions: Array[Array[TicTacToePosition]] = Array.ofDim[TicTacToePosition](3, 3)
    for (i <- 0 to 2)
      for (z <- 0 to 2) {
        Positions(i)(z) = TicTacToePosition(X, O)
        Positions(i)(z).column = z
        Positions(i)(z).row = i
      }
    Positions(0)(0).applyActionForPlayer(X)
    Positions(1)(0).applyActionForPlayer(X)
    G.setBoard(Positions)
    val AB: AlphaBeta = AlphaBeta(X, O)
    val Tp: Action = AB.getBestMove(G, 1, O)
    assertTrue("Pos Col 1 should be 2 0", Tp == Positions(2)(0))
  }

  @Test def checkBestPositionColumn2() {
    val X: Player = Player(0, null, Color.yellow, isHuman = true, isPlayerTurn = true, null)
    val O: Player = Player(0, null, Color.yellow, isHuman = false, isPlayerTurn = false, null)
    val G: TicTacToe = TicTacToe(X, O)
    val Positions: Array[Array[TicTacToePosition]] = Array.ofDim[TicTacToePosition](3, 3)
    for (i <- 0 to 2)
      for (z <- 0 to 2) {
        Positions(i)(z) = TicTacToePosition(X, O)
        Positions(i)(z).column = z
        Positions(i)(z).row = i
      }
    Positions(0)(1).applyActionForPlayer(X)
    Positions(1)(1).applyActionForPlayer(X)
    G.setBoard(Positions)
    val AB: AlphaBeta = AlphaBeta(X, O)
    val Tp: Action = AB.getBestMove(G, 1, O)
    assertTrue("Pos Col 2 should be 2 1", Tp == Positions(2)(1))
    Tp.applyActionForPlayer(O)
  }

  @Test def checkBestPositionColumn3() {
    val X: Player = Player(0, null, Color.yellow, isHuman = true, isPlayerTurn = true, null)
    val O: Player = Player(0, null, Color.yellow, isHuman = false, isPlayerTurn = false, null)
    val G: TicTacToe = TicTacToe(X, O)
    val Positions: Array[Array[TicTacToePosition]] = Array.ofDim[TicTacToePosition](3, 3)
    for (i <- 0 to 2)
      for (z <- 0 to 2) {
        Positions(i)(z) = TicTacToePosition(X, O)
        Positions(i)(z).column = z
        Positions(i)(z).row = i
      }
    Positions(0)(2).applyActionForPlayer(X)
    Positions(1)(2).applyActionForPlayer(X)
    G.setBoard(Positions)
    val AB: AlphaBeta = AlphaBeta(X, O)
    val Tp: Action = AB.getBestMove(G, 1, O)
    assertTrue("Pos Col 3 should be 2 2", Tp == Positions(2)(2))
  }

  @Test def checkBestPositionPreventOtherPlayerFromWinning() {
    val X: Player = Player(0, null, Color.yellow, isHuman = true, isPlayerTurn = true, null)
    val O: Player = Player(0, null, Color.yellow, isHuman = false, isPlayerTurn = false, null)
    val G: TicTacToe = TicTacToe(X, O)
    val Positions: Array[Array[TicTacToePosition]] = Array.ofDim[TicTacToePosition](3, 3)
    var i: Int = 0
    for (i <- 0 to 2)
      for (z <- 0 to 2) {
        Positions(i)(z) = TicTacToePosition(X, O)
        Positions(i)(z).column = z
        Positions(i)(z).row = i
      }
    Positions(0)(2).applyActionForPlayer(X)
    Positions(0)(1).applyActionForPlayer(O)
    Positions(1)(2).applyActionForPlayer(X)
    G.setBoard(Positions)
    val AB: AlphaBeta = AlphaBeta(X, O)
    val Tp: Action = AB.getBestMove(G, 3, O)
    assertTrue("POS 2 2", Tp == Positions(2)(2))
  }
}