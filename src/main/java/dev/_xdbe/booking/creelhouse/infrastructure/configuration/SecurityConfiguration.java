package dev._xdbe.booking.creelhouse.infrastructure.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests(auth -> auth
                // Step 4a: add access control
                // ...
                // Step 4a: end
                .anyRequest().permitAll()
            )
            // Step 4b: Add login form
            // ...
            // Step 4b: End of login form configuration
            
            .csrf((csrf) -> csrf
                .ignoringRequestMatchers("/h2-console/**")
            )
            .headers(headers ->
                headers.frameOptions(frameOptions ->
                    frameOptions.disable()
                )
            )
            .build();
    }
    // Step 3: add InMemoryUserDetailsManager
    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails administrator = User.builder()
            .username("admin")
            .password("{bcrypt}$2y$10$IsfDaRT33oj2tYPM0Y2mpOVzgoYHjSNDvXZ.EONLNTllCXh1/FDri")
            .roles("ADMIN")
            .build();

        UserDetails guest = User.builder()
            .username("guest")
            .password("{bcrypt}$2y$10$dD/BOOSuEc6S3nE/4pdvs.Ev8wgwhTQrYihDYneN5g05u49c0eKQu")
            .roles("GUEST")
            .build();

        return new InMemoryUserDetailsManager(administrator, guest);
    }
    // Step 3: end
}
