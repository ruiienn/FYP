package fyp.tayxinyu;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Activities {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message="Please select your diploma!")
	private String diploma;
	
	@NotEmpty(message="Please enter your booth!")
	private String booth;
	
	private String imagesName;
	
	@NotEmpty(message="Please enter the activity!")
	private String activity;
	
	@NotEmpty(message="Please enter the activity's description!")
	private String description;
	
	@NotNull(message="Please enter the amount of points to reward for the activity!")
	@Min(value=10, message="Minimum point is 10!")
	private int points;
	
	private int diplomaID;
	
	private String diplomaName;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getImagesName() {
		return imagesName;
	}

	public void setImagesName(String imagesName) {
		this.imagesName = imagesName;
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

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getDiplomaID() {
		return diplomaID;
	}

	public void setDiplomaID(int diplomaID) {
		this.diplomaID = diplomaID;
	}

	public String getDiplomaName() {
		return diplomaName;
	}

	public void setDiplomaName(String diplomaName) {
		this.diplomaName = diplomaName;
	}
	
}