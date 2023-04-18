package com.ckyeon.springoauth2logintemplate.security.oauth2.service;

import com.ckyeon.springoauth2logintemplate.security.oauth2.user.CustomOAuth2User;
import com.ckyeon.springoauth2logintemplate.user.domain.OAuth2Provider;
import com.ckyeon.springoauth2logintemplate.user.domain.User;
import com.ckyeon.springoauth2logintemplate.user.domain.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
public class CustomOAuth2UserService extends DefaultOAuth2UserService implements
  CustomOAuth2Service {

  private final UserRepository userRepository;

  public CustomOAuth2UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    OAuth2User oAuth2User = super.loadUser(userRequest);
    OAuth2Provider oAuth2Provider = OAuth2Provider.getProvider(userRequest);
    User user = processUser(userRepository, oAuth2Provider, oAuth2User.getAttributes());
    return new CustomOAuth2User(user.getId(), user.getRole(), oAuth2User);
  }
}
