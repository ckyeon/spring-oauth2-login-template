package com.ckyeon.springoauth2logintemplate.user.domain.repository;

import com.ckyeon.springoauth2logintemplate.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByProviderId(String providerId);
}
