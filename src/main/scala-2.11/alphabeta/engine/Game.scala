package alphabeta.engine

/**
  * Created by Abdelrahman Sayed on 01/09/16.
  */
abstract class Game {
  def getPossibleActions(player: Player): Set[Action]

  def checkTerminalState: Boolean

  def evaluate(P: Player): Double
}
