package authapp.logging;

import authapp.controller.AuthController;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * This class logs events in AuthController using AOP with AspectJ
 */
@Aspect
@Component
public class AuthControllerLogging {
    private final static Logger logger = LoggerFactory.getLogger(AuthController.class);

    /**
     * Logging request to /auth endpoint
     */
    @Before("execution(* authapp.controller.AuthController.generateJwtToken(..))")
    public void logEntryPointToGenerateJwtToken(){
        logger.info("POST request made to /auth");
    }
}
