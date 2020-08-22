package srs.bulletinboard.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import srs.bulletinboard.model.User;
import srs.bulletinboard.repository.UserRepository;
import srs.bulletinboard.util.Constants;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	
	@Override
	public List<User> getAdmins() {

		List<User> admins = new ArrayList<User>();
		
		admins = userRepository.findByRoleId(Constants.ADMIN);
		return admins;
	}

}
