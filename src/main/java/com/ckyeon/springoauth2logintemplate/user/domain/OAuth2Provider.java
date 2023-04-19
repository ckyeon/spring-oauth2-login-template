package com.ckyeon.springoauth2logintemplate.user.domain;

import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;

public enum OAuth2Provider {
  GOOGLE,
  GITHUB,
  KAKAO;

  public static OAuth2Provider getProvider(OAuth2UserRequest oAuth2UserRequest) {
    String registrationId = oAuth2UserRequest.getClientRegistration().getRegistrationId();
    return OAuth2Provider.valueOf(registrationId.toUpperCase());
  }
}
