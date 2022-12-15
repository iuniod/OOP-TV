package command;

import input.action.Action;

public abstract class AbstractFactory {

  public abstract Command getAction(final Action action);
}
