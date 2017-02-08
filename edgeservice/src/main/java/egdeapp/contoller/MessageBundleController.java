package egdeapp.contoller;

import egdeapp.internationalization.BundleMessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Properties;

@RestController
public class MessageBundleController {
    private final BundleMessageSource messageSource;

    @Autowired
    public MessageBundleController(BundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping("/messageBundle")
    public Properties getPropetiesByLanguage(@RequestParam String lang){
        return messageSource.getAllPropertiesForGivenLanguage(new Locale(lang));
    }
}
