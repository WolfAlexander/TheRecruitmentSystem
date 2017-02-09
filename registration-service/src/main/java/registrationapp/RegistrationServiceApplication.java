package registrationapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This class launches the registration service by starting the application associated with
 * it.
 *
 * @author Albin Friedner
 */

@SpringBootApplication
public class RegistrationServiceApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(RegistrationServiceApplication.class, args);
	}
}
