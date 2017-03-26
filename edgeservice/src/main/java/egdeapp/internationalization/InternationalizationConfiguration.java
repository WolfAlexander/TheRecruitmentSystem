package egdeapp.internationalization;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.*;

import javax.websocket.Session;
import java.util.Locale;

/**
 * Configuration for internalization and configuration
 */
@Configuration
public class InternationalizationConfiguration extends WebMvcConfigurerAdapter {
    /**
     * Setting up implementation of LocaleResolver that will pick up Locale
     * @return local resolver
     */
    @Bean
    public LocaleResolver localeResolver(){
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        Locale.setDefault(new Locale("en"));
        resolver.setDefaultLocale(new Locale("en"));

        return resolver;
    }

    /**
     * Setting up a interception that will reade change in locale on every request
     * @return a custom interceptor
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");

        return localeChangeInterceptor;
    }

    /**
     * Creating implementation that accesses resource using basename
     * @return
     */
    @Bean
    public BundleMessageSource bundleMessageSource(){
        BundleMessageSource bundleMessageSource = new BundleMessageSource();
        bundleMessageSource.setBasename("classpath:/messages");

        return bundleMessageSource;
    }

    /**
     * Registering a new interceptor
     * @param registry registry of interceptors
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
