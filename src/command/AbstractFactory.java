package command;

import input.action.Action;

public abstract class AbstractFactory {

  /**
   * @param action the action that will be executed
   * @return the command that will execute the action - OnPageFactory or ChangePageFactory
   */
  public abstract Command getAction(Action action);
}
