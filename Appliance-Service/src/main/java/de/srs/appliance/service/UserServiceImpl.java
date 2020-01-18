package de.srs.appliance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.srs.appliance.model.Student;
import de.srs.appliance.model.User;
import de.srs.appliance.repository.StudentRepository;
import de.srs.appliance.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Override
	public User getUserByStudentId(Integer studentId) {

		User user = null;
		Student student = studentRepository.findById(studentId).get();
		if(student != null){
			user = userRepository.findById(student.getUser().getId()).get();
		}
		return user;
	}

}
