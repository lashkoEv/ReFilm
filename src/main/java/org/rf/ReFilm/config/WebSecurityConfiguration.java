package org.rf.ReFilm.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Arrays;

@EnableWebSecurity
@Slf4j
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;


    private PasswordEncoder passwordEncoder;
    private UserDetailsService userDetailsService;

    public WebSecurityConfiguration(final DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        if (passwordEncoder == null) {
            passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        }
        log.info(" --- PasswordEncoder {}", passwordEncoder.getClass().getName());
        return passwordEncoder;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled "
                        + "from users "
                        + "where username = ?")
                .authoritiesByUsernameQuery("select u.username, a.authority "
                        + "from authorities a LEFT JOIN users u on a.user_id = u.id "
                        + "where u.username = ?");
    }


    @Override
    protected void configure(HttpSecurity httpSecurity)
            throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/h2-console/**")
                .permitAll()

                .antMatchers("/quiz/create").hasAnyRole(new String[]{"ADMIN", "EXPERT"})

                .antMatchers("/**")
                .permitAll()

                .anyRequest()
                .authenticated()
                .and()

                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/success")
                .failureUrl("/login/error")
                .successHandler(new AuthenticationSuccessHandler() {

                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                        Authentication authentication) throws IOException, ServletException {

                        log.info("--- Logged user: " + authentication.getName());
                        response.sendRedirect("/success");
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {

                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                                        AuthenticationException exception) throws IOException, ServletException {
                        log.info("--- Login failed!" + exception.getMessage());
                        response.sendRedirect("/login/error");
                    }
                })
                .permitAll()


                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(new LogoutSuccessHandler() {

                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                                Authentication authentication)
                            throws IOException, ServletException {

                        log.info("--- Logged out " + authentication.getName());
                        response.sendRedirect("/logout/success");
                    }
                })

                .and()
                .rememberMe()
                .key("rememberMe")
                .rememberMeParameter("remember-me")
                .tokenValiditySeconds(360000)

                .and()
                .csrf()
                .disable();


        httpSecurity.headers()
                .frameOptions()
                .sameOrigin();
        httpSecurity.cors().configurationSource(corsConfigurationSource());


    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "HEAD", "OPTIONS", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers", "Authorization", "withCredentials"));
        configuration.setExposedHeaders(Arrays.asList("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(10L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}
