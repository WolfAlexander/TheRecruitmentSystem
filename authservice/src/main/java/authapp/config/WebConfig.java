package authapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Security configuration that will handles all incoming traffic to the service
 *
 * @author WolfAlexander nikal@kth.se
 */
@Configuration
@SuppressWarnings("SpringJavaAutowiringInspection")
public class WebConfig extends WebSecurityConfigurerAdapter{
    private final UserDetailsService userDetailsService;

    @Autowired
    public WebConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Customizing authentication manager with custom user details service
     * @param authenticationManagerBuilder - authentication builder
     * @throws Exception
     */
    @Autowired
    public void configureAuth(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder
                .userDetailsService(userDetailsService);
    }

    /**
     * Configuring how endpoint should be handled by spring security
     * @param http security object
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                    .antMatchers("/auth/**").permitAll()
                    .anyRequest().authenticated();
    }
}
