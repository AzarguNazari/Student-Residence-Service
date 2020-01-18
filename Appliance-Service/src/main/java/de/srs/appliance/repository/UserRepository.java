package de.srs.appliance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.srs.appliance.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{}
