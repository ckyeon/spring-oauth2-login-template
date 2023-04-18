package com.ckyeon.springoauth2logintemplate.security.oauth2.user;

import com.ckyeon.springoauth2logintemplate.common.Id;
import com.ckyeon.springoauth2logintemplate.user.domain.Role;
import com.ckyeon.springoauth2logintemplate.user.domain.User;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomOAuth2User implements OAuth2User, Serializable {

  private final Id<User, Long> id;

  private final Role role;

  private final OAuth2User oAuth2User;

  public CustomOAuth2User(Long id, Role role, OAuth2User oAuth2User) {
    Preconditions.checkArgument(id != null, "id must be provided.");
    Preconditions.checkArgument(role != null, "role must be provided.");
    Preconditions.checkArgument(oAuth2User != null, "oAuth2User must be provided.");

    this.id = Id.of(User.class, id);
    this.role = role;
    this.oAuth2User = oAuth2User;
  }

  @Override
  public Map<String, Object> getAttributes() {
    return oAuth2User.getAttributes();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return oAuth2User.getAuthorities();
  }

  @Override
  public String getName() {
    return oAuth2User.getName();
  }

  public Id<User, Long> getId() {
    return id;
  }

  public Role getRole() {
    return role;
  }

  public OAuth2User getOAuth2User() {
    return oAuth2User;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CustomOAuth2User that = (CustomOAuth2User) o;
    return Objects.equal(id, that.id) && role == that.role
      && Objects.equal(oAuth2User, that.oAuth2User);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id, role, oAuth2User);
  }
}
