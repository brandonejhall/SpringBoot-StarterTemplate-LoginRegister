package com.swiftrecruit.usermanagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetails;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.swiftrecruit.usermanagement.config.JwtRequestFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

        @Autowired
        private UserDetailsService userDetailsService;

        @Autowired
        private JwtRequestFilter jwtRequestFilter;

        @Bean
        public static PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                // .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer
                return http
                                .cors(Customizer.withDefaults())
                                .csrf(AbstractHttpConfigurer::disable)

                                .httpBasic(withDefaults())
                                .formLogin(withDefaults())
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers(HttpMethod.OPTIONS, "/").permitAll()
                                                .requestMatchers("/api/users/index").permitAll()
                                                .requestMatchers("/api/users/register").permitAll()
                                                .requestMatchers("/api/users/login").permitAll()
                                                .anyRequest().authenticated())
                                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                                .build();

        }

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
                auth
                                .userDetailsService(userDetailsService)
                                .passwordEncoder(passwordEncoder());
        }
        /*
         * @Bean
         * CorsConfigurationSource corsConfigurationSource() {
         * CorsConfiguration configuration = new CorsConfiguration();
         * configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
         * configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
         * configuration.setAllowedHeaders(Arrays.asList("Authorization",
         * "Cache-Control", "Content-Type"));
         * configuration.addAllowedMethod("OPTIONS");
         * 
         * 
         * UrlBasedCorsConfigurationSource source = new
         * UrlBasedCorsConfigurationSource();
         * source.registerCorsConfiguration("/", configuration);
         * return source;
         * }
         */

        @Bean
        public UserDetailsService userDetailsService() {

                UserDetails user = User.builder()
                                .username("user")
                                .password("{noop}password") // {noop} is used to indicate that the password is stored in
                                // plain text
                                .roles("USER")
                                .build();

                UserDetails admin = User.builder()
                                .username("admin")
                                .password("{noop}admin") // {noop} is used to indicate that the password is stored in
                                // plain text
                                .roles("ADMIN")
                                .build();

                return new InMemoryUserDetailsManager(user, admin);
        }

        @Bean
        public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
                return http.getSharedObject(AuthenticationManagerBuilder.class).build();
        }

}