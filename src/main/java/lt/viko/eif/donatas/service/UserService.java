package lt.viko.eif.donatas.service;

import java.util.List;

import lt.viko.eif.donatas.model.User;

public interface UserService {
	List<User> getUsers();
	User getUserByUsername(String username);
	User resigterNewUser(User user);
	List<User> filterUsersByUsername(String username);
}
