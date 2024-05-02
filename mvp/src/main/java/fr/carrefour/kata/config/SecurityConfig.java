package fr.carrefour.kata.config;

/**
 * @author Amine Achouri
 */

import fr.carrefour.kata.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailServiceImpl userDetailService;

    protected void configure(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable).cors(AbstractHttpConfigurer::disable).authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                .requestMatchers("/login").permitAll()
                                .anyRequest().authenticated())
                // Filter for the api/login requests
                .addFilterBefore(new LoginFilter("/login", new AuthenticationManager() {
                            @Override
                            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                                return null;
                            }
                        }),
                        UsernamePasswordAuthenticationFilter.class)
                //				// Filter for other requests to check JWT in header
                .addFilterBefore(new AuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);
    }



    //	@Bean
    //	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //	http.authorizeHttpRequests((auth -> auth
    ////			.requestMatchers("/api/users/**").permitAll()
    //			.anyRequest()
    //			.authenticated()))
    //			.httpBasic(Customizer.withDefaults());
    //
    //
    //
    //		return http.build();
    //	}

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
