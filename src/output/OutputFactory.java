package output;

import input.action.Action;

public class OutputFactory {
  public static Output getOutput(final String errorMessage, final Action action) {
    switch (errorMessage) {
      case "ERROR": {
        return new Error();
      }
      case "SUCCESS": {
        return new Success(action);
      }
      default: {
        return null;
      }
    }
  }
}
