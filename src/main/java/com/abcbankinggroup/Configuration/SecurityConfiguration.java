package com.abcbankinggroup.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Order(99)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(SecurityConfiguration.class);

    /** Ant style pattern, that means "all incoming requests, regardless of specific URL */
    private static final String ALL_INCOMING_REQUESTS = "/**";

    /** Ant style pattern, that means "all admin requests */
    private static final String MANAGEMENT_REQUESTS = "/management/**";

    /**
     * Name of the "role" that is necessary for api requests.
     */
    private static final String ROLE_API_USER = "API_USER";

    /**
     * Name of the "role" that is necessary for administration requests.
     */
    private static final String ROLE_MANAGER = "MANAGER";

    /** the username that all users of the API will have to use */
    @Value("apiuser")
    private String apiUsername;

    /** the password that all users of the API will have to use */
    @Value("pass")
    private String apiPassword;

    @Value("user")
    private String managementUsername;

    @Value("pass")
    private String managementPassword;

    /** Configure security settings for this micro service with Http basic authentication */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        LOG.info("setting up http basic authentication");

        //  no CSRF protection for a REST Api:
        http.csrf().disable().authorizeRequests()
                .antMatchers(
                        "/webjars/**",
                        "/"
                ).permitAll()
                .antMatchers(MANAGEMENT_REQUESTS).hasRole(ROLE_MANAGER)
                .antMatchers(ALL_INCOMING_REQUESTS).hasRole(ROLE_API_USER)
                .anyRequest().authenticated()
                .and().httpBasic();

    }

    /** Use a simple in memory authentication store for the time being. */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        LOG.info(String.format("setting up in memory authentication database"));

        auth.inMemoryAuthentication()
                .withUser(apiUsername)
                .password(apiPassword)
                .roles(ROLE_API_USER)
                .and()
                .withUser(managementUsername)
                .password(managementPassword)
                .roles(ROLE_MANAGER);

    }

}
