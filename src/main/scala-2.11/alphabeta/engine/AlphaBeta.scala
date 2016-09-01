package alphabeta.engine

import scala.util.control.Breaks

/**
  * Created by Abdelrahman Sayed on 01/09/16.
  */
object AlphaBeta {
  def apply(PlayerOne: Player, PlayerTwo: Player): AlphaBeta = new AlphaBeta(PlayerOne, PlayerTwo)
}

class AlphaBeta(PlayerOne: Player, PlayerTwo: Player) {
  var computer: Player = _
  var human: Player = _

  /**
    * miniMax algorithm for searching for the best move
    *
    * @param game   is your game object that implements Interface Game
    * @param depth  is the max level for searching recursively
    * @param player is the player which the algorithm search for the best move for
    *               him
    * @return object of Position for the best move
    */
  def getBestMove(game: Game, depth: Int, player: Player): Action = {
    computer = player
    if (player == PlayerOne) {
      human = PlayerTwo
      computer = PlayerOne
    }
    else {
      human = PlayerOne
      computer = PlayerTwo
    }
    val bestMove: ActionEvaluation = miniMax(game, depth, player, Double.NegativeInfinity, Double.PositiveInfinity)
    bestMove.position
  }

  /**
    * miniMax algorithm for searching for the best move
    *
    * @param game   is your game object that implements Interface Game
    * @param depth  is the max level for searching recursively
    * @param player is the player which the algorithm search for the best move for
    *               him
    * @param alpha  is the Evaluation for the player move
    * @param beta   is the Evaluation for the opponent move
    * @return object of PositionEvaluation for the best move
    */
  private def miniMax(game: Game, depth: Int, player: Player, alpha: Double, beta: Double): ActionEvaluation = {
    var bestAction: Action = null
    if (game.checkTerminalState || depth == 0) {
      if (player == computer)
        ActionEvaluation(bestAction, game.evaluate(player))
      else
        ActionEvaluation(bestAction, -game.evaluate(player))
    }
    else {
      val nextPlayer = if (player == computer) human else computer
      var updatedAlpha = alpha
      var updatedBeta = beta
      val availableActions = game getPossibleActions player
      val loop = new Breaks
      loop.breakable {
        availableActions.foreach {
          action => {
            action applyActionForPlayer player
            lazy val currentAction: ActionEvaluation = miniMax(game, depth - 1, nextPlayer, updatedAlpha, updatedBeta)
            if (player == computer) {
              if (currentAction.evaluation > updatedAlpha) {
                updatedAlpha = currentAction.evaluation
                bestAction = action
              }
            }
            else {
              if (currentAction.evaluation < updatedBeta) {
                updatedBeta = currentAction.evaluation
                bestAction = action
              }
            }
            action.reverseAction()
            if (updatedAlpha >= updatedBeta) {
              loop.break
            }
          }
        }
      }
      if (player == computer)
        ActionEvaluation(bestAction, updatedAlpha)
      else
        ActionEvaluation(bestAction, updatedBeta)
    }
  }

  /**
    * Inner That Carry Position And it's Evaluation
    *
    * @param position   is the position
    * @param evaluation is the evaluation Value for the position
    */
  case class ActionEvaluation(var position: Action, var evaluation: Double) extends Ordered[ActionEvaluation] {
    override def compare(that: ActionEvaluation): Int = {
      evaluation.compareTo(that.evaluation)
    }
  }

}
