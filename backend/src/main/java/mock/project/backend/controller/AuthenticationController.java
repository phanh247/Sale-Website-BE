package mock.project.backend.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mock.project.backend.config.TokenUtil;
import mock.project.backend.request.JwtResponse;
import mock.project.backend.request.User;
import mock.project.backend.response.ResponseTransfer;

@RestController
@RequestMapping("/api/authenticate")
public class AuthenticationController {

	private Logger logger = Logger.getLogger(AuthenticationController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenUtil tokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@GetMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return ResponseEntity.ok("Logout successful!");
	}

	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createAthenticatioToken(@RequestBody User user) throws Exception {
		String token = null;
		if(authenticate(user.getUsername(), user.getPassword())) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
		token = tokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(token);		
		}
		String msg = "Incorrect username or password, please login again!";
		return 	ResponseEntity.ok(msg);
	}

	private boolean authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		return true;
		} catch (DisabledException e) {
			logger.error("Wrong username!");
		} catch (BadCredentialsException e) {
			logger.error("Wrong password!");
		}
		return false;
	}
}
