/**
 * 
 * I declare that this code was written by me, xandr. 
 * I will not copy or allow others to copy my code. 
 * I understand that copying code is considered as plagiarism.
 * 
 * Student Name: xandra
 * Student ID: 22022591
 * Date created: 2024-Oct-27 10:26:46 pm 
 * 
 */
package fyp.user.admin;

import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author xandr
 *
 */
public interface MemberRepository extends JpaRepository<Member, Integer> {
	public Member findByUsername(String username);
	

}
