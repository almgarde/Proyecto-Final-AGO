package es.iessoterohernandez.ProyectoFinalAGO.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Dao.AdminDaoI;
import es.iessoterohernandez.ProyectoFinalAGO.Persistence.Entity.Admin;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AdminDaoI adminDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Admin admin = adminDao.findByUsernameAdmin(username);

		UserBuilder builder = null;

		if (admin != null) {
			builder = User.withUsername(username);
			builder.disabled(false);
			builder.password(admin.getPwdAdmin());
			builder.authorities(new SimpleGrantedAuthority("ROLE_USER"));
		} else {
			throw new UsernameNotFoundException("Admin no encontrado");
		}

		return builder.build();
	}

}
