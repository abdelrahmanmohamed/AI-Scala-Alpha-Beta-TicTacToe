package alphabeta.engine

import java.awt.Color

/**
  * Created by Abdelrahman Sayed on 01/09/16.
  */
case class Player(id: Int, name: String, var color: Color, isHuman: Boolean, var isPlayerTurn: Boolean, var opponent: Player = null) {
  /**
    * setter for java classes
    *
    * @param opponent opponent player
    */
  def setOpponent(opponent: Player) = {
    this.opponent = opponent
  }
}