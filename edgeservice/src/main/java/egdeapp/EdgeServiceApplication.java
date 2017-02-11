package egdeapp;

import egdeapp.fallback.TestFallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcProperties;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * Configuration and startup of Edge Service
 */
@SpringBootApplication
@EnableZuulProxy
public class EdgeServiceApplication{
    /**
     * Creating Hystrix fallback for testservice
     * @return TestFallbackProvider of type ZuulFallbackProvider
     */
    @Bean
    public ZuulFallbackProvider zuulFallbackProvider(){
        return new TestFallbackProvider();
    }

    /**
     * Main launch method
     * @param args arguments for application
     */
    public static void main(String[] args) {
        SpringApplication.run(EdgeServiceApplication.class, args);
    }
}