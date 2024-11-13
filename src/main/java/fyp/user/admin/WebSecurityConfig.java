/**
 * 
 * I declare that this code was written by me, xandr. 
 * I will not copy or allow others to copy my code. 
 * I understand that copying code is considered as plagiarism.
 * 
 * Student Name: xandra
 * Student ID: 22022591
 * Date created: 2024-Nov-03 10:59:49 pm 
 * 
 */
package fyp.user.admin;

/**
 * @author xandr
 *
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


/**
 * @author xandr
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Bean
	public MemberDetailsService memberDetailsService() {
		return new MemberDetailsService();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(memberDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
 
//		        .requestMatchers("/categories/add", "/categories/edit/*", "/categories/save", "/categories/delete/*").hasRole("ADMIN")
		        .requestMatchers("/rewards/add", "/rewards/edit/*", "/rewards/save", "/rewards/delete/*").hasRole("ADMIN")
		        .requestMatchers("/members", "/members/edit/*",  "/members/delete/*").hasRole("ADMIN")
	        	.requestMatchers("/members/add","/members/save").permitAll() //Home page is visible without logging in
	        	.requestMatchers("/bootstrap/*/*").permitAll() //for static resources, visible to all
	        	.requestMatchers("/images/*").permitAll() //for static resources, visible to all
	            .anyRequest().authenticated()//Other requests such as to view an item with id 1: /items/1            
	          		
				) //end of authorizeHttpRequests
			.formLogin((login) -> login.loginPage("/login").permitAll().defaultSuccessUrl("/")) //Goes to homepage upon login
			.logout((logout) -> logout.logoutSuccessUrl("/")) //Goes to homepage upon logout
						         
			.exceptionHandling((exceptionHandling) -> exceptionHandling.accessDeniedPage("/403"));
 
		return http.build();
 
	}
	
}

