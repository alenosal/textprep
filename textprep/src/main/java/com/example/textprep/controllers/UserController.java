package com.example.textprep.Controllers;


import com.example.textprep.Services.ConfirmationTokenService;
import com.example.textprep.Services.UserService;
import com.example.textprep.entity.ConfirmationToken;
import com.example.textprep.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class UserController {

	private final UserService userService;

	private final ConfirmationTokenService confirmationTokenService;


	@GetMapping("/sign-in")
	String signIn() {

		return "sign-in";
	}

	@GetMapping("/sign-up")
	String signUpPage(User user) {

		return "sign-up";
	}

	@PostMapping("/sign-up")
	String signUp(User user) {

		userService.signUpUser(user);

		return "redirect:/sign-in";
	}

	@GetMapping("/sign-up/confirm")
	String confirmMail(@RequestParam("token") String token) {

		Optional<ConfirmationToken> optionalConfirmationToken = confirmationTokenService.findConfirmationTokenByToken(token);

		//optionalConfirmationToken.ifPresent(userService::confirmUser);

		return "redirect:/sign-in";
	}

}
