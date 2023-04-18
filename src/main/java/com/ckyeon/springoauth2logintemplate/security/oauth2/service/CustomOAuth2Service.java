package com.ckyeon.springoauth2logintemplate.security.oauth2.service;

import com.ckyeon.springoauth2logintemplate.security.oauth2.userinfo.CustomOAuth2UserInfo;
import com.ckyeon.springoauth2logintemplate.security.oauth2.userinfo.CustomOAuth2UserInfoFactory;
import com.ckyeon.springoauth2logintemplate.user.domain.OAuth2Provider;
import com.ckyeon.springoauth2logintemplate.user.domain.User;
import com.ckyeon.springoauth2logintemplate.user.domain.repository.UserRepository;
import java.util.Map;

public interface CustomOAuth2Service {

  default User processUser(UserRepository userRepository, OAuth2Provider provider,
    Map<String, Object> attributes) {
    CustomOAuth2UserInfo userInfo = CustomOAuth2UserInfoFactory.create(provider, attributes);
    String providerId = userInfo.getProviderId();
    return userRepository.findByProviderId(providerId)
      .orElseGet(() -> {
        User user = new User(provider, providerId);
        return userRepository.save(user);
      });
  }
}
