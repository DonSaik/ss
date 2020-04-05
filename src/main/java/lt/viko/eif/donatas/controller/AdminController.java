package lt.viko.eif.donatas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

	@GetMapping(value="/admin-home")
	public String showAdminHome() {
		return "admin/home";
	}
}
