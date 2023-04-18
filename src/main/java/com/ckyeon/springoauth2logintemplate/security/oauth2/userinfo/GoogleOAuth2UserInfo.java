package com.ckyeon.springoauth2logintemplate.security.oauth2.userinfo;

import java.util.Map;

public class GoogleOAuth2UserInfo implements CustomOAuth2UserInfo {

  private final Map<String, Object> attributes;

  public GoogleOAuth2UserInfo(Map<String, Object> attributes) {
    this.attributes = attributes;
  }

  @Override
  public String getProviderId() {
    return attributes.get("sub").toString();
  }
}
