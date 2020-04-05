package lt.viko.eif.donatas.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	private static final Logger logger = Logger.getLogger(HomeController.class);
	@GetMapping("/")
	public String showIndex() {
		logger.info("Getting index page");
		return "index";
	}
	@GetMapping("/home")
	public String showHome() {
		logger.info("Getting home page");
		return "home";
	}

}
