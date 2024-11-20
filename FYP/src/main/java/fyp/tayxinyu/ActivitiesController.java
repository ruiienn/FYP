package fyp.tayxinyu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class ActivitiesController {

    @Autowired
    private ActivitiesRepository activitiesRepository;
    
    
	@GetMapping("/activities")
	public String activities(Model model) {
		
		List<Activities> listActivities = activitiesRepository.findAll();
		
		model.addAttribute("listActivities",listActivities);
		return "activities";
	}
	@GetMapping("/activities/add")
		public String addActivities(Model model) {
		model.addAttribute("activities", new Activities());
		return "add_activities";
	}
	
	@PostMapping("/activities/save")
	public String saveActivities(@Valid Activities activities, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "add_activities";
		}
		activitiesRepository.save(activities);
		return "redirect:/activities";
	}
	@GetMapping("/activities/edit/{id}")
	public String editActivities(@PathVariable("id") Integer id, Model model) { 
		
		Activities activities = activitiesRepository.getReferenceById(id);
		model.addAttribute("activities",activities);
		
		return "edit_activities";
	}
	
	@PostMapping("/activities/edit/{id}")
	public String saveUpdatedActivities(@PathVariable("id") Integer id,Activities activities) {
		activitiesRepository.save(activities);
		return "redirect:/activities";		
	}
	@GetMapping("/activities/delete/{id}")
	public String deleteActivities(@PathVariable("id")Integer id) {
		activitiesRepository.deleteById(id);
		return "redirect:/activities";	
	}
}
