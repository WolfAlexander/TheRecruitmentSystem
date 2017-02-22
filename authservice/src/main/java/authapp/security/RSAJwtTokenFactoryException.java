package authapp.security;

/**
 * This exception encapsulates all exception cached in RSAJwtTokenFactory
 */
public class RSAJwtTokenFactoryException extends RuntimeException{
    private static final long serialVersionUID = 5017272796400395351L;

    public RSAJwtTokenFactoryException(String message) {
        super(message);
    }
}
