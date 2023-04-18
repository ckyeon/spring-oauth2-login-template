package com.ckyeon.springoauth2logintemplate.security.oauth2.user;

import com.ckyeon.springoauth2logintemplate.user.domain.Role;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.util.Map;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public class CustomOidcUser extends CustomOAuth2User implements OidcUser {

  private final OidcUser oidcUser;

  public CustomOidcUser(Long id, Role role, OidcUser oidcUser) {
    super(id, role, oidcUser);
    Preconditions.checkArgument(oidcUser != null, "oidcUser must be provided.");

    this.oidcUser = oidcUser;
  }

  @Override
  public Map<String, Object> getClaims() {
    return super.getAttributes();
  }

  @Override
  public OidcUserInfo getUserInfo() {
    return oidcUser.getUserInfo();
  }

  @Override
  public OidcIdToken getIdToken() {
    return oidcUser.getIdToken();
  }

  public OidcUser getOidcUser() {
    return oidcUser;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    CustomOidcUser that = (CustomOidcUser) o;
    return Objects.equal(oidcUser, that.oidcUser);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(super.hashCode(), oidcUser);
  }
}
