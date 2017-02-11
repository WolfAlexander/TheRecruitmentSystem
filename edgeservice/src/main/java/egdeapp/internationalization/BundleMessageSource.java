package egdeapp.internationalization;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;
import java.util.Properties;

/**
 * Implementation that accesses message resources
 */
public class BundleMessageSource extends ReloadableResourceBundleMessageSource{

    /**
     * Returns message properties for a given language
     * @param lang - given language
     * @return properties for a given language
     */
    public Properties getAllPropertiesForGivenLanguage(Locale lang){
        clearCacheIncludingAncestors();
        PropertiesHolder propertiesHolder = getMergedProperties(lang);

        return propertiesHolder.getProperties();
    }
}
