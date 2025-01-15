package fyp.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;

	public void addPoints(Integer memberId, int points) {
		Member member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("Member not found"));
		member.setPoints(member.getPoints() + points);
		memberRepository.save(member);
	}

}
