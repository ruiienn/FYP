/**
 * 
 * I declare that this code was written by me, ${user}. 
 * I will not copy or allow others to copy my code. 
 * I understand that copying code is considered as plagiarism.
 * 
 * Student Name: 
 * Student ID: 
 * Date created: ${id:date('YYYY-MMM-dd')} ${time} 
 * 
 */

package fyp.user.admin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;

@Controller
public class RewardsController {
	
	@Autowired
	private RewardsRepository rewardsRepository;
	
	@GetMapping("/rewards")
	public String viewRewards(Model model) {
		
		List<Rewards> listRewards = rewardsRepository.findAll();
		model.addAttribute("listRewards", listRewards);
		return "view_rewards";
	}
	
	@GetMapping("/rewards/add")
	public String addRewards(Model model) {
		model.addAttribute("rewards", new Rewards());
		return "add_rewards";
	}
	
	@GetMapping("/rewards/edit/{rewardsId}")
	public String editRewards(@PathVariable("rewardsId") Integer rewardsId, Model model) {
		Rewards rewards = rewardsRepository.findById(rewardsId).orElseThrow(() -> new IllegalArgumentException("Invalid reward ID:" + rewardsId));		
		model.addAttribute("rewards", rewards);
		return "edit_rewards";
	}
	
	@GetMapping("/rewards/view/{rewardsId}")
	public String viewSingleReward(@PathVariable("rewardsId") Integer rewardsId, Model model) {
	    Rewards rewards = rewardsRepository.findById(rewardsId)
	            .orElseThrow(() -> new IllegalArgumentException("Invalid reward ID: " + rewardsId));
	    model.addAttribute("rewards", rewards);
	    return "view_single_reward";
	}
	
	@PostMapping("/rewards/save")
	public String saveRewards(@Valid Rewards rewards, BindingResult result, @RequestParam("rewardsImage") MultipartFile imgFile) {
		
	    if (result.hasErrors()) {
	        return "add_rewards";
	    }
	    
	    if(rewards.getStatus() == null || rewards.getStatus().isEmpty()) {
	    	rewards.setStatus("Available");
	    }
	    
	    String imageName = imgFile.getOriginalFilename();
	    rewards.setImgName(imageName);
	    Rewards savedRewards = rewardsRepository.save(rewards);
	    
	    try {
	    	String uploadDir = "uploads/rewards/" +savedRewards.getRewardsId();
	    	Path uploadPath = Paths.get(uploadDir);
			System.out.println("Directory path: " + uploadPath);
			if (!Files.exists(uploadPath)) {

				Files.createDirectories(uploadPath);
				
			}
			Path fileToCreatePath = uploadPath.resolve(imageName);
			System.out.println("File path: " + fileToCreatePath);
			Files.copy(imgFile.getInputStream(), fileToCreatePath, StandardCopyOption.REPLACE_EXISTING);

		} catch (IOException io) {
			io.printStackTrace();
	    }
	    
	    return "redirect:/rewards";
	}
	
	@PostMapping("rewards/delete/{rewardsId}")
	public String saveDeletedRewards(@PathVariable("rewardsId")Integer rewardsId) {
		rewardsRepository.deleteById(rewardsId);
		return "redirect:/rewards";
	}
	
	@PostMapping("/rewards/edit/{rewardsId}")
	public String savedUpdatedRewards(@PathVariable("rewardsId") Integer rewardsId, @Valid Rewards rewards, 
										BindingResult result, @RequestParam("rewardsImage") MultipartFile imgFile) {
		if(result.hasErrors()) {
			return "edit_rewards";
		}
		
		Rewards existingReward = rewardsRepository.findById(rewardsId).
				orElseThrow(() -> new IllegalArgumentException("Invalid reward ID:" + rewardsId));
		
		existingReward.setDescription(rewards.getDescription());
		existingReward.setQuantity(rewards.getQuantity());
		existingReward.setPointsRequired(rewards.getPointsRequired());
		
		existingReward.setStatus(rewards.getStatus());
		
		if(!imgFile.isEmpty()) {
			String imageName = imgFile.getOriginalFilename();
			existingReward.setImgName(imageName);
			
			try {
				String uploadDir = "uploads/rewards/" + rewardsId;
				Path uploadPath = Paths.get(uploadDir);
				if(!Files.exists(uploadPath)) {
					Files.createDirectories(uploadPath);
				}
				Path fileToCreatePath = uploadPath.resolve(imageName);
				Files.copy(imgFile.getInputStream(), fileToCreatePath, StandardCopyOption.REPLACE_EXISTING);
			}catch(IOException io) {
				io.printStackTrace();
			}
		}
		
		rewardsRepository.save(existingReward);
		return "redirect:/rewards";

	}

}
