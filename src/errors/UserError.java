package errors;

public class UserError extends Error {
  public UserError(String message) {
    super(message);
  }
}