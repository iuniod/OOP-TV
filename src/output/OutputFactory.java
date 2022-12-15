package output;

public class OutputFactory {
  public static Output getOutput(final String errorMessage, final String feature) {
    switch (errorMessage) {
      case "ERROR": {
        return new Error(feature);
      }
      case "SUCCESS": {
        return new Success(feature);
      }
      default: {
        return null;
      }
    }
  }
}
