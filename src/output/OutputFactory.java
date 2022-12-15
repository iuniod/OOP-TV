package output;

public class OutputFactory {
  public static Output getOutput(final String errorMessage) {
    return switch (errorMessage) {
      case "error" -> new Error();
      case "success" -> new Success();
      default -> null;
    };
  }
}
