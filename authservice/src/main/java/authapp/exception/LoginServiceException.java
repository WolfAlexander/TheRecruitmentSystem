package authapp.exception;

/**
 * Exception that encapsulates any exeption the a thrown during authorization process
 */
public class LoginServiceException extends RuntimeException {
    /**
     * Exception constructor with an error message
     * @param message - explains cause of the exception
     */
    public LoginServiceException(String message) {
        super(message);
    }
}
