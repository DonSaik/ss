package lt.viko.eif.donatas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lt.viko.eif.donatas.dao.UserDao;
import lt.viko.eif.donatas.model.Authority;
import lt.viko.eif.donatas.model.User;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDAO;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private List<Authority> newUserAuthorities;
	@Override
	public List<User> getUsers() {
		return userDAO.getUsers();
	}

	@Override
	public User getUserByUsername(String username) {
		return userDAO.getUserByUsername(username);
	}

	@Override
	public User resigterNewUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setConfirmPassword(user.getPassword());

		user.setAuthorities(newUserAuthorities);
		userDAO.saveUser(user);
		return user;
		
	}

	@Override
	public List<User> filterUsersByUsername(String username) {
		return userDAO.getUsersByName(username);
	}


}
