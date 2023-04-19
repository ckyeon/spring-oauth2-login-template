package com.ckyeon.springoauth2logintemplate.security.oauth2.userinfo;

import java.util.Map;

public class KakaoOAuth2UserInfo implements CustomOAuth2UserInfo {

  private final Map<String, Object> attributes;

  public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
    this.attributes = attributes;
  }

  @Override
  public String getProviderId() {
    return attributes.get("id").toString();
  }
}
