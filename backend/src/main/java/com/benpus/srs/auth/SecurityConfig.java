package com.benpus.srs.auth;

import com.benpus.srs.models.Permission;
import com.benpus.srs.models.UserType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    public SecurityConfig(JwtAuthenticationFilter filter, AuthenticationProvider authenticationProvider, LogoutService logoutHandler){
        jwtAuthFilter = filter;
        this.authenticationProvider = authenticationProvider;
        this.logoutHandler = logoutHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> {
                    request.requestMatchers("api/v1/auth/**").permitAll();

                    request.requestMatchers("api/v1/users/**").hasRole(UserType.ADMIN.name());

                    request.requestMatchers(HttpMethod.GET, "api/v1/instructors/**").permitAll();
                    request.requestMatchers(HttpMethod.GET, "api/v1/shootingRanges/**").permitAll();
                    request.requestMatchers(HttpMethod.GET, "api/v1/firearms/**").permitAll();
                    request.requestMatchers(HttpMethod.GET, "api/v1/companies/**").permitAll();

                    request.requestMatchers(HttpMethod.POST, "api/v1/instructors/**").hasAnyAuthority(Permission.ADMIN_CREATE.getPermission(), Permission.USER_CREATE.getPermission());
                    request.requestMatchers(HttpMethod.PATCH, "api/v1/instructors/**").hasAnyAuthority(Permission.ADMIN_UPDATE.getPermission(), Permission.USER_UPDATE.getPermission());
                    request.requestMatchers(HttpMethod.DELETE, "api/v1/instructors/**").hasAnyAuthority(Permission.ADMIN_DELETE.getPermission(), Permission.USER_DELETE.getPermission());

                    request.requestMatchers(HttpMethod.POST, "api/v1/shootingRanges/**").hasAnyAuthority(Permission.ADMIN_CREATE.getPermission(), Permission.USER_CREATE.getPermission());
                    request.requestMatchers(HttpMethod.PATCH, "api/v1/shootingRanges/**").hasAnyAuthority(Permission.ADMIN_UPDATE.getPermission(), Permission.USER_UPDATE.getPermission());
                    request.requestMatchers(HttpMethod.DELETE, "api/v1/shootingRanges/**").hasAnyAuthority(Permission.ADMIN_DELETE.getPermission(), Permission.USER_DELETE.getPermission());

                    request.requestMatchers(HttpMethod.POST, "api/v1/firearms/**").hasAnyAuthority(Permission.ADMIN_CREATE.getPermission(), Permission.USER_CREATE.getPermission());
                    request.requestMatchers(HttpMethod.PATCH, "api/v1/firearms/**").hasAnyAuthority(Permission.ADMIN_UPDATE.getPermission(), Permission.USER_UPDATE.getPermission());
                    request.requestMatchers(HttpMethod.DELETE, "api/v1/firearms/**").hasAnyAuthority(Permission.ADMIN_DELETE.getPermission(), Permission.USER_DELETE.getPermission());

                    request.requestMatchers(HttpMethod.POST, "api/v1/companies/**").hasAnyAuthority(Permission.ADMIN_CREATE.getPermission(), Permission.USER_CREATE.getPermission());
                    request.requestMatchers(HttpMethod.PATCH, "api/v1/companies/**").hasAnyAuthority(Permission.ADMIN_UPDATE.getPermission(), Permission.USER_UPDATE.getPermission());
                    request.requestMatchers(HttpMethod.DELETE, "api/v1/companies/**").hasAnyAuthority(Permission.ADMIN_DELETE.getPermission(), Permission.USER_DELETE.getPermission());

/*      example:    request.requestMatchers("api/v1/instructors/**").hasAnyRole(UserType.ADMIN.name(), UserType.USER.name());
                    request.requestMatchers(HttpMethod.GET, "api/v1/instructors/**").hasAnyAuthority(Permission.ADMIN_READ.getPermission(), Permission.USER_READ.getPermission());
                    request.requestMatchers(HttpMethod.POST, "api/v1/instructors/**").hasAnyAuthority(Permission.ADMIN_CREATE.getPermission(), Permission.USER_CREATE.getPermission());
                    request.requestMatchers(HttpMethod.PATCH, "api/v1/instructors/**").hasAnyAuthority(Permission.ADMIN_UPDATE.getPermission(), Permission.USER_UPDATE.getPermission());
                    request.requestMatchers(HttpMethod.DELETE, "api/v1/instructors/**").hasAnyAuthority(Permission.ADMIN_DELETE.getPermission(), Permission.USER_DELETE.getPermission());*/

                    request.anyRequest().authenticated();
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> {
                    logout.logoutUrl("/api/v1/auth/logout");
                    logout.addLogoutHandler(logoutHandler);
                    logout.logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());
                });
        return http.build();
    }

}
