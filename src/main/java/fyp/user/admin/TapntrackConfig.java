<<<<<<< HEAD
package fyp.user.admin;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
public class TapntrackConfig implements WebMvcConfigurer {

=======
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

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class TapntrackConfig implements WebMvcConfigurer {
>>>>>>> branch 'main' of https://github.com/ruiienn/FYP.git
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/uploads/**").addResourceLocations("file:uploads/");
	}
}
