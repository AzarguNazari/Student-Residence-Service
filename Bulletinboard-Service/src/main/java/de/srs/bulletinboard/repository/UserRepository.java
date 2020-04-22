package de.srs.bulletinboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import de.srs.bulletinboard.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query(value = "SELECT u.* FROM user_details u "
			+ " JOIN"
			+ " role r ON r.id = u.role_id "
			+ " AND r.name = ?1", nativeQuery = true)
	public List<User> findByRoleId(String role);
}
