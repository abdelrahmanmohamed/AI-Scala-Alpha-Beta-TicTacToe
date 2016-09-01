package tictactoe

import alphabeta.engine.{Action, Player}

/**
  * Created by Abdelrahman Sayed on 01/09/16.
  */
object TicTacToePosition {
  def apply(X: Player, O: Player): TicTacToePosition = new TicTacToePosition(X, O)
}

class TicTacToePosition(val X: Player, val O: Player) extends Action {
  /**
    * variable that carries value of the position
    */
  var Piece: Char = 0
  var row: Int = 0
  var column: Int = 0

  override def applyActionForPlayer(player: Player) = {
    if (player == X) {
      X.isPlayerTurn = false
      O.isPlayerTurn = true
      Piece = 'X'
    }
    else {
      X.isPlayerTurn = true
      O.isPlayerTurn = false
      Piece = 'O'
    }
  }

  override def reverseAction() = {
    Piece = 0
  }

  override def equals(obj2: scala.Any): Boolean = {
    val obj: TicTacToePosition = obj2.asInstanceOf[TicTacToePosition]
    super.equals(obj) && X.equals(obj.X) && O.equals(obj.O) && Piece == obj.Piece && row == obj.row && column.equals(column)
  }
}
