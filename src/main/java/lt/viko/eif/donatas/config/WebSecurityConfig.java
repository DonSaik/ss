package lt.viko.eif.donatas.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import lt.viko.eif.donatas.model.Authority;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/*
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		return provider;
	}
	*/
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean 
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public GrantedAuthoritiesMapper authoritiesMapper() {
		SimpleAuthorityMapper mapper = new SimpleAuthorityMapper();
		mapper.setPrefix("ROLE_"); // this line is not required 
		mapper.setConvertToUpperCase(true); // convert your roles to uppercase		
		return mapper;
	}
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	    provider.setUserDetailsService(userDetailsService);
	    provider.setPasswordEncoder(passwordEncoder());
	    provider.setAuthoritiesMapper(authoritiesMapper());

	    return provider;
	}
	/*
	@Autowired
	private DataSource dataSource;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder());
	}*/
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http//.requiresChannel().anyRequest().requiresSecure().and()
		.authorizeRequests()
			.antMatchers("/admin-home").hasRole("ADMIN")
			.antMatchers("/users").hasRole("ADMIN")
			.antMatchers("/home").hasRole("USER")
			.antMatchers("/").permitAll()
			//https://kb.globalscape.com/Knowledgebase/10691/What-is-the-difference-between-basic-auth-and-formbased-auth
			.and()//.httpBasic()
			.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/authenticateTheUser")
				.permitAll()
			.and().logout().permitAll()
				.logoutSuccessUrl("/")
			.and().exceptionHandling()
				.accessDeniedPage("/access-denied")
			.and().headers().frameOptions(frameOptions -> frameOptions.sameOrigin())
				.and().headers().xssProtection();
	}
	
	
	@Bean List<Authority> newUserAuthorities (){
		List<Authority> newUserAuthorities = new ArrayList<Authority>();
		Authority newAuthority = new Authority();
		newAuthority.setName("USER");
		newUserAuthorities.add(newAuthority);
		return newUserAuthorities;
	}
	
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}
	
}
