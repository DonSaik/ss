package lt.viko.eif.donatas.dao;

import java.util.List;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lt.viko.eif.donatas.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public List<User> getUsers() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = currentSession.createQuery("from User order by username", User.class);
		List<User> users = theQuery.getResultList();
		return users;
	}

	@Override
	@Transactional
	public void saveUser(User user) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.save(user);
		
	}

	@Override
	public User getUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public User getUserByUsername(String username) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = currentSession.createQuery("from User where username = :username");
		theQuery.setParameter("username", username);
		List<User> users = theQuery.getResultList();
		User user = null;
		if(!users.isEmpty()) {
			user= users.get(0);
		}
		return user;
	}

	//0 OR 0=0; insert into user (username, password, enabled) values ("abc", "password", 1)
	// a' OR 'q'='q'--
	@Override
	@Transactional
	public List<User> getUsersByName(String username) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = currentSession.createQuery("from User where username like :username order by username DESC");
		theQuery.setParameter("username", '%'+ username+'%');
		List<User> users = theQuery.getResultList();

		return users;
	}

}
