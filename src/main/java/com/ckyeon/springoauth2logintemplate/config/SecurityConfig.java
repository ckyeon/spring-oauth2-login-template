package com.ckyeon.springoauth2logintemplate.config;

import com.ckyeon.springoauth2logintemplate.security.oauth2.service.CustomOAuth2UserService;
import com.ckyeon.springoauth2logintemplate.security.oauth2.service.CustomOidcUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.JdbcOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final JdbcTemplate jdbcTemplate;
  private final ClientRegistrationRepository clientRegistrationRepository;
  private final CustomOAuth2UserService customOAuth2UserService;
  private final CustomOidcUserService customOidcUserService;

  public SecurityConfig(JdbcTemplate jdbcTemplate,
    ClientRegistrationRepository clientRegistrationRepository,
    CustomOAuth2UserService customOAuth2UserService, CustomOidcUserService customOidcUserService) {
    this.jdbcTemplate = jdbcTemplate;
    this.clientRegistrationRepository = clientRegistrationRepository;
    this.customOAuth2UserService = customOAuth2UserService;
    this.customOidcUserService = customOidcUserService;
  }

  @Bean
  public OAuth2AuthorizedClientService oAuth2AuthorizedClientService() {
    return new JdbcOAuth2AuthorizedClientService(jdbcTemplate, clientRegistrationRepository);
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.oauth2Login()
      .authorizedClientService(oAuth2AuthorizedClientService())
      .userInfoEndpoint()
      .userService(customOAuth2UserService)
      .oidcUserService(customOidcUserService);

    http.authorizeHttpRequests()
      .anyRequest().authenticated();

    return http.build();
  }
}
