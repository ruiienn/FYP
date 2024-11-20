package fyp.tayxinyu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MoreController {
	@GetMapping("/more")
	public String more() {
		return "more";
	}
	@GetMapping("/avatar")
	public String avatar() {
		return "avatar";
	}
	@GetMapping("/profile")
	public String profile() {
		return "profile";
	}
	@GetMapping("/guide")
	public String guide() {
		return "guide";
	}
	@GetMapping("/feedback")
	public String feedback() {
		return "feedback";
	}
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	@GetMapping("/forget")
	public String forget() {
		return "forget";
	}
}
