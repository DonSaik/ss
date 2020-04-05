package lt.viko.eif.donatas.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lt.viko.eif.donatas.dao.UserDao;
import lt.viko.eif.donatas.model.User;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	private UserDao userDAO;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDAO.getUserByUsername(username);
		if(user ==null) {
			throw new UsernameNotFoundException("User 404");
			
		}
		return new UserDetailsImpl(user);
	}

}
