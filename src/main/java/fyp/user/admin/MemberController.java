/**
 * 
 * I declare that this code was written by me, xandr. 
 * I will not copy or allow others to copy my code. 
 * I understand that copying code is considered as plagiarism.
 * 
 * Student Name: xandra
 * Student ID: 22022591
 * Date created: 2024-Oct-27 10:24:48 pm 
 * 
 */
package fyp.user.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;


/**
 * @author xandr
 *
 */
@Controller
public class MemberController {
	@Autowired
	private MemberRepository memberRepository;
	
	@GetMapping("/members")
	public String viewMembers(Model model) {
		List<Member> listMembers = memberRepository.findAll();
		model.addAttribute("listMembers", listMembers);
		return "view_member";
	}
	
	@GetMapping("/members/add")
	public String addMember(Model model) {
		model.addAttribute("member", new Member());
		return "add_member";
	}

	@PostMapping("/members/save")
	public String saveMember(RedirectAttributes redirectAttribute, @Valid Member member, BindingResult bindingResult) {

		if(bindingResult.hasErrors()) {
			System.out.println("error");
			return "add_member";
		}
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(member.getPassword());
		
		member.setPassword(encodedPassword);
		member.setRole("ROLE_USER");
		member.setRole("ROLE_ADMIN");
		memberRepository.save(member);
		
		redirectAttribute.addFlashAttribute("success", "Member registered!");
		
		return "redirect:/members";
	}
	
	@GetMapping("/members/edit/{id}")
	public String editMember(@PathVariable("id") Integer id, Model model) {
		Member member = memberRepository.getReferenceById(id);
		model.addAttribute("member", member);
		return "edit_member";
	}
	
	@PostMapping("/members/edit/{id}")
	public String savedUpdatedMember(@PathVariable("id") Integer id, Member member) {
		member.setPassword(memberRepository.getReferenceById(id).getPassword());
		memberRepository.save(member);
		return "redirect:/members";
	}
	
	@GetMapping("/members/delete/{id}")
	public String deleteMember(@PathVariable("id") Integer id) {
		memberRepository.deleteById(id);
		return "redirect:/members";
	}
}

