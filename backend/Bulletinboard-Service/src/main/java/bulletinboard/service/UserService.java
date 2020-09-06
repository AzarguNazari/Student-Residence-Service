package bulletinboard.service;

import java.util.List;

import bulletinboard.model.User;

public interface UserService {

	public List<User> getAdmins();
}
