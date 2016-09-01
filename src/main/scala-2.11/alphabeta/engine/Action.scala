package alphabeta.engine

/**
  * Created by Abdelrahman Sayed on 01/09/16.
  */
abstract class Action {
  /**
    * apply action for player
    *
    * @param player player to handle the action
    */
  def applyActionForPlayer(player: Player)

  /**
    * undo action
    */
  def reverseAction()
}
