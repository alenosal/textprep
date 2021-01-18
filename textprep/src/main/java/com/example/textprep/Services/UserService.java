package com.example.textprep.Services;


import com.example.textprep.Repositories.UserRepository;
import com.example.textprep.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	private final ConfirmationTokenService confirmationTokenService;

	private final EmailSenderService emailSenderService;


	void sendConfirmationMail(String userMail, String token) {

		final SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(userMail);
		mailMessage.setSubject("Mail Confirmation Link!");
		mailMessage.setFrom("<MAIL>");
		mailMessage.setText(
				"Thank you for registering. Please click on the below link to activate your account." +
						"http://localhost:8080/sign-up/confirm?token=" + token);

		emailSenderService.sendEmail(mailMessage);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		final UserDetails optionalUser = userRepository.findByUsername(username);

		//return optionalUser.orElseThrow(() -> new UsernameNotFoundException(MessageFormat.format("User with email {0} cannot be found.", username)));
		return optionalUser;
	}

	public void signUpUser(User user) {

		final String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());

		user.setPassword(encryptedPassword);

		user.setEnabled(true);

		final User createdUser = userRepository.save(user);
//
//		final ConfirmationToken confirmationToken = new ConfirmationToken(user);
//
//		confirmationTokenService.saveConfirmationToken(confirmationToken);


	}
//	void confirmUser(ConfirmationToken confirmationToken) {
//
//		final User user = confirmationToken.getUser();
//
//
//
//		userRepository.save(user);
//
//		confirmationTokenService.deleteConfirmationToken(confirmationToken.getId());
//
//	}
}
