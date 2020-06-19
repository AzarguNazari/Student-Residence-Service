package srs.appliance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import srs.appliance.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{}
