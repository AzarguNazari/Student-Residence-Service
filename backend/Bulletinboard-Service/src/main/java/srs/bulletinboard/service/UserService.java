package srs.bulletinboard.service;

import java.util.List;

import srs.bulletinboard.model.User;

public interface UserService {

	public List<User> getAdmins();
}
