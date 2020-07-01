package srs.AuthServer.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import srs.AuthServer.model.User;

@Repository
public interface UserDetailsRepository extends CrudRepository<User, Long> {
	Optional<User> findByUsername(String username);
}
