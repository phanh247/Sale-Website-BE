package mock.project.backend.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import mock.project.backend.entities.CustomUserDetails;
import mock.project.backend.entities.Users;
import mock.project.backend.repository.RoleRepository;
import mock.project.backend.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private Logger logger = Logger.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Users user = userRepo.findByUserName(userName);
		if (user == null) {
			logger.error("User not found! " + userName);
			throw new UsernameNotFoundException("User " + userName + " was not found in the database");
		}
		logger.info("Found: " + user);
		List<String> roleNames = roleRepo.findByRoleName(user.getUserId());
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		if (roleNames != null) {
			for (String role : roleNames) {
				GrantedAuthority authority = new SimpleGrantedAuthority(role);
				grantList.add(authority);
			}
		}
		
		UserDetails userDetails = (UserDetails) new CustomUserDetails(user.getUserName(),
				user.getEncryptedPassword(), grantList);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, grantList);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return userDetails;
	}
}