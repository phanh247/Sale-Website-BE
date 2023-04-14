package mock.project.backend.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import mock.project.backend.entities.Products;
import mock.project.backend.entities.UserRole;
import mock.project.backend.entities.Users;
import mock.project.backend.repository.UserRepository;
import mock.project.backend.repository.UserRoleRepository;
import mock.project.backend.request.ProductDTO;
import mock.project.backend.request.UserDTO;
import mock.project.backend.request.UserDTOReponse;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserRoleRepository userRoleRepo;
	
	@Autowired
	private ModelMapper modelMap;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
		
	public Users registerUserAccount(UserDTO userDTO) throws Exception {
		Users user = new Users();
		user.setUserId(userDTO.getUserId());
		user.setUserName(userDTO.getUserName());
		user.setEncryptedPassword((bCryptPasswordEncoder.encode(userDTO.getPassword())));;
		user.setFullName(userDTO.getFullName());
		user.setEmail(userDTO.getEmail());
		user.setAddress(userDTO.getAddress());
		user.setPhone(userDTO.getPhone());
		user.setDateofBirth(userDTO.getDateofBirth());
		user.setImage(userDTO.getImage());
		user.setEnabled(true);
		userRepo.save(user);
		if(userDTO.getRole() != null) {
		userRoleRepo.save(new UserRole(user,userDTO.getRole()));
		}
		return user;
	}
	
	public Users updateUserAccount(UserDTO userDTO) throws Exception {
		Users user = new Users();
		user.setUserId(userDTO.getUserId());
		user.setUserName(userDTO.getUserName());
		user.setEncryptedPassword((bCryptPasswordEncoder.encode(userDTO.getPassword())));;
		user.setFullName(userDTO.getFullName());
		user.setEmail(userDTO.getEmail());
		user.setAddress(userDTO.getAddress());
		user.setPhone(userDTO.getPhone());
		user.setDateofBirth(userDTO.getDateofBirth());
		user.setImage(userDTO.getImage());
		user.setEnabled(true);
		userRepo.save(user);
		if(userDTO.getRole() != null) {
		userRoleRepo.findByUser(user).setRole(userDTO.getRole());;
		}
		return user;
	}
	
	public UserDTO findByUserName(String userName) {
		Users user = userRepo.findByUserName(userName);
		if(user == null) {
			return null;
		}
		UserDTO userDTO = modelMap.map(user, UserDTO.class);
		return userDTO;
	}
	
	public List<Users> findAllUser() {
		return userRepo.findAll();

	}
	
	public List<Users> findAllAdmin() {
		return userRepo.findAllAdmin();
	}

}
