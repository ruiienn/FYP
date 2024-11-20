package fyp.tayxinyu;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Activities {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@NotEmpty(message="Please select your diploma!")
	private String diploma; 
	@NotEmpty(message="Please enter your booth!")
	private String booth;
	@NotEmpty(message="Please enter the activity!")
	private String activity;
	@NotEmpty(message="Please enter the activity's description!")
	private String description;
	@NotNull(message="Please enter the amount of points to reward for the activity!")
	@Min(value=1, message="Minimum point is 1!")
	private int point;

	
	public int getId() {
		return id;
	}
	
	public String getDiploma() {
		return diploma;
	}
	public void setDiploma(String diploma) {
		this.diploma = diploma;
	}
	public String getBooth() {
		return booth;
	}
	public void setBooth(String booth) {
		this.booth = booth;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
