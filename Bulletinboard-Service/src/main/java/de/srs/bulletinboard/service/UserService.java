package de.srs.bulletinboard.service;

import java.util.List;

import de.srs.bulletinboard.model.User;

public interface UserService {

	public List<User> getAdmins();
}
