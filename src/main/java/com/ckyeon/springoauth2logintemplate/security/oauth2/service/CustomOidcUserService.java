package com.ckyeon.springoauth2logintemplate.security.oauth2.service;

import com.ckyeon.springoauth2logintemplate.security.oauth2.user.CustomOidcUser;
import com.ckyeon.springoauth2logintemplate.user.domain.OAuth2Provider;
import com.ckyeon.springoauth2logintemplate.user.domain.User;
import com.ckyeon.springoauth2logintemplate.user.domain.repository.UserRepository;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

@Component
public class CustomOidcUserService extends OidcUserService implements CustomOAuth2Service {

  private final UserRepository userRepository;

  public CustomOidcUserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
    OidcUser oidcUser = super.loadUser(userRequest);
    OAuth2Provider oAuth2Provider = OAuth2Provider.getProvider(userRequest);
    User user = processUser(userRepository, oAuth2Provider, oidcUser.getAttributes());
    return new CustomOidcUser(user.getId(), user.getRole(), oidcUser);
  }
}
