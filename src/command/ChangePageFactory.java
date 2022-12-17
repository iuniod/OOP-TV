package command;


import command.commands.ChangePage;
import input.action.Action;

public final class ChangePageFactory extends AbstractFactory {

  @Override
  public Command getAction(final Action action) {
    return new ChangePage(action);
  }
}
