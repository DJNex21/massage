package ch.zli.m223.punchclock.config;

import ch.zli.m223.punchclock.filter.JWTAuthenticationFilter;
import ch.zli.m223.punchclock.filter.JWTAuthorizationFilter;
import ch.zli.m223.punchclock.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static ch.zli.m223.punchclock.config.SecurityConstants.*;


/**
 * The type Web security configuration.
 */
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private UserService userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Instantiates a new Web security configuration.
     *
     * @param userDetailsService    the user details service
     * @param bCryptPasswordEncoder the b crypt password encoder
     */
    public WebSecurityConfiguration(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                .antMatchers(HttpMethod.GET, "*.css").permitAll()
                .antMatchers(HttpMethod.GET, "*.html").permitAll()
                .antMatchers(HttpMethod.GET, "*.js").permitAll()
                .antMatchers(HttpMethod.GET, "/**/**.*").permitAll()
                .antMatchers(HttpMethod.GET, USERS_URL).permitAll()
                .antMatchers(HttpMethod.POST, USERS_URL).permitAll()
                .antMatchers(HttpMethod.DELETE, USERS_URL).permitAll()
                .antMatchers(HttpMethod.PUT, USERS_URL).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html").permitAll()
                .loginProcessingUrl(LOGIN_URL)
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    /**
     * Cors configuration source cors configuration source.
     *
     * @return the cors configuration source
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}