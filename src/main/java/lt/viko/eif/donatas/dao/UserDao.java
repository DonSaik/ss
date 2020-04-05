package lt.viko.eif.donatas.dao;

import java.util.List;

import lt.viko.eif.donatas.model.User;

public interface UserDao {
	List<User> getUsers();
	void saveUser(User user);
	User getUser(int id);
	void deleteUser(int id);
	User getUserByUsername(String username);
	List<User> getUsersByName(String username);
}
