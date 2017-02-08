package egdeapp.internationalization;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;
import java.util.Properties;

public class BundleMessageSource extends ReloadableResourceBundleMessageSource{

    public Properties getAllPropertiesForGivenLanguage(Locale lang){
        clearCacheIncludingAncestors();
        PropertiesHolder propertiesHolder = getMergedProperties(lang);

        return propertiesHolder.getProperties();
    }
}
