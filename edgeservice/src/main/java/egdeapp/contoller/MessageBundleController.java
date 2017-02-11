package egdeapp.contoller;

import egdeapp.internationalization.BundleMessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Properties;

/**
 * Controller for provides content text depending on a requested language
 */
@RestController
public class MessageBundleController {
    private final BundleMessageSource messageSource;

    /**
     * Injecting source of where translated content can be found
     * @param messageSource - representation that accesses messages in properties
     */
    @Autowired
    public MessageBundleController(BundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Returns content for a given language
     * @param lang - requested language
     * @return content in properties form
     */
    @GetMapping("/messageBundle")
    public Properties getPropetiesByLanguage(@RequestParam String lang){
        return messageSource.getAllPropertiesForGivenLanguage(new Locale(lang));
    }
}
