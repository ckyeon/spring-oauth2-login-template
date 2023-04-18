package com.ckyeon.springoauth2logintemplate.security.oauth2.userinfo;

import com.ckyeon.springoauth2logintemplate.user.domain.OAuth2Provider;
import java.util.Map;

public class CustomOAuth2UserInfoFactory {

  public static CustomOAuth2UserInfo create(OAuth2Provider provider,
    Map<String, Object> attributes) {
    return switch (provider) {
      case GITHUB -> new GithubOAuth2UserInfo(attributes);
      case GOOGLE -> new GoogleOAuth2UserInfo(attributes);
    };
  }
}
