package fyp.tayxinyu;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ActivitiesController {
	@Autowired
	private ActivitiesRepository activitiesRepository;

	@GetMapping("/activities")
	public String viewActivities(Model model) {

		List<Activities> listActivities = activitiesRepository.findAll();

		model.addAttribute("listActivities", listActivities);
		return "activities";
	}

	@GetMapping("/activities/add")
	public String addActivities(Model model) {
		model.addAttribute("activities", new Activities());

		List<Activities> listActivities = activitiesRepository.findAll();
		model.addAttribute("listActivities", listActivities);

		return "add_activities";
	}

	@PostMapping("/activities/save")
	public String saveItem(Activities activities, @RequestParam("activitiesImages") MultipartFile imagesFile) {

		String imagesName = imagesFile.getOriginalFilename();

		// Set the image name in the activities object
		activities.setImagesName(imagesName);
		Activities savedActivities = activitiesRepository.save(activities);

		try {
			// Preparing the directory path
			String uploadDir = "uploads/activities/" + savedActivities.getId(); // Assuming getId() is a method in your
																				// activities class
			// to get the ID
			Path uploadPath = Paths.get(uploadDir);
			System.out.println("Directory path: " + uploadPath);

			// Checking if the upload path exists, if not it will be created.
			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}

			// Prepare path for the file
			Path fileToCreatePath = uploadPath.resolve(imagesName);
			System.out.println("File path: " + fileToCreatePath);

			// Copy the file to the upload location
			Files.copy(imagesFile.getInputStream(), fileToCreatePath, StandardCopyOption.REPLACE_EXISTING);

			// Save the activities object to the database after successfully saving the file
			/* activities savedActivities = activitiesRepository.save(activities); */

		} catch (IOException io) {
			io.printStackTrace();
		}

		return "redirect:/activities";
	}

	@GetMapping("/activities/edit/{id}")
	public String editActivities(@PathVariable("id") Integer id, Model model) {
		Activities activities = activitiesRepository.getReferenceById(id);
		model.addAttribute("activities", activities);
		return "edit_activities";
	}

	@PostMapping("/activities/edit/{id}")
	public String saveUpdatedActivities(@PathVariable("id") Integer id, Activities activities,
			@RequestParam("activitiesImages") MultipartFile imagesFile) {

		// if imagesFile is NOT empty, proceed to:
		// set new images name for activities object, save the activities object, upload
		// new file and
		// update db
		// otherwise, this means no new file was uploaded, simply save the activities
		// object

		if (!imagesFile.isEmpty()) {
			String imagesName = imagesFile.getOriginalFilename();
			System.out.println("Image name from imagesFile: " + imagesName);

			// Set the image name in activities object
			activities.setImagesName(imagesName);

			Activities savedActivities = activitiesRepository.save(activities);

			try {
				// Preparing the directory path
				String uploadDir = "uploads/activities/" + savedActivities.getId();
				Path uploadPath = Paths.get(uploadDir);
				System.out.println("Directory path: " + uploadPath);

				// Checking if the upload path exists, if not it will be created.
				if (!Files.exists(uploadPath)) {
					Files.createDirectories(uploadPath);
				}

				// Prepare path for file
				Path fileToCreatePath = uploadPath.resolve(imagesName);
				System.out.println("File path: " + fileToCreatePath);

				// Copy file to the upload location
				Files.copy(imagesFile.getInputStream(), fileToCreatePath, StandardCopyOption.REPLACE_EXISTING);

			} catch (IOException io) {
				io.printStackTrace();
			}
		} else { // No edit to image, save the activities object as passed.

			System.out.println("Image name from activities object: " + activities.getImagesName());
			activitiesRepository.save(activities);
		}

		return "redirect:/activities";
	}

	// delete activities
	@GetMapping("/activities/delete/{id}")
	public String deleteActivities(@PathVariable("id") Integer id) {

		activitiesRepository.deleteById(id);

		return "redirect:/activities";
	}
}
