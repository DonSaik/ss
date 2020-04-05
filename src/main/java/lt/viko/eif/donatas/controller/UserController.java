package lt.viko.eif.donatas.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lt.viko.eif.donatas.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public String getUsers(HttpServletRequest request, Model model ) {
		
		String username = request.getParameter("serachUsers");
		if(username != null) {
			model.addAttribute("users", userService.filterUsersByUsername(username));
			model.addAttribute("serachUsers", username);
		}
		else {
			model.addAttribute("users", userService.getUsers());
			model.addAttribute("serachUsers", username);
			}
		return "users";
	}
}
