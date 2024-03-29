package output;

import input.action.Action;
import output.error.Error;
import output.success.Success;

import static database.Constants.*;

public class OutputFactory {
  protected OutputFactory() {
  }
  /** Factory method for creating an Output object according to the error message. */
  public static Output getOutput(final String errorMessage, final Action action) {
    switch (errorMessage) {
      case ERROR:
        return new Error();
      case SUCCESS:
        return new Success(action);
      default:
        return null;
    }
  }
}
