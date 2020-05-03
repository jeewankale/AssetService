package in.co.web.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.co.we.form.UserForm;
import in.co.web.entities.User;
import in.co.web.repository.MyUserPrincipal;
import in.co.web.repository.UserRepository;
import in.co.web.response.BaseResponse;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new MyUserPrincipal(user);
	}

	public List<User> findAllUses() {
		return userRepository.findAll();
	}

	public BaseResponse register(UserForm userForm) {
		User user = userRepository.findByEmail(userForm.getEmail());
		if (user != null) {
			return new BaseResponse(300, "User Already Exist.", "NA");
		}
		user = new User();
		BeanUtils.copyProperties(userForm, user);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setCreatedDate(new Date());
		user.setEnable(true);
		user.setLocked(false);
		userRepository.save(user);
		
		return new BaseResponse(200, "success", user);

	}

	public BaseResponse authnticate(UserForm userForm) {
		User dbUser = userRepository.findByEmail(userForm.getEmail());
		if (dbUser == null) {
			return new BaseResponse(300, "User not Exist.", "NA");
		} else {
			if (passwordEncoder.matches(userForm.getPassword(), dbUser.getPassword())) {
				return new BaseResponse(200, "success", "NA");
			} else {
				return new BaseResponse(401, "Unauthorized client", "NA");
			}
		}
	}
}
