package in.co.rays.Project_4.Bean;



/**
 * Marksheet JavaBean encapsulates Marksheet attributes.
 * @author Darshan
 *
 */
public class MarksheetBean extends BaseBean {
	private String rollNo;
	private long studentId;
	private String name;
	private Integer physics;
	private Integer chemistry;
	private Integer maths;
	private long total;
	
public MarksheetBean() {
	
}
	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPhysics() {
		return physics;
	}

	public void setPhysics(Integer physics) {
		this.physics = physics;
	}

	public Integer getChemistry() {
		return chemistry;
	}

	public void setChemistry(Integer chemistry) {
		this.chemistry = chemistry;
	}

	public Integer getMaths() {
		return maths;
	}

	public void setMaths(Integer maths) {
		this.maths = maths;
	}

	public String getKey() {
		return id + "";
	}

	public String getValue() {
		return rollNo;
	}
}
