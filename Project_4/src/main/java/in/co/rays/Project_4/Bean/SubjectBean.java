package in.co.rays.Project_4.Bean;

/**
 * Subject JavaBean encapsulates Subject attributes.
 * @author Darshan
 *
 */
public class SubjectBean extends BaseBean {
	
	
	private String name;
	private String description;
	private long courseId;
	private String courseName;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getCourseId() {
		return courseId;
	}
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getKey() {
		return id+"";
	}
	public String getValue() {
		return name;
	}
	
}
