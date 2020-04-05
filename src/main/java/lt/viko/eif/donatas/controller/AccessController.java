package lt.viko.eif.donatas.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import lt.viko.eif.donatas.model.Authority;
import lt.viko.eif.donatas.model.User;
import lt.viko.eif.donatas.recaptcha.RecaptchaService;
import lt.viko.eif.donatas.service.UserService;


@Controller
public class AccessController {

	private static final Logger logger = Logger.getLogger(HomeController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	@Qualifier("recapthcaSite")
	private String recaptchaSite;
	
	@Autowired
	private RecaptchaService recaptchaService;
	
	@GetMapping("/login")
	public String showLogin() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		logger.info("Gettinglogin page");
		if (!(auth instanceof AnonymousAuthenticationToken)) {

		    return "redirect:/";
		}
		return "access/login";
	}
	@PostMapping("/logout")
	public String logoutProcess() {
		logger.info("Loggout");
		return "redirect:/";
	}
	@GetMapping("/access-denied")
	public String showAccessDenied() {
		return "access/access-denied";
	}
	@GetMapping("/sign-up")
	public String showAccessDenied(HttpServletRequest request, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		logger.info("Getting signup page");
		if (!(auth instanceof AnonymousAuthenticationToken)) {

		    return "redirect:/";
		}
		model.addAttribute("user", new User());
		model.addAttribute("recapthcaSite", recaptchaSite);
		return "access/signup";
	}
	@PostMapping("/sign-up")
	public String showSignUpForm(HttpServletRequest request, 
			@Valid @ModelAttribute ("user") User user, BindingResult result, Errors errors) {
		
		String clientResponse=request.getParameter("g-recaptcha-response");
		boolean isValidRecaptcha = false;
		if(userService.getUserByUsername(user.getUsername())!=null) {
			logger.info("There is such existing user");
			FieldError ssoError = new FieldError("user", "username", "User already exits");
			result.addError(ssoError);
			return "access/signup";
		}
		if(result.hasErrors()) {
			logger.info("User data has errors");
			return "access/signup";
		}
		try {
			isValidRecaptcha=recaptchaService.isResponseValid(clientResponse);
		}
		catch (Exception e) {
			logger.error("reCaptcha service has failed");
			System.out.println(e.getMessage());
		}

		if(isValidRecaptcha) {
			logger.info("reCaptcha is Valid");
			user = userService.resigterNewUser(user);
			authenticateUserAndSetSession(user, request);
			}
		else {
			logger.error("reCaptcha is not Valid");
			request.setAttribute("recaptchaError", "Failed recaptcha test");
			return "access/signup";
		}
		return "redirect:/";
	}
	 private void authenticateUserAndSetSession(User user, HttpServletRequest request) {
		 logger.info("Auto login new user");
		 List<GrantedAuthority> authorities = new ArrayList<>();
			for(Authority role: user.getAuthorities()) {
				authorities.add(new SimpleGrantedAuthority("ROLE_"+ role.getName()));
			}
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
		 authentication.setDetails(new WebAuthenticationDetails(request));
		    SecurityContextHolder.getContext().setAuthentication(authentication);
	    }
}
