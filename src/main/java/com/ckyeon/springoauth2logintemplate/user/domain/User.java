package com.ckyeon.springoauth2logintemplate.user.domain;

import com.google.common.base.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(length = 10, nullable = false)
  private OAuth2Provider provider;

  @Column(length = 25, nullable = false, unique = true)
  private String providerId;

  @Enumerated(EnumType.STRING)
  @Column(length = 15, nullable = false)
  private Role role;

  protected User() {
  }

  public User(OAuth2Provider provider, String providerId) {
    this(null, provider, providerId, Role.ROLE_USER);
  }

  private User(Long id, OAuth2Provider provider, String providerId, Role role) {
    this.id = id;
    this.provider = provider;
    this.providerId = providerId;
    this.role = role;
  }

  public Long getId() {
    return id;
  }

  public OAuth2Provider getProvider() {
    return provider;
  }

  public String getProviderId() {
    return providerId;
  }

  public Role getRole() {
    return role;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equal(id, user.id) && provider == user.provider
      && Objects.equal(providerId, user.providerId) && role == user.role;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id, provider, providerId, role);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
      .append("id", id)
      .append("provider", provider)
      .append("providerId", providerId)
      .append("role", role)
      .toString();
  }
}
