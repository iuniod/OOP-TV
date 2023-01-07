package command;


import command.commands.navigate.BackPage;
import command.commands.navigate.NextPage;
import input.action.Action;

import static database.Constants.BACK;
import static database.Constants.CHANGE_PAGE;

public final class ChangePageFactory extends AbstractFactory {

  @Override
  public Command getAction(final Action action) {
    switch (action.getType().toLowerCase()) {
      case CHANGE_PAGE:
        return new NextPage(action);
      case BACK:
        return new BackPage(action);
      default:
        return null;
    }
  }
}
