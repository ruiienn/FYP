package fyp.tayxinyu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RewardsController {
	@GetMapping("/rewards")
	public String rewards() {
		return "rewards";
	}

	@GetMapping("/redeem")
	public String redeem() {
		return "redeem";
	}
	@GetMapping("/history")
	public String history() {
		return "history";
	}

}


