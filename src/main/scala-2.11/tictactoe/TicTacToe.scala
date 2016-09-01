package tictactoe

import alphabeta.engine.{Action, Game, Player}

/**
  * Created by Abdelrahman Sayed on 01/09/16.
  */
object TicTacToe {
  def apply(X: Player, O: Player): TicTacToe = new TicTacToe(X, O)
}

class TicTacToe(var X: Player, var O: Player) extends Game {
  /**
    * board for the game consist of TicTacToePosition
    */
  private var Board: Array[Array[TicTacToePosition]] = Array.ofDim[TicTacToePosition](3, 3)

  for (i <- 0 to 2) {
    for (j <- 0 to 2) {
      Board(i)(j) = new TicTacToePosition(X, O)
    }
  }

  /**
    * variable to check winning state
    */
  private var count: Int = 0

  /**
    * Provide the possible positions for specific player based on game rules
    * which is provided in your implementation
    *
    * @param player is the player who the method search for possible position for
    * @return ArrayList of Positions
    */
  override def getPossibleActions(player: Player): Set[Action] = {
    var possibleActions = Set.empty[Action]
    for (row <- Board)
      for (cell <- row)
        if (cell.Piece == 0)
          possibleActions += cell
    possibleActions
  }

  /**
    * check if the game reached Terminal state terminal state could be draw or
    * ended with winner
    *
    * @return true if terminal state reached false otherwise
    */
  override def checkTerminalState: Boolean = {
    if (checkWinner(X) || checkWinner(O) || checkDraw) return true
    false
  }

  /**
    * check if there is no winner
    *
    * @return true if no winner
    */
  def checkDraw: Boolean = {
    for (row <- Board)
      for (cell <- row)
        if (cell.Piece == 0)
          return false
    if (checkWinner(X)) return false
    if (checkWinner(O)) return false
    true
  }

  /**
    * check if player p is winner
    *
    * @return true if win else false
    */
  def checkWinner(P: Player): Boolean = {
    var Piece: Char = 0
    if (P == O) Piece = 'O'
    else Piece = 'X'
    if (LeftDiagonalCheck(Piece)) return true
    if (RightDiagonalCheck(Piece)) return true
    if (RowCheck(Piece)) return true
    if (ColumnCheck(Piece)) return true
    false
  }

  /**
    * check if the piece of a player makes win state in the left diagonal
    *
    * @param Piece player piece which X or O
    * @return true if left Diagonal
    */
  def LeftDiagonalCheck(Piece: Char): Boolean = {
    count = 0
    for (i <- 0 to 2) {
      if (Board(i)(i).Piece == Piece) {
        count += 1
      }
    }
    if (count == 3) return true
    false
  }

  /**
    * check if the piece of a player makes win state in the right diagonal
    *
    * @param Piece player piece which X or O
    * @return true if right Diagonal Win
    */
  def RightDiagonalCheck(Piece: Char): Boolean = {
    count = 0
    var i: Int = 2
    var z: Int = 0
    while (i >= 0 && z < 3) {
      if (Board(i)(z).Piece == Piece) {
        count += 1
      }
      i -= 1
      z += 1
    }
    if (count == 3)
      return true
    false
  }

  /**
    * check if the piece of a player makes win state in any row
    *
    * @param Piece player piece which X or O
    * @return true if Row Win occurred
    */
  def RowCheck(Piece: Char): Boolean = {
    for (i <- 0 to 2) {
      count = 0
      for (z <- 0 to 2) {
        if (Board(i)(z).Piece == Piece) {
          count += 1
        }
      }
      if (count == 3) return true
    }
    false
  }

  /**
    * check if the piece of a player makes win state in any column
    *
    * @param Piece player piece which X or O
    * @return return true if Row Win occurred
    */
  def ColumnCheck(Piece: Char): Boolean = {
    for (i <- 0 to 2) {
      count = 0
      for (z <- 0 to 2) {
        if (Board(z)(i).Piece == Piece) {
          count += 1
        }
      }
      if (count == 3) return true

    }
    false
  }

  /**
    * evaluate the possibility of winning for Player P based on game rules
    *
    * @param P is the player to evaluate it's state
    * @return number that represent wining possibility
    */
  override def evaluate(P: Player): Double = {
    var evaluationValue: Int = 0
    evaluationValue += evaluateChance(0, 0, 1, 1, 2, 2, P) //LeftDiagonal
    evaluationValue += evaluateChance(0, 2, 1, 1, 2, 0, P) //RightDiagonal
    for (i <- 0 to 2)
      evaluationValue += evaluateChance(0, i, 1, i, 2, i, P) //Column
    for (i <- 0 to 2)
      evaluationValue += evaluateChance(i, 0, i, 1, i, 2, P) //Rows
    evaluationValue
  }

  /**
    * evaluate the possibility of winning for Player in row or column or one of
    * the diagonals
    *
    * @param P is the player to evaluate it's state
    * @return number that represent wining possibility
    */
  def evaluateChance(r1: Int, c1: Int, r2: Int, c2: Int, r3: Int, c3: Int, P: Player): Int = {
    var evaluationValue: Int = 0
    var PPiece: Char = 0
    var OPiece: Char = 0
    if (P == X) {
      PPiece = 'X'
      OPiece = 'O'
    }
    else {
      PPiece = 'O'
      OPiece = 'X'
    }

    if (Board(r1)(c1).Piece == PPiece)
      evaluationValue = 1
    else if (Board(r1)(c1).Piece == OPiece)
      evaluationValue = -1

    if (Board(r2)(c2).Piece == PPiece) {
      if (evaluationValue == 1)
        evaluationValue = 10
      else {
        if (evaluationValue == -1)
          return 0
        evaluationValue = 1
      }
    }
    else if (Board(r2)(c2).Piece == OPiece) {
      if (evaluationValue == -1)
        evaluationValue = -10
      else {
        if (evaluationValue == 1)
          return 0
        evaluationValue = -1
      }
    }
    if (Board(r3)(c3).Piece == PPiece) {
      if (evaluationValue > 0)
        evaluationValue *= 10
      else {
        if (evaluationValue < 0)
          return 0
        evaluationValue = 1
      }
    }
    else if (Board(r3)(c3).Piece == OPiece) {
      if (evaluationValue < 0)
        evaluationValue *= 10
      else {
        if (evaluationValue > 1)
          return 0
        evaluationValue = -1
      }
    }
    evaluationValue
  }

  /**
    * return the piece in position (row,column)
    *
    * @param row    row
    * @param column column
    * @return value of the position
    */
  def getPiece(row: Int, column: Int): Char = Board(row)(column).Piece

  /**
    * return the position object in position (row,column) of the board
    *
    * @param row    row
    * @param Column column
    * @return position
    */
  def getPositionAt(row: Int, Column: Int): TicTacToePosition = {
    Board(row)(Column)
  }

  /**
    * for Testing
    *
    * @param Board game board
    */
  def setBoard(Board: Array[Array[TicTacToePosition]]) {
    this.Board = Board
  }

  /**
    * for Testing
    *
    * @param X setPlayer
    */
  def setX(X: Player) {
    this.X = X
  }

  /**
    * for Testing
    *
    * @param O setPlayer
    */
  def setO(O: Player) {
    this.O = O
  }
}
