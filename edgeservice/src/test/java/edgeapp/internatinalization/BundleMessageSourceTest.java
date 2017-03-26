package edgeapp.internatinalization;

import egdeapp.EdgeServiceApplication;
import egdeapp.internationalization.BundleMessageSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * Testing retrial of message properties depending on the language
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("testing")
@SpringBootTest(classes = EdgeServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BundleMessageSourceTest {
    @Autowired
    private BundleMessageSource messageSource;

    @Test
    public void getAllPropertiesForEngglish(){
        Properties properties =  messageSource.getAllPropertiesForGivenLanguage(new Locale("en"));

        assertEquals("The recruitment system", properties.getProperty("homepage.title"));
    }

    @Test
    public void getAllPropertiesForSwedish(){
        Properties properties =  messageSource.getAllPropertiesForGivenLanguage(new Locale("sv"));

        assertEquals("Recruterings system", properties.getProperty("homepage.title"));
    }

}
